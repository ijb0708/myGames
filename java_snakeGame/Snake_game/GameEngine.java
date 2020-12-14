package Snake_game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameEngine extends JPanel implements KeyListener {
	
	private static final long serialVersionUID = 1L;

	public static final int GAME_WIDTH = 15;
	public static final int GAME_HEIGHT = 15;
	
	private static final int ONE_BLOCK = 25;
	
	private WormHead head;
	private Food food;
	
	private boolean isOver=false;
	
	public GameEngine() {
		this.setSize(GAME_WIDTH*ONE_BLOCK, GAME_HEIGHT*ONE_BLOCK);
		this.setBackground(Color.white);
		this.addKeyListener(this);
		
		head= new WormHead(GAME_WIDTH/2, GAME_HEIGHT/2);
		food= new Food();
		
		Runnable run = () -> {
			while(true) {
				head.update();
				if(head.crash()) {System.out.println("Game Over");isOver=true;repaint();break;}
				if(food.eat(head)) {head.AddBody();food.make(head, 15);}
	//			System.out.printf("%d, %d\n", head.getX(), head.getY());
				repaint();
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		
		new Thread(run).start();
		this.setFocusable(true);
	}
	
	public void draw (Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("±Ã¼­", Font.BOLD, 25));
		g.drawString("Game Over", GAME_WIDTH*ONE_BLOCK/3, GAME_HEIGHT*ONE_BLOCK/3);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		head.keyLode(e);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected void paintComponent (Graphics g) {
		super.paintComponent(g);
		g.fillRect(0, 0, (GAME_WIDTH)*ONE_BLOCK, (GAME_HEIGHT)*ONE_BLOCK);
		head.draw(g);
		food.draw(g);
		if(isOver)this.draw(g);
	}

}
