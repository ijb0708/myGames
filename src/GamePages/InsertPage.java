package GamePages;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import Managers.GameManager;
import Managers.ImageLoader;

public class InsertPage extends PageAdapter {

	private ImageLoader il = ImageLoader.getInstance();

	private Font bigFont = new Font("굴림", Font.BOLD, 20);
	private Font smallFont = new Font("굴림", Font.BOLD, 10);

	private GameManager gm;

	public String id="";

	public InsertPage(GameManager gm) {
		this.gm = gm;
	}

	@Override
	public void draw(Graphics2D g2d) {

		g2d.drawImage(il.getBackground_black(), 0, 0, GAME_WIDTH, GAME_HEIGHT, null);

		g2d.setColor(Color.white);
		g2d.drawString("insert your ID", 15, 40);
		g2d.drawRect(10, 60, 200, 24);
		g2d.drawString(id, 15, 80);
		g2d.drawString("Press Enter Key", 15, 110);
		g2d.drawString("Back is ESC key", 15, 140);
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
			gm.setPage(2);
			break;
		case KeyEvent.VK_ESCAPE:
			gm.setPage(0);
			break;
		case KeyEvent.VK_BACK_SPACE:
			if (!id.equals("")) {
				id = id.substring(0, id.length() - 1);
				break;
			}
		default:
			if (id.length() <= 15) {
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
