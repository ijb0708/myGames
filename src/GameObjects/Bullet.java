package GameObjects;

import java.awt.Graphics;

public class Bullet extends GameObject {

	private static final int SHELL_SIZE = 5;
	public static final int 
		LEFT = 1, RIGHT =2;
	
	private int dir;
	private int player;
	
	public Bullet(int x, int y, int dir, int p) {
		super(x, y, SHELL_SIZE, SHELL_SIZE);
		moveSpeed=2.5;
		this.dir = dir;
		this.player=p;
		
	}
	
	public void draw(Graphics g2d) {
		g2d.drawImage(il.getShell(), dx, dy, SHELL_SIZE, SHELL_SIZE, null);
	}
	
	public void update() {
		switch (dir) {
		case RIGHT :
			right();
			break;
		case LEFT :
			left();
			break;
		}
		
		correctLocation();
		
	}
}
