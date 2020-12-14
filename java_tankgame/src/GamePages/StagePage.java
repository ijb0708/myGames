package GamePages;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import Managers.GameManager;
import Managers.ImageLoader;
import Managers.MapMaker;
import Managers.ObjectManager;
import Managers.ValuesManager;

public class StagePage extends PageAdapter{
	
	private ImageLoader il = ImageLoader.getInstance();
	private ObjectManager om = ObjectManager.getInstance();
	private ValuesManager vm = ValuesManager.getInstance();
	
	private BufferedImage back= il.getBackground_black();
	
	private GameManager gm;
	private MapMaker mm;
	
	private Font bigFont = new Font("±¼¸²", Font.BOLD,40);
	
	public StagePage(GameManager gm) {
		this.gm=gm;
	}

	@Override
	public void init() {
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.drawImage(back, 0, 0, PageAdapter.GAME_WIDTH, PageAdapter.GAME_WIDTH, null);
		g2d.setFont(bigFont);
		g2d.drawString("stage" + vm.getStage(), PageAdapter.GAME_WIDTH/4, PageAdapter.GAME_HEIGHT/3);
		g2d.drawString("press Enter", PageAdapter.GAME_WIDTH/4, PageAdapter.GAME_HEIGHT/3 + 80);
	}

	@Override
	public void update() {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			gm.setPage(1);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
