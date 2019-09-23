package Snake_game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class WormHead extends Object {
	
	private int dir=0;
	
	private final int WIDTH=25;
	private final int HEIGHT=25;
	
	private static final int DIR_UP=1;
	private static final int DIR_LEFT=2;
	private static final int DIR_DOWN=3;
	private static final int DIR_RIGHT=4;
	
	private Point body[] = new Point[225];
	private int length=0;

	public WormHead(int objectX, int objectY) {
		// TODO Auto-generated constructor stub
		super(objectX, objectY);
	}
	
	public void draw (Graphics g) {
		if(x<16&&y<16) {
			g.setColor(Color.BLUE);
			g.fillRect(x*ONE_BLOCK, y*ONE_BLOCK, WIDTH, HEIGHT);
		}
		g.setColor(Color.cyan);
		for(int i=0; i<length; i++)
		g.fillRect(body[i].x*ONE_BLOCK, body[i].y*ONE_BLOCK, WIDTH, HEIGHT);
		
	}
	
	
	public void keyLode (KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_UP:dir=1;break;
			case KeyEvent.VK_RIGHT:dir=2;break;
			case KeyEvent.VK_DOWN:dir=3;break;
			case KeyEvent.VK_LEFT:dir=4;break;
		}
	}
	
	
	public void update() {
		//ÇöÀ§Ä¡
		if(length>0)
			for(int i=length; i>0; i--) {
				if(i==1)body[0].setXY(x, y);
				else body[i-1].setXY(body[i-2].x, body[i-2].y);
			}
		switch(dir) {
			case DIR_UP:--y;break;
			case DIR_LEFT:++x;break;
			case DIR_DOWN:++y;break;
			case DIR_RIGHT:--x;break; 
		}
		System.out.printf("%d\n", length);
	}
	
	public void AddBody () { body[length]= new Point(15, 15);++length;}
	
	
	public boolean crash () {
		for(int i=0; i<length; i++) {
			if(body[i].x==x&&body[i].y==y&&dir!=0) {
				return true;
			}
		}
		if(x<0||y<0||y>=15||x>=15) { return true;}
		
		return false;
	}
	
	public boolean checkOverlap (int curX, int curY) {
		for(int i=0; i<length; i++) {
			if(body[i].x==curX&&body[i].y==curY) {
				return false;
			}
		}
		
		if(x==curX&&y==curY) {
			return false;
		}
		
		return true;
	}
	
	class Point {
		int x, y;
		
		public  Point (int curX, int curY) {
			x=curX;
			y=curY;
		}
		
		public void setXY(int curX, int curY) {
			x=curX;
			y=curY;
		}
	}

}
