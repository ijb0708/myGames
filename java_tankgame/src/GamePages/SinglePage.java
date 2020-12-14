package GamePages;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import GameObjects.Tank;
import Managers.DBManager;
import Managers.GameManager;
import Managers.ImageLoader;
import Managers.MapMaker;
import Managers.ObjectManager;
import Managers.ValuesManager;

public class SinglePage extends PageAdapter{
	
	private ImageLoader il = ImageLoader.getInstance();
	private ObjectManager om = ObjectManager.getInstance();
	private ValuesManager vm = ValuesManager.getInstance();
	
	private BufferedImage back= il.getBackground_mountain();
	
	private GameManager gm;
	private MapMaker mm;
	
	private boolean isRun;
	
	public SinglePage(GameManager gm) {
		this.gm=gm;

	}

	@Override
	public void init() {
		
		mm = new MapMaker( (int) (Math.random()*5)+1);
		
		om.setEnemies(mm.getEnemies());
		om.setFloors(mm.getFloors());
		om.setTanks(mm.getTanks());
		
		isRun=true;
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.drawImage(back, 0, 0, GAME_WIDTH, GAME_HEIGHT, null);
		g2d.setColor(Color.BLACK);
		g2d.drawRect(10, 10, 300, 30);
		if(om.isTank(1)) {
			g2d.setColor(Color.RED);
			g2d.fillRect(10, 10,(int) (300 * ((double)om.getTank(1).getCurHealth() /(double) om.getTank(1).getMaxHealth())), 30);
		}
		om.draw(g2d);
	}

	@Override
	public void update() {
		om.update();
		om.removeObject();
		
		if(!om.isTank(1)&&isRun) {
			
			isRun=false;
			gm.setPage(2);
		}
		
		if(!om.isEnemy()&&isRun) {
			isRun=false;
			vm.upStage();
			gm.setPage(3);
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		om.getTank(1).pressKey(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		om.getTank(1).releasKey(e);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
