package tetris;

public class Stick {
	private int x;
	private int y;
	private int dir;
	private int shape;

	public Stick(int x, int y) {
		this.x = x;
		this.y = y;
		this.shape = (int) (Math.random() * 5);
		this.dir = (int) (Math.random() * 4);
	}

	public void reset() {
		this.x=4;
		this.y=0;
		this.shape = (int) (Math.random() * 5);
		this.dir = (int) (Math.random() * 4);
	}
	
	public void reset(Stick stick) {
		this.x=4;
		this.y=0;
		this.shape=stick.getShape();
		this.dir=stick.getDir();
	}

	public void turnRight() {
		this.dir = dir + 1 == 4 ? 0 : dir + 1;
	}

	public void turnLeft() {
		this.dir = dir - 1 == -1 ? 3 : dir - 1;
	}

	public void right(int in) {
		this.x += in;
	}

	public void left(int in) {
		this.x -= in;
	}

	public void down(int in) {
		this.y += in;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getShape() {
		return shape;
	}

	public int getDir() {
		return dir;
	}

	public int getRightDir() {
		return dir + 1 == 4 ? 0 : dir + 1;
	}

	public int getLeftDir() {
		return dir - 1 == -1 ? 3 : dir - 1;
	}

}
