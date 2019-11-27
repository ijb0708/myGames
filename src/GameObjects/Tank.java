package GameObjects;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import Managers.Animation;
import Managers.GameTimer;

public class Tank extends GameObject {

	// 탱그 사이즈
	private static final int TANK_SIZE = 38;

	// 추가적인 상태를 위한 변수
	private boolean isFire;

	// 애니메이션 속도 관련 변수
	private double moveDelta;

	// 플레이어 변수(더미)
	private int player;
	
	private int maxHealth;
	private int curHealth;

	private BufferedImage sprite;

	private GameTimer gt;

	private Animation move;
	private Animation fire;

	public Tank(int x, int y, int p) {
		super(x, y, TANK_SIZE, TANK_SIZE);
		this.player = p;
		isMoving = false;
		isFire = true;
		moveSpeed = 2.5;
		fallSpeed = 0.2;
		jumpSpeed = 0.2;
		curJumpSpeed = maxJumpSpeed = 6.5;
		curFallSpeed = 0.2;
		moveDelta = 120000000;
		
		curHealth = maxHealth = 500;
		
		
		gt = new GameTimer();
		gt.setDelay(800000000);
		
		hitBox = new Rectangle2D.Double(mapX + 3, mapY + 3, width - 6, height - 6);

		move = new Animation(il.getTank1(), moveDelta, false);
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

		updateHitBox();
		updateBoxs();
		correctLocation();
	}

	@Override
	public void draw(Graphics2D g2d) {

//		if() {
//			
//		}else {
		if (isRside) {
			g2d.drawImage(move.getFrame(), dx, dy, width, height, null);
		} else {
			g2d.drawImage(move.getFrame(), dx + width, dy, -width, height, null);
		}
//		}

//		g2d.setColor(Color.red);
//		for (int i = 0; i < 5; i++) {
//			g2d.draw(boxs[i]);
//		}
//		g2d.draw(hitBox);
//		
//		g2d.draw(baseLine);

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
		case KeyEvent.VK_X:
			shoot();
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
		case KeyEvent.VK_X:
			break;
		}

	}

	// getter&setter
	public int getPlayer() {
		return player;
	}

	private void shoot() {

		if (gt.check()) {
			if (isRside) {
				om.addBullet(new Bullet((int) mapX + (width - 10), (int) mapY + (height / 12), Bullet.RIGHT, player));
			} else {
				om.addBullet(new Bullet((int) mapX + 10, (int) mapY + (height / 12), Bullet.LEFT, player));
			}
			gt.start();
		}
	}

	private void updateHitBox() {
		hitBox.setRect(mapX + 3, mapY + 2, width - 6, height - 4);
	}
}