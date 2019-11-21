package Managers;

import java.awt.Graphics2D;
import java.util.ArrayList;

import Floors.BasicFloor;
import GameObjects.Enemy;
import GameObjects.GameObject;
import GameObjects.Tank;

public class ObjectManager {
	
	//싱글톤패턴
	private static ObjectManager om = new ObjectManager();
	public static ObjectManager getInstance() {
		return om;
	}
	
	//본코드
	private ArrayList<Enemy> enemies = new ArrayList();
	private ArrayList<BasicFloor> floors = new ArrayList();
	private ArrayList<Tank> tanks = new ArrayList();
	
	private ObjectManager() {
		
	}
	
	// 맵으로 부터 지형을 받는 메소드
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
	
	
	//탱크를 사용하기위한 불러오기 함수
	public Tank getTank(int p) {
		for (int i=0; i<tanks.size(); i++) {
			if(tanks.get(i).getPlayer()==p) {
				return tanks.get(i);
			}
		}
		System.out.println("not tank");
		return null;
	}
	
	public void removeEnemy(int i) {
		enemies.remove(i);
	}
	
	public void draw(Graphics2D g2d) {
		for (Enemy o : enemies) {
			o.draw(g2d);
		}
		for (BasicFloor f : floors) {
			f.draw(g2d);
		}
		for (Tank t : tanks) {
			t.draw(g2d);
		}
	}
	//obj.getLine(GameObject.BOT))&&f.getBlocked()==true
	//추락하고 있는지 판정해주는 함수
	public boolean checkfalling(GameObject obj) {
		for(BasicFloor f : floors) {
			if(f.getBox().intersectsLine(obj.getLine(GameObject.BOT))&&f.getBlocked()==true) {
				return true;
			}
		}
		return false;
	}
	
	//추락시 양옆으로 충돌판정
	public boolean checkRight(GameObject obj) {
		for(BasicFloor f : floors) {
			if(f.getBox().intersectsLine(obj.getLine(GameObject.RIGHT))&&f.getBlocked()==true) {
				return true;
			}
		}
		return false;
	}
	
	//평시 양옆으로 충동판정
	public boolean checkLeft(GameObject obj) {
		for(BasicFloor f : floors) {
			if(f.getBox().intersectsLine(obj.getLine(GameObject.LEFT))&&f.getBlocked()==true) {
				return true;
			}
		}
		return false;
	}
	
}
