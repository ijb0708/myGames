package Move_Ball;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Ball {
	public int x = 50;
	public int y = 50;

	private int xSpeed = 10;
	private int ySpeed = 10;
	
	private final int size = 30;
	
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval(x, y, size, size);
	}
	
	public void update() {}

	public void keyLode(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_UP: if(y>0)y-=ySpeed; break;
		case KeyEvent.VK_DOWN: y+=ySpeed; break;
		case KeyEvent.VK_LEFT: if(x>0)x-=xSpeed; break;
		case KeyEvent.VK_RIGHT: x+=xSpeed; break;
		}
		System.out.println(e.getKeyCode());
	}
	
}
