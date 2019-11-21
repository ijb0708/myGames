package GameObjects;

import java.awt.Graphics2D;

public class Tank extends GameObject {

	private boolean isShut, isJumping, isFalling;

	private double moveSpeed, jumpSpeed;
	private double curJumpSpeed, maxJumpSpeed;
	private double attackDelay;

	private int player;

	private static final int TANK_SIZE = 50;

	public Tank(int x, int y, int p) {
		super(x, y, TANK_SIZE, TANK_SIZE);
		this.player = p;
		isMoving = false;
		fallingSpeed = 3.0;
		jumpSpeed = 3.5;
		moveSpeed = 2.5;
		curJumpSpeed = maxJumpSpeed = 10.5;
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
//		}

		if (isJumping&&!isFalling) {
			mapY-=curJumpSpeed;
			curJumpSpeed-=0.5;
			
			if(curJumpSpeed<=0) {
				curJumpSpeed=maxJumpSpeed;
				isJumping=false;
			}
		}

		falling();
		updateLine();

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

		// g2d.setColor(Color.black);
		// g2d.draw(Line[0]);
		// g2d.draw(Line[1]);
		// g2d.draw(Line[2]);
		// g2d.draw(Line[3]);
	}

	public void jump() {
		isJumping=true;
	}

	public void right() {
		isMoving = true;
		isRight = true;
		isRside = true;
	}

	public void left() {
		isMoving = true;
		isLeft = true;
		isRside = false;
	}

	public void notRight() {
		isRight = false;
		if (!isLeft) {
			isMoving = false;
		} else {
			isRside = false;
		}
	}

	public void notLeft() {
		isLeft = false;
		if (!isRight) {
			isMoving = false;
		} else {
			isRside = true;
		}
	}

	public int getPlayer() {
		return player;
	}
}