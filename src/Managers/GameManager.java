package Managers;

import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import GamePages.GameOverPage;
import GamePages.MainPage;
import GamePages.PageAdapter;
import GamePages.PageInterface;
import GamePages.ScorePage;
import GamePages.SinglePage;
import GamePages.StagePage;

public class GameManager extends KeyAdapter implements PageInterface {
	
	public GameManager() {
		pages.add(new MainPage(this));
		pages.add(new SinglePage(this));
		pages.add(new GameOverPage(this));
		pages.add(new StagePage(this));
		pages.add(new ScorePage(this));
	}
	
	// º» ÄÚµå
	private ArrayList<PageAdapter> pages = new ArrayList<PageAdapter>();
	private int nowSelect=0;
	
	public String name;
	
	public void setPage(int now) {
		nowSelect=now;
		pages.get(nowSelect).init();
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
