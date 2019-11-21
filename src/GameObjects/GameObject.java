package GameObjects;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import Managers.ImageLoader;
import Managers.ObjectManager;

public class GameObject {

	public static final int TOP = 0, RIGHT = 1, BOT = 2, LEFT = 3;

	protected ImageLoader il = ImageLoader.getInstance();
	protected ObjectManager om = ObjectManager.getInstance();

	protected Line2D[] Line = new Line2D[4];
	protected Rectangle2D box;

	protected double mapX, mapY;
	protected double fallingSpeed;
	protected int dx, dy;
	protected int width, height;
	protected boolean isRight, isLeft, isMoving, isFalling;
	protected boolean isRside;

	public GameObject(int x, int y, int width, int height) {
		this.dx = x;
		this.dy = y;
		this.mapX = x;
		this.mapY = y;
		this.width = width;
		this.height = height;
		this.fallingSpeed=2.5;
		this.isRside=true;

		//위 아래 왼쪽 오른쪽
		Line[TOP] = new Line2D.Double(mapX, mapY, mapX + (double) width, mapY);
		Line[BOT] = new Line2D.Double(mapX, mapY + (double) height, mapX + (double) width, mapY + (double) height);
		Line[LEFT] = new Line2D.Double(mapX, mapY, mapX, mapY + (double) height - 100);
		Line[RIGHT] = new Line2D.Double(mapX + (double) width, mapY, mapX + (double) width, mapY+ (double) height - 100);
		
		box = new Rectangle2D.Double(mapX, mapY, (double) width, (double) height);
		
		isRight=isLeft=isMoving=isFalling=false;
		
	}

	public void draw(Graphics2D g2d) {

	}

	public void update() {
	}
	
	protected void falling() {
		
		if(om.checkfalling(this) ) {
			isFalling=false;
		}else {
			isFalling=true;
		}
		
		if(isFalling) {
			mapY+=fallingSpeed;
		}
	}

	protected void updateLine() {
		Line[TOP].setLine(mapX, mapY, mapX + (double) width, mapY);
		Line[BOT].setLine(mapX, mapY + (double) height, mapX + (double) width, mapY + (double) height);
		Line[LEFT].setLine(mapX, mapY, mapX, mapY + (double) height - 2);
		Line[RIGHT].setLine(mapX + (double) width, mapY, mapX + (double) width, mapY+ (double) height - 2);
	}
	
	protected void updateRect() {
		box.setRect(mapX, mapY, (double) width, (double) height);
		
	}
	
	public Line2D getLine(int dir) {
		return Line[dir];
	}

	protected boolean checkTile() {
		return true;
	}
	
}
