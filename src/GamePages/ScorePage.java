package GamePages;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Managers.DBManager;
import Managers.GameManager;
import Managers.ImageLoader;
import Managers.scoreBean;

public class ScorePage extends PageAdapter{
	
	private ImageLoader il = ImageLoader.getInstance();
	private DBManager dm = DBManager.getInstance();
	
	private BufferedImage back = il.getBackground_black();
	private GameManager gm;
	
	private ArrayList<scoreBean> scores = new ArrayList<scoreBean>();

	public ScorePage(GameManager gm) {
		this.gm=gm;
	}
	
	@Override
	public void draw(Graphics2D g2d) {
		g2d.setFont(new Font("±¼¸²", Font.BOLD, 25));
		g2d.setColor(Color.white);
		g2d.drawImage(back, 0, 0, PageAdapter.GAME_WIDTH, PageAdapter.GAME_HEIGHT, null);
		for (int i=0; i<10; i++) {
			if(scores.size()==i) {
				break;
			}
			g2d.drawString(i+1 + "st  " + scores.get(i).getName() + "    " + scores.get(i).getStage(), PageAdapter.GAME_WIDTH/4, PageAdapter.GAME_HEIGHT/12 + i * 40);
		}
	}

	@Override
	public void init() {
		scores.clear();
		scores.addAll(dm.seletScore());
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ESCAPE) {
			gm.setPage(0);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
