package GameObjects;

public class Enemy extends GameObject{
	
	private static final int ENEMY_SIZE=50;

	public Enemy(int x, int y) {
		super(x, y, ENEMY_SIZE, ENEMY_SIZE);
	}
	
	
}
