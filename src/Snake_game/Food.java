package Snake_game;

import java.awt.Color;
import java.awt.Graphics;

public class Food {

	int x=0,y=0;
	boolean isfood =true;
	
	protected static final int ONE_BLOCK = 25;
	
	
	public Food () {}
	
	public boolean eat (WormHead head) {
		if (head.getX()==x&&head.getY()==y) {
			isfood=false;
			return true;
		}else {
			return false;
		}
	}
	
	public void make (WormHead head, int area) {
		while(true) {
			x=(int) (Math.random()*area);
			y=(int) (Math.random()*area);
			if(head.checkOverlap(x, y)) {
				isfood=true;
				return;
			}
		}
	}
	
	public void draw(Graphics g) {
		if(isfood) {
			g.setColor(Color.green);
			g.fillArc(x*ONE_BLOCK, y*ONE_BLOCK, ONE_BLOCK, ONE_BLOCK, 0, 360);	
		}
	}

}
