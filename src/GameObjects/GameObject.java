package GameObjects;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import GamePages.PageAdapter;
import Managers.Animation;
import Managers.ImageLoader;
import Managers.ObjectManager;

public class GameObject {

	// ��Ʈ�ڽ� ���� ����
	public static final int CENTER=0, TOP = 1, RIGHT = 2, BOT = 3, LEFT = 4;
	protected final double BOXTERM = 20;
	
	// �ҷ����� �Ŵ�����
	protected ImageLoader il = ImageLoader.getInstance();
	protected ObjectManager om = ObjectManager.getInstance();

	// ��Ʈ�ڽ���
	protected Rectangle2D[] boxs = new Rectangle2D[5];
	protected Rectangle2D hitBox;
	protected Line2D baseLine;

	// ���� ���Ǵ� ��ǥ
	protected double mapX, mapY;

	// �ӵ� ���� ����
	protected double moveSpeed, fallSpeed, jumpSpeed;
	protected double curJumpSpeed, maxJumpSpeed;
	protected double curFallSpeed;
	
	//������� �κп��� ���Ǿ����� �ּ�
	protected int base;
	protected int dx, dy;
	protected int width, height;
	
	//�����̰� �ִ� ������ ����
	protected boolean isRight, isLeft, isMoving, isFalling;
	protected boolean isRside;
	
	protected boolean isJumping, isPosJump;

	public GameObject(int x, int y, int width, int height) {
		this.dx = x;
		this.dy = y;
		this.mapX = x;
		this.mapY = y;
		this.width = width;
		this.height = height;
		this.isRside=true;

		//�� �Ʒ� ���� ������
		boxs[TOP] = new Rectangle2D.Double(mapX + 3, mapY - BOXTERM, (double) width - 6, (double) BOXTERM);
		boxs[BOT] = new Rectangle2D.Double(mapX + 3, mapY + height, (double) width - 6, (double) BOXTERM); 
		boxs[LEFT] = new Rectangle2D.Double(mapX - BOXTERM, mapY + 2, (double) BOXTERM, (double) height - 4); 
		boxs[RIGHT] = new Rectangle2D.Double(mapX + width, mapY + 2, (double) BOXTERM, (double) height - 4); 
		boxs[CENTER] = new Rectangle2D.Double(mapX, mapY, (double) width, (double) height); 
		
		baseLine = new Line2D.Double(mapX, mapY, mapX + width, mapY);
		
		isRight=isLeft=isMoving=isFalling=false;
		
	}
	
	//�������̵带 ���� �޼ҵ�
	public void draw(Graphics2D g2d) {

	}

	public void update() {
	}

	//���ڸ� ���������� ������Ʈ�ϱ����� �޼ҵ�
	protected void updateBoxs() {
		boxs[TOP].setRect(mapX + 3, mapY - BOXTERM, (double) width - 6, (double) BOXTERM);
		boxs[BOT].setRect(mapX + 3, mapY + height, (double) width - 6, (double) BOXTERM);
		boxs[LEFT].setRect(mapX - BOXTERM, mapY + 3, (double) BOXTERM, (double) height - 6);
		boxs[RIGHT].setRect(mapX + width, mapY + 3, (double) BOXTERM, (double) height - 6);
		boxs[CENTER].setRect(mapX, mapY, (double) width, (double) height);
		
		for (double i= 0; i<PageAdapter.GAME_HEIGHT; i++) {
				baseLine.setLine(mapX + 3, boxs[CENTER].getCenterY() + i, mapX + width - 6, boxs[CENTER].getCenterY() + i);
			if(om.checkBaseLine(this)) {
				base=(int) (boxs[CENTER].getCenterY()  + i);
				break;
			}
		}
	}

	// ����ó���Լ�
	protected void updateMoving() {
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

	protected void updateFalling() {

		if (!om.checkBottom(this) && !isJumping) {
			mapY += curFallSpeed;
			curFallSpeed += fallSpeed;

			isFalling = true;
		} else {
			curFallSpeed = fallSpeed;
			isFalling = false;
			isPosJump = true;
		}

		if (mapY + height > base) {
			mapY = base - height + 1;
		}
	}

	protected void updateJumping() {

		if (isJumping) {
			mapY -= curJumpSpeed;
			curJumpSpeed -= jumpSpeed;
			isPosJump = false;

			if (curJumpSpeed <= 0 || om.checkTop(this)) {
				curJumpSpeed = maxJumpSpeed;
				isJumping = false;
			}

		}
	}

	protected void jump() {
		if (isPosJump) {
			isJumping = true;
		}
	}

	protected void right() {
		isMoving = true;
		isRight = true;
		isRside = true;
	}

	protected void left() {
		isMoving = true;
		isLeft = true;
		isRside = false;
	}

	protected void notRight() {
		isRight = false;
		if (!isLeft) {
			isMoving = false;
		} else {
			isRside = false;
		}
	}

	protected void notLeft() {
		isLeft = false;
		if (!isRight) {
			isMoving = false;
		} else {
			isRside = true;
		}
	}
	
	protected void correctLocation () {
		dx = (int) mapX;
		dy = (int) mapY;
	}
	
	// getter, setter
	public Rectangle2D getBoxs(int dir) {
		return boxs[dir];
	}
	
	public Line2D getBaseLine() {
		return baseLine;
	}
}
