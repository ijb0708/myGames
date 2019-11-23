package GameObjects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class Tank extends GameObject {

	private boolean isShut, isJumping, isPosJump;

	private double moveSpeed;
	private double curJumpSpeed, maxJumpSpeed;
	private double curFallSpeed;
	private double attackDelay;

	private int player;

	private static final int TANK_SIZE = 50;

	public Tank(int x, int y, int p) {
		super(x, y, TANK_SIZE, TANK_SIZE);
		this.player = p;
		isMoving = false;
		moveSpeed = 2.5;
		curJumpSpeed = maxJumpSpeed = 7.5;
		curFallSpeed = 0.2;
	}

	@Override
	public void update() {

//		if (isFalling) {
//			if (isMoving) {
//				if (isRside) {
//					mapX += moveSpeed;
//				} else {
//					mapX -= moveSpeed;
//				}
//			}
//		} else {
//		}

		updateMoving();
		updateFalling();
		updateJumping();

		updateBoxs();
		dx = (int) mapX;
		dy = (int) mapY;
	}

	@Override
	public void draw(Graphics2D g2d) {
		if (isRside) {
			g2d.drawImage(il.getTank(0, 0), dx, dy, width, height, null);
		} else {
			g2d.drawImage(il.getTank(0, 0), dx + width, dy, -width, height, null);
		}

		g2d.setColor(Color.red);
		for (int i = 0; i < 5; i++) {
			g2d.draw(boxs[i]);
		}
		g2d.draw(baseLine);

	}

	// 키입력 메소드
	public void pressKey(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_RIGHT:
			right();
			break;
		case KeyEvent.VK_LEFT:
			left();
			break;
		case KeyEvent.VK_Z:
			jump();
			break;
		}
	}

	public void releasKey(KeyEvent e) {

		switch (e.getKeyCode()) {
		case KeyEvent.VK_RIGHT:
			notRight();
			break;
		case KeyEvent.VK_LEFT:
			notLeft();
			break;
		}

	}

	// getter&setter
	public int getPlayer() {
		return player;
	}

	// 내부처리함수
	private void updateMoving() {
		if (isMoving) {
			if (isRside) {
				if (!om.checkRight(this)) {
					mapX += moveSpeed;
				}
			} else {
				if (!om.checkLeft(this)) {
					mapX -= moveSpeed;
				}
			}
		}
	}

	private void updateFalling() {

		if (!om.checkBottom(this) && !isJumping) {
			mapY += curFallSpeed;
			curFallSpeed += 0.15;

			isFalling = true;
		} else {
			curFallSpeed = 0.2;
			isFalling = false;
			isPosJump = true;
		}

		System.out.println(isFalling);

		if (mapY > base) {
			mapY = base + 1;
		}
	}

	private void updateJumping() {

		if (isJumping) {
			mapY -= curJumpSpeed;
			curJumpSpeed -= 0.2;
			isPosJump = false;

			if (curJumpSpeed <= 0 || om.checkTop(this)) {
				curJumpSpeed = maxJumpSpeed;
				isJumping = false;
			}

		}
	}

	private void jump() {
		if (isPosJump) {
			isJumping = true;
		}
	}

	private void right() {
		isMoving = true;
		isRight = true;
		isRside = true;
	}

	private void left() {
		isMoving = true;
		isLeft = true;
		isRside = false;
	}

	private void notRight() {
		isRight = false;
		if (!isLeft) {
			isMoving = false;
		} else {
			isRside = false;
		}
	}

	private void notLeft() {
		isLeft = false;
		if (!isRight) {
			isMoving = false;
		} else {
			isRside = true;
		}
	}
}