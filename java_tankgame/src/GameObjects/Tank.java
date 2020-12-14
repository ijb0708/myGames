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

	// 플레이어 변수(더미)
	private int player;

	private int maxHealth;
	private int curHealth;

	private BufferedImage sprite;

	private GameTimer gt;

	private Animation move;
	private Animation fire;
	private Animation bomb;

	public Tank(int x, int y, int p) {
		super(x, y, TANK_SIZE, TANK_SIZE);
		this.player = p;
		isMoving = false;
		isFire = false;
		moveSpeed = 2.5;
		fallSpeed = 0.2;
		jumpSpeed = 0.2;
		curJumpSpeed = maxJumpSpeed = 6.5;
		curFallSpeed = 0.2;

		double attackDelay = 800000000;

		curHealth = maxHealth = 500;

		gt = new GameTimer();
		gt.setDelay(attackDelay);

		hitBox = new Rectangle2D.Double(mapX + 3, mapY + 3, width - 6, height - 6);

		bomb = new Animation(il.getBomb(), 100000000, true);
		move = new Animation(il.getTank1(), 120000000, false);
		// fire = new Animation(il.getTankFire1(), 150000000, true);
	}

	@Override
	public void update() {

//		if (isFire) {
//			fire.start();
//		} else {
		if (isMoving) {
			move.start();
		} else {
			move.end();
		}
//		}

		checkHitTank();

		updateHitBox();
		updateBoxs();

		updateMoving();
		updateFalling();
		updateJumping();

		correctLocation();
	}

	@Override
	public void draw(Graphics2D g2d) {

//		if(fire.isRun) {
//			g2d.drawImage(fire.getFrame(), dx, dy, width, height, null);
//		}else {
		if (bomb.isStart) {
			g2d.drawImage(bomb.getFrame(), dx, dy, width, height, null);
		} else {
			if (isRside) {
				g2d.drawImage(move.getFrame(), dx, dy, width, height, null);
			} else {
				g2d.drawImage(move.getFrame(), dx + width, dy, -width, height, null);
			}
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
		}
		if(e.getKeyCode()==KeyEvent.VK_X) {
			shoot();
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

	public int getCurHealth() {
		return curHealth;
	}

	public int getMaxHealth() {
		return maxHealth;
	}
	
	public void giveTankHealth() {
		isDead=false;
		curHealth=maxHealth;
	}

	private void shoot() {

		isFire = true;

		if (gt.check()) {
			if (isRside) {
				om.addBullet(new Bullet((int) mapX + (width - 10), (int) mapY + (height / 12), Bullet.RIGHT, player));
			} else {
				om.addBullet(new Bullet((int) mapX + 10, (int) mapY + (height / 12), Bullet.LEFT, player));
			}
			gt.start();
		}
		isFire = false;
	}

	private void updateHitBox() {
		hitBox.setRect(mapX + 3, mapY + 2, width - 6, height - 4);
	}

	private void checkHitTank() {
		if (om.checkHitTank(this)) {
			this.curHealth -= 10;
		}
		if (curHealth <= 0) {
			bomb.start();
			if (bomb.isFinish) {
				isDead = true;
			}
		}
	}
	
	
}