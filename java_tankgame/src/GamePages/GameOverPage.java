package GamePages;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import Managers.DBManager;
import Managers.GameManager;
import Managers.ImageLoader;
import Managers.ValuesManager;

public class GameOverPage extends PageAdapter {

	private ImageLoader il = ImageLoader.getInstance();
	private DBManager dm = DBManager.getInstance();
	private ValuesManager vm = ValuesManager.getInstance();

	private Font bigFont = new Font("굴림", Font.BOLD, 60);
	private Font smallFont = new Font("굴림", Font.BOLD, 20);

	private GameManager gm;

	private String id="";
	
	private String[] name = new String[] {
			"rePlay", "Main"
	};
	
	private int selecter =0;

	public GameOverPage(GameManager gm) {
		this.gm = gm;
	}

	@Override
	public void draw(Graphics2D g2d) {

		g2d.drawImage(il.getBackground_black(), 0, 0, GAME_WIDTH, GAME_HEIGHT, null);

		g2d.setFont(bigFont);
		g2d.setColor(Color.white);
		g2d.drawString("gAme oVer", GAME_WIDTH/3, 100);
		
		g2d.setFont(smallFont);
		g2d.drawString("insert your ID", GAME_WIDTH/3, 180);
		g2d.drawRect(GAME_WIDTH/3, 200, 200, 24);
		g2d.drawString(id, GAME_WIDTH/3+5, 220);

		for (int i=0; i<2; i++) {
			if(i == selecter) {
				g2d.setColor(Color.RED);
			}else {
				g2d.setColor(Color.WHITE);
			}
			g2d.drawString(name[i], PageAdapter.GAME_WIDTH/3 + 10, 300+i*30);
		}
	}

	@Override
	public void init() {
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_ENTER:
			// 데이터베이스로 로직처리 그러나 일단 생략
			
			if (!id.equals("")) {
				dm.insertScore(id, 0, vm.getStage(), 0);
			}
			vm.resetStage();
			
			if(selecter==0) {
				gm.setPage(1);
			}else if(selecter==1) {
				gm.setPage(0);
			}
			break;
		case KeyEvent.VK_BACK_SPACE:
			if (!id.equals("")) {
				id = id.substring(0, id.length() - 1);
			}
			break;
		case KeyEvent.VK_UP :
			selecter--;
			if (selecter==-1) {
				selecter=1;
			}
			break;
		case KeyEvent.VK_DOWN :
			selecter++;
			if (selecter==2) {
				selecter=0;
			}
			break;
		case KeyEvent.VK_RIGHT :
		case KeyEvent.VK_LEFT :
			break;
		default:
			if (id.length() <= 5) {
				id += e.getKeyChar();
			}
			break;
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
