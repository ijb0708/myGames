package GamePages;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import GameObjects.Tank;
import Managers.GameManager;
import Managers.ImageLoader;
import Managers.MapMaker;
import Managers.ObjectManager;

public class SinglePage extends PageAdapter{
	
	private ImageLoader il = ImageLoader.getInstance();
	private ObjectManager om = ObjectManager.getInstance();
	
	private BufferedImage back= il.getBackground_mountain();
	
	private GameManager gm;
	private MapMaker mm;
	
	private Tank tank;
	
	public SinglePage(GameManager gm) {
		this.gm=gm;
		tank = new Tank(50, 50, 0);
		
		mm = new MapMaker(0);
		mm.make();
		
		om.setEnemies(mm.getEnemies());
		om.setFloors(mm.getFloors());
		om.setTanks(mm.getTanks());
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.drawImage(back, 0, 0, GAME_WIDTH, GAME_HEIGHT, null);
		om.draw(g2d);
	}

	@Override
	public void init() {
	}

	@Override
	public void update() {
		om.getTank(1).update();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_RIGHT :
			om.getTank(1).right();
			break;
		case KeyEvent.VK_LEFT :
			om.getTank(1).left();
			break;
		case KeyEvent.VK_Z:
			om.getTank(1).jump();
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		switch(e.getKeyCode()) {
		case KeyEvent.VK_RIGHT :
			om.getTank(1).notRight();
			break;
		case KeyEvent.VK_LEFT :
			om.getTank(1).notLeft();
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
