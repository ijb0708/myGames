package GameObjects;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import Managers.Animation;
import Managers.GameTimer;

public class Enemy extends GameObject{
	
	private static final int ENEMY_SIZE=40;

	private double curHealth;
	private double maxHealth;
	
	private Animation bomb;
	
	private GameTimer gt;
	
	public Enemy(int x, int y) {
		super(x, y, ENEMY_SIZE, ENEMY_SIZE);
		curHealth = maxHealth = 50;
		curJumpSpeed = maxJumpSpeed = 6.5;

		isMoving = false;
		moveSpeed = 2.5;
		fallSpeed = 0.2;
		jumpSpeed = 0.2;
		curFallSpeed = 0.2;

		hitBox = new Rectangle2D.Double(mapX, mapY, width, height);
		
		bomb = new Animation(il.getBomb(), 100000000, true);
	
		gt = new GameTimer();
		gt.setDelay(1500000000);
		gt.start();
	}
	
	public void draw(Graphics2D g2d) {
		if(bomb.isStart) {
			g2d.drawImage(bomb.getFrame(), dx, dy, width, height, null);
		}else {
			g2d.drawImage(il.getEnemy(), dx, dy, width, height, null);
		}
	}

	public void update() {
		
		if(isRside) {
			notLeft();
			right();
			if(gt.check()) {
				isRside=false;
				gt.start();
			}
		}else {
			notRight();
			left();
			if(gt.check()) {
				isRside=true;
				gt.start();
			}
		}
		
		checkHit();
		
		updateHitBox();
		updateBoxs();
		
		updateMoving();
		updateFalling();
		updateJumping();

		correctLocation();
	}

	private void updateHitBox() {
		hitBox.setRect(mapX, mapY, width, height);
	}
	
	private void checkHit() {
		if(om.checkHitEnemy(this)) {
			curHealth-=10;
		}
		if(curHealth<=0) {
			bomb.start();
			if(bomb.isFinish) {
				isDead=true;
			}
		}
	}
}
