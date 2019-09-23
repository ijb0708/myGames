package Move_Ball;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class GameEngine extends JPanel implements KeyListener {
	
	private static final long serialVersionUID = 1L;
	
	static final int BOARD_WIDTH = 600;
	static final int BOARD_HEIGHT = 600;
	
	
	private Ball ball= new Ball();
	
	public GameEngine() {
//		super();
		this.setBackground(Color.BLACK);
		this.addKeyListener(this);
		this.setFocusable(true);
		
		
		Runnable task = () -> {
			while(true) {
				ball.update();
				repaint();
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		
		new Thread(task).start();
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ball.draw(g);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		ball.keyLode(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}




