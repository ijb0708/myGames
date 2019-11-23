package GameObjects;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import GamePages.PageAdapter;
import Managers.GameTimer;
import Managers.ImageLoader;
import Managers.ObjectManager;

public class GameObject {

	public static final int CENTER=0, TOP = 1, RIGHT = 2, BOT = 3, LEFT = 4;
	protected final double BOXTERM = 20;
	protected ImageLoader il = ImageLoader.getInstance();
	protected ObjectManager om = ObjectManager.getInstance();

	protected GameTimer gt;

	protected Rectangle2D[] boxs = new Rectangle2D[5];
	protected Rectangle2D hitBox;
	protected Line2D baseLine;

	protected int base;
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
		
		gt = new GameTimer();

		//위 아래 왼쪽 오른쪽
		boxs[TOP] = new Rectangle2D.Double(mapX, mapY - BOXTERM, (double) width, (double) BOXTERM);
		boxs[BOT] = new Rectangle2D.Double(mapX, mapY + height, (double) width, (double) BOXTERM); 
		boxs[LEFT] = new Rectangle2D.Double(mapX - BOXTERM, mapY, (double) BOXTERM, (double) height-2); 
		boxs[RIGHT] = new Rectangle2D.Double(mapX + width, mapY, (double) BOXTERM, (double) height-2); 
		boxs[CENTER] = new Rectangle2D.Double(mapX-1, mapY-1, (double) width+1, (double) height+1); 
		
		baseLine = new Line2D.Double(mapX, mapY, mapX + width, mapY);
		
		isRight=isLeft=isMoving=isFalling=false;
		
	}

	public void draw(Graphics2D g2d) {

	}

	public void update() {
	}

	protected void updateBoxs() {
		boxs[TOP].setRect(mapX + 3, mapY - BOXTERM, (double) width - 6, (double) BOXTERM);
		boxs[BOT].setRect(mapX + 3, mapY + height, (double) width - 6, (double) BOXTERM);
		boxs[LEFT].setRect(mapX - BOXTERM, mapY + 3, (double) BOXTERM, (double) height - 6);
		boxs[RIGHT].setRect(mapX + width, mapY + 3, (double) BOXTERM, (double) height - 6);
		boxs[CENTER].setRect(mapX, mapY, (double) width, (double) height);
		
		for (double i= 0; i<PageAdapter.GAME_HEIGHT; i++) {
				baseLine.setLine(mapX + 3, mapY + height + i, mapX + width - 3, mapY + height + i);
			if(om.checkBaseLine(this)) {
				base=(int) (mapY + i);
				break;
			}
		}
	}
	
	protected void updatehitBox() {
		
	}
	
	public Rectangle2D getBoxs(int dir) {
		return boxs[dir];
	}
	
	public Line2D getBaseLine() {
		return baseLine;
	}

	protected boolean checkTile() {
		return true;
	}
}
