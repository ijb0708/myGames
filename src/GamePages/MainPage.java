package GamePages;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import Managers.GameManager;
import Managers.ImageLoader;

public class MainPage extends PageAdapter {
	
	private ImageLoader il = ImageLoader.getInstance();

	private Font bigFont = new Font("±¼¸²", Font.BOLD, 35);
	private Font smallFont = new Font("±¼¸²", Font.BOLD, 20);
	
	private int selecter=0;
	private String[] name = new String[] {
			"sIngle Play", "mUlti Play", "eXit"
	};
	
	private GameManager gm;
	
	public MainPage(GameManager gm) {
		this.gm= gm;
	}
	
	@Override
	public void draw(Graphics2D g2d) {
		
		g2d.drawImage(il.getBackground_black(), 0, 0, GAME_WIDTH, GAME_HEIGHT, null);
		
		g2d.setColor(Color.GREEN);
		g2d.setFont(bigFont);
		g2d.drawString("tank GAme", 35, 50);
		g2d.setFont(smallFont);
		
		for (int i=0; i<3; i++) {
			if(i == selecter) {
				g2d.setColor(Color.RED);
			}else {
				g2d.setColor(Color.BLUE);
			}
			g2d.drawString(name[i], 45, 100+i*30);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_UP:
			selecter--;
			if(selecter==-1) {
				selecter=2;
			}
			break;
		case KeyEvent.VK_DOWN:
			selecter++;
			if(selecter==3) {
				selecter=0;
			}
			break;
		case KeyEvent.VK_SPACE:
		case KeyEvent.VK_ENTER:
			if(selecter==0) {
				gm.setPage(1);
			}else if(selecter==1) {
				
			}else if(selecter==2) {
				System.exit(0);
			}
			break;
		}
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

}
