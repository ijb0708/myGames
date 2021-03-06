package GameObjects;

import java.awt.Graphics2D;

import GamePages.PageAdapter;
import Managers.Animation;

public class Bullet extends GameObject {

	private static final int SHELL_SIZE = 10;
	public static final int LEFT = 1, RIGHT = 2;

	private int dir;
	private int player;

	private Animation bomb;

	public Bullet(int x, int y, int dir, int p) {
		super(x, y, SHELL_SIZE, SHELL_SIZE);
		moveSpeed = 7.5;
		this.dir = dir;
		this.player = p;
		this.bomb = new Animation(il.getBomb(), 300, true);
	}

	@Override
	public void update() {
		switch (dir) {
		case RIGHT:
			right();
			break;
		case LEFT:
			left();
			break;
		}

		updateMoving();
		updateBoxs();
		updateCrash();
		correctLocation();
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.drawImage(il.getBullet(), dx, dy, SHELL_SIZE, SHELL_SIZE, null);
	}

	private void updateCrash() {
		if(om.checkCenterToEnemy(this)) {
			isDead = true;
		}
		if(om.checkCenterToTank(this)) {
			isDead = true;
		}
		if (om.checkCenter(this)) {
			isDead = true;
		}
		if(mapX>PageAdapter.GAME_WIDTH||mapY>PageAdapter.GAME_HEIGHT
				||mapX<0||mapY<0) {
			isDead = true;
		}
	}
	
	public int getPlayer() {
		return player;
	}
}
