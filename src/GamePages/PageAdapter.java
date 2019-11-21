package GamePages;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import Managers.GameManager;

public abstract class PageAdapter {
	
	public static final int GAME_WIDTH=1200;
	public static final int GAME_HEIGHT=600;
	
	public abstract void draw(Graphics2D g2d);
	public abstract void init();
	public abstract void update();

	public abstract void keyPressed(KeyEvent e);
	public abstract void keyReleased(KeyEvent e);
	public abstract void keyTyped(KeyEvent e);
}
