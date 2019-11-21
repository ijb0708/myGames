package Managers;

import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import GamePages.InsertPage;
import GamePages.MainPage;
import GamePages.SinglePage;
import GamePages.PageAdapter;
import GamePages.PageInterface;

public class GameManager extends KeyAdapter implements PageInterface {
	
	public GameManager() {
		pages.add(new MainPage(this));
		pages.add(new InsertPage(this));
		pages.add(new SinglePage(this));
	}
	
	// º» ÄÚµå
	private ArrayList<PageAdapter> pages = new ArrayList();
	private int nowSelect=0;
	
	public String name;
	
	
	public void setPage(int now) {
		pages.get(nowSelect).init();
		nowSelect=now;
	}

	@Override
	public void draw(Graphics2D g2d) {
		pages.get(nowSelect).draw(g2d);
	}

	@Override
	public void update() {
		pages.get(nowSelect).update();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		pages.get(nowSelect).keyPressed(e);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		pages.get(nowSelect).keyReleased(e);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		pages.get(nowSelect).keyTyped(e);
	}

	@Override
	public void init() {
		pages.get(nowSelect).init();
	}
}
