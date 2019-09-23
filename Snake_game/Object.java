package Snake_game;

public class Object {
	protected int x;
	protected int y;
	
	protected static final int ONE_BLOCK = 25;
	
	public Object(int objectX, int objectY) {
		// TODO Auto-generated constructor stub
		x=objectX;
		y=objectY;
	}

	void setXY (int x, int y) {
		x=this.x;
		y=this.y;
	}
	
	int getX () { return x; }

	int getY () { return y; }
}

