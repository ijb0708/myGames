package Managers;

import java.util.ArrayList;

import Floors.BasicFloor;
import Floors.Stone;
import GameObjects.Enemy;
import GameObjects.Tank;

public class MapMaker {

	private final int MAP_WIDTH = 24;
	private final int MAP_HEIGHT = 12;

	private T[][] use;

	// 기본적인 맵길이는 24, 12로 구성
	private T[][] ExamMap = new T[][] {
//  1    2    3    4    5    6    7    8    9   10   11   12   13   14   15   16   17   18   19   20   21   22   23   24
{ T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N }, // 1
{ T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N }, // 2
{ T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N }, // 3
{ T.S, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N }, // 4
{ T.S, T.N, T.N, T.N, T.S, T.S, T.S, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N }, // 5
{ T.S, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N }, // 6
{ T.S, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N }, // 7
{ T.S, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.S, T.S, T.S, T.S, T.S, T.S, T.N, T.N, T.N, T.N, T.N, T.N, T.N }, // 8
{ T.S, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.E, T.N, T.N, T.N, T.N, T.N, T.N }, // 9
{ T.S, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.S, T.S, T.S, T.S, T.S, T.S, T.N, T.N, T.N, T.N }, // 10
{ T.S, T.N, T.N,T.T1, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.N, T.S }, // 11
{ T.S, T.S, T.S, T.S, T.S, T.S, T.S, T.S, T.S, T.S, T.S, T.S, T.S, T.S, T.S, T.S, T.S, T.S, T.S, T.S, T.S, T.S, T.S, T.S }, // 12
	};

	private ArrayList<BasicFloor> floors = new ArrayList<BasicFloor>();
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private ArrayList<Tank> tanks = new ArrayList<Tank>();

	public MapMaker(int select) {
		if (select == 0) {
			use = ExamMap;
		} else {
			System.out.println("맵이 없습니다.");
		}

	}

	public void setMap(int select) {
		if (select == 0) {
			use = ExamMap;
		} else {
			System.out.println("맵이 없습니다.");
		}
	}

	public void make() {
		floors.clear();
		enemies.clear();
		tanks.clear();

		for (int i = 0; i < MAP_HEIGHT; i++) {
			for (int j = 0; j < MAP_WIDTH; j++) {
				switch (use[i][j]) {
				case S:
					floors.add(new Stone(j * BasicFloor.SIZE, i * BasicFloor.SIZE));
					break;
				case E:
					enemies.add(new Enemy(j * BasicFloor.SIZE, i * BasicFloor.SIZE));
					break;
				case T1:
					tanks.add(new Tank(j * BasicFloor.SIZE, i * BasicFloor.SIZE, 1));
					break;
				case T2:
					tanks.add(new Tank(j * BasicFloor.SIZE, i * BasicFloor.SIZE, 2));
					break;
				case T3:
					tanks.add(new Tank(j * BasicFloor.SIZE, i * BasicFloor.SIZE, 3));
					break;
				case T4:
					tanks.add(new Tank(j * BasicFloor.SIZE, i * BasicFloor.SIZE, 4));
					break;
				case N:
					break;
				}
			}
		}

		return;
	}

	public ArrayList<BasicFloor> getFloors() {
		return floors;
	}

	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}

	public ArrayList<Tank> getTanks() {
		return tanks;
	}
}

// N은 NONE , S는  Stone, T1는 tank1, 
enum T {
	N, S, E, T1, T2, T3, T4;
}
