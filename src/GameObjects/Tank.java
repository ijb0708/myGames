package GameObjects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import Managers.Animation;

public class Tank extends GameObject {

	// 탱그 사이즈
	private static final int TANK_SIZE = 38;

	// 추가적인 상태를 위한 변수
	private boolean isFire, isJumping, isPosJump;

	// 속도 관련 변수
	private double moveSpeed;
	private double curJumpSpeed, maxJumpSpeed;
	private double curFallSpeed;
	private double attackDelay;

	// 애니메이션 속도 관련 변수
	private double moveDelta;

	// 플레이어 변수(더미)
	private int player;

	private Animation move;
	private Animation fire;

	public Tank(int x, int y, int p) {
		super(x, y, TANK_SIZE, TANK_SIZE);
		this.player = p;
		isMoving = false;
		isFire=true;
		moveSpeed = 2.5;
		curJumpSpeed = maxJumpSpeed = 6.5;
		curFallSpeed = 0.2;
		moveDelta = 80;

		move = new Animation(il.getTank1(), 120000000);
	}

	@Override
	public void update() {

//		if (!isFire) {
//			
//		} else {
			if (isMoving) {
				move.start();
			} else {
				move.end();
			}
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
			g2d.drawImage(move.getFrame(), dx, dy, width, height, null);
		} else {
			g2d.drawImage(move.getFrame(), dx + width, dy, -width, height, null);
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
			curFallSpeed += 0.25;

			isFalling = true;
		} else {
			curFallSpeed = 0.2;
			isFalling = false;
			isPosJump = true;
		}

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