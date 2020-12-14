package Floors;

public class Stone extends BasicFloor {

	public Stone(int x, int y) {
		super(x, y);
		sprite = il.getStone();
		isBlocked=true;
	}
}
