package Managers;

import java.awt.Graphics2D;
import java.util.ArrayList;

import Floors.BasicFloor;
import GameObjects.Bullet;
import GameObjects.Enemy;
import GameObjects.GameObject;
import GameObjects.Tank;

public class ObjectManager {
	
	//�̱�������
	private static ObjectManager om = new ObjectManager();
	public static ObjectManager getInstance() {
		return om;
	}
	
	//���ڵ�
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private ArrayList<BasicFloor> floors = new ArrayList<BasicFloor>();
	private ArrayList<Tank> tanks = new ArrayList<Tank>();
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	
	private ArrayList<GameObject> removes = new ArrayList<GameObject>();
	
	private ObjectManager() {
		
	}
	
	// ������ ���� ������ �޴� �޼ҵ�  �Ǵ� �޾��ִ� �żҵ�
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
	
	
	//��ũ�� ����ϱ����� �ҷ����� �Լ�
	public Tank getTank(int p) {
		for (int i=0; i<tanks.size(); i++) {
			if(tanks.get(i).getPlayer()==p) {
				return tanks.get(i);
			}
		}
		System.out.println("not tank");
		return null;
	}
	
	public void draw(Graphics2D g2d) {
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
		removesAll();
	}
	
	public void addRemoves(GameObject obj) {
		try {
			removes.add(obj);
		}catch(Exception e) {
			e.fillInStackTrace();
		}
	}
	
	public void removesAll() {
		try {
			bullets.removeAll(removes);
			removes.clear();
		}catch(Exception e) {
			e.fillInStackTrace();
		}
	}
	
	//���� �żҵ�
	public void removeFloors(BasicFloor obj) {
		floors.remove(obj);
	}
	
	public void removeFloors(int i) {
		floors.remove(i);
	}
	
	public void removeEnemy(Enemy obj) {
		enemies.remove(obj);
	}
	
	public void removeTank(Tank obj) {
		tanks.remove(obj);
	}
	
	public void removeBullet(Bullet obj) {
		bullets.remove(obj);
	}
	
	public void removeBullet(int i) {
		bullets.remove(i);
	}
	
	public void removeAll() {
		tanks.clear();
		enemies.clear();
		floors.clear();
		bullets.clear();
	}
	
	//�ܺ� ó�� �޼ҵ�
	//�߶��ϰ� �ִ��� �������ִ� �Լ�
	public boolean checkBottom(GameObject obj) {
		for(BasicFloor f : floors) {
			if(f.getBox().intersects(obj.getBoxs(GameObject.BOT))&&f.getBlocked()==true
					&&f.getBox().intersects(obj.getBoxs(GameObject.CENTER))) {
				return true;
			}
			
		}
		return false;
	}

	public boolean checkTop(GameObject obj) {
		for(BasicFloor f : floors) {
			if(f.getBox().intersects(obj.getBoxs(GameObject.TOP))&&f.getBlocked()==true
					&&f.getBox().intersects(obj.getBoxs(GameObject.CENTER))) {
				return true;
			}
			
		}
		return false;
	}

	public boolean checkRight(GameObject obj) {
		for(BasicFloor f : floors) {
			if(f.getBox().intersects(obj.getBoxs(GameObject.RIGHT))&&f.getBlocked()==true
					&&f.getBox().intersects(obj.getBoxs(GameObject.CENTER))) {
				return true;
			}
			
		}
		return false;
	}
	
	
	public boolean checkLeft(GameObject obj) {
		for(BasicFloor f : floors) {
			if(f.getBox().intersects(obj.getBoxs(GameObject.LEFT))&&f.getBlocked()==true
					&&f.getBox().intersects(obj.getBoxs(GameObject.CENTER))) {
				return true;
			}
			
		}
		return false;
	}
	
	public boolean checkBaseLine(GameObject obj) {
		for(BasicFloor f : floors) {
			if(f.getBox().intersectsLine(obj.getBaseLine())) {
				return true;
			}
		}
		return false;
	}
	
	public boolean checkCenter(GameObject obj) {
		for (BasicFloor f : floors) {
			if(f.getBox().intersects(obj.getBoxs(GameObject.CENTER))&&f.getBlocked()==true) {
				return true;
			}
		}
		return false;
	}
}
