package tetris;

public class GameValue {

	private final int FastDown = 75;
	private static final int MAXSPEED = 1000;
	private static final int TERM = 20;

	private int speed;
	private int check;
	private int level;
	private int combo;
	private int score;

	public GameValue() {
		this.check = 0;
		this.score = 0;
		this.level = 0;
		this.combo = 0;
	}

	public void comboUp() {
		combo++;
	}

	public void reset() {
		this.check = 0;
		this.level = 0;
		this.level = 0;
	}

	public void checkUp() {
		check++;
	}

	public void update() {
		if (check > 100) {
			speed = MAXSPEED - TERM * 40;
			level = 5;
		} else if (check > 60) {
			speed = MAXSPEED - TERM * 30;
			level = 4;
		} else if (check > 40) {
			speed = MAXSPEED - TERM * 20;
			level = 3;
		} else if (check > 20) {
			speed = MAXSPEED - TERM * 10;
			level = 2;
		} else {
			speed = MAXSPEED;
			level = 1;
		}

		int com_temp = 0;
		if (combo > 0) {
			com_temp = level;
			for (int i = 0; i < combo - 1; i++) {
				com_temp *= level + 1;
			}
		}
		score += com_temp * 10;
		combo = 0;
	}
	//±èÁ¾ºó ¹Ùº¸ ¸ÛÃ»ÀÌ ÇØ»ï ¸»¹ÌÀß 
	public void fast() {
		this.speed = FastDown;
	}

	public int getSpeed() {
		return this.speed;
	}

	public int getCheck() {
		return this.check;
	}

	public int getLevel() {
		return this.level;
	}

	public int getScore() {
		return this.score;
	}
}
