package Managers;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;

import Floors.BasicFloor;
import GameObjects.Bullet;
import GameObjects.Enemy;
import GameObjects.GameObject;
import GameObjects.Tank;

public class ObjectManager {

	// 싱글톤패턴
	private static ObjectManager om = new ObjectManager();

	public static ObjectManager getInstance() {
		return om;
	}

	// 본코드
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private ArrayList<BasicFloor> floors = new ArrayList<BasicFloor>();
	private ArrayList<Tank> tanks = new ArrayList<Tank>();
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();

	private ObjectManager() {

	}

	// 맵으로 부터 지형을 받는 메소드 또는 받아주는 매소드
	public void setFloors(ArrayList<BasicFloor> floors) {
		this.floors.clear();
		this.floors.addAll(floors);
	}

	public void setTanks(ArrayList<Tank> tanks) {
		this.tanks.clear();
		this.tanks.addAll(tanks);
	}

	public void setEnemies(ArrayList<Enemy> enemies) {
		this.enemies.clear();
		this.enemies.addAll(enemies);
	}

	public void addBullet(Bullet obj) {
		this.bullets.add(obj);
	}

	// 탱크를 사용하기위한 불러오기 함수
	public Tank getTank(int p) {
		for (int i = 0; i < tanks.size(); i++) {
			if (tanks.get(i).getPlayer() == p) {
				return tanks.get(i);
			}
		}
		System.out.println("not tank");
		return null;
	}

	public synchronized void draw(Graphics2D g2d) {
		for (Enemy o : enemies) {
			o.draw(g2d);
		}
		for (Bullet b : bullets) {
			b.draw(g2d);
		}
		for (BasicFloor f : floors) {
			f.draw(g2d);
		}
		for (Tank t : tanks) {
			t.draw(g2d);
		}
	}

	public void update() {
		for (Enemy o : enemies) {
			o.update();
		}
		for (Tank t : tanks) {
			t.update();
		}
		for (Bullet b : bullets) {
			b.update();
		}
	}

	public void removeObject() {
//		for (Enemy o : enemies) {
//			if(o.isDead) {
//				enemies.remove(o);
//			}
//		}
//		for (Tank t : tanks) {
//			if(t.isDead) {
//				tanks.remove(t);
//			}
//		}
//		for (Bullet b : bullets) {
//			if(b.isDead) {
//				bullets.remove(b);
//			}
//		}
		
		
		Iterator<Bullet> iterB = bullets.iterator();
		Iterator<Tank> iterT = tanks.iterator();
		Iterator<Enemy> iterE = enemies.iterator();
		
			while (iterB.hasNext()) {
				Bullet tempObj = iterB.next();
				if (tempObj.isDead) {
					iterB.remove();
				}
			}
			while (iterT.hasNext()) {
				Tank tempObj = iterT.next();
				if (tempObj.isDead) {
					iterT.remove();
				}
			}
			while (iterE.hasNext()) {
				Enemy tempObj = iterE.next();
				if (tempObj.isDead) {
					iterE.remove();
				}
			}
	}

	public void removeAll() {
		tanks.clear();
		enemies.clear();
		floors.clear();
		bullets.clear();
	}

	// 외부 처리 메소드
	// 추락하고 있는지 판정해주는 함수
	public boolean checkBottom(GameObject obj) {
		for (BasicFloor f : floors) {
			if (f.getBox().intersects(obj.getBoxs(GameObject.BOT)) && f.getBlocked() == true
					&& f.getBox().intersects(obj.getBoxs(GameObject.CENTER))) {
				return true;
			}

		}
		return false;
	}

	public boolean checkTop(GameObject obj) {
		for (BasicFloor f : floors) {
			if (f.getBox().intersects(obj.getBoxs(GameObject.TOP)) && f.getBlocked() == true
					&& f.getBox().intersects(obj.getBoxs(GameObject.CENTER))) {
				return true;
			}

		}
		return false;
	}

	public boolean checkRight(GameObject obj) {
		for (BasicFloor f : floors) {
			if (f.getBox().intersects(obj.getBoxs(GameObject.RIGHT)) && f.getBlocked() == true
					&& f.getBox().intersects(obj.getBoxs(GameObject.CENTER))) {
				return true;
			}

		}
		return false;
	}

	public boolean checkLeft(GameObject obj) {
		for (BasicFloor f : floors) {
			if (f.getBox().intersects(obj.getBoxs(GameObject.LEFT)) && f.getBlocked() == true
					&& f.getBox().intersects(obj.getBoxs(GameObject.CENTER))) {
				return true;
			}

		}
		return false;
	}

	public boolean checkBaseLine(GameObject obj) {
		for (BasicFloor f : floors) {
			if (f.getBox().intersectsLine(obj.getBaseLine())) {
				return true;
			}
		}
		return false;
	}

	public boolean checkCenter(GameObject obj) {
		for (BasicFloor f : floors) {
			if (f.getBox().intersects(obj.getBoxs(GameObject.CENTER)) && f.getBlocked() == true) {
				return true;
			}
		}
		return false;
	}
}
