package GamePages;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public interface PageInterface {
	
	public void draw(Graphics2D g2d);
	public void init();
	public void update();

	public void keyPressed(KeyEvent e);
	public void keyReleased(KeyEvent e);
	public void keyTyped(KeyEvent e);
	
}
