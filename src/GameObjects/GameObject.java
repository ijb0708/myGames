package GameObjects;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import GamePages.PageAdapter;
import Managers.Animation;
import Managers.ImageLoader;
import Managers.ObjectManager;

public class GameObject {

	// 히트박스 상태 정리
	public static final int CENTER=0, TOP = 1, RIGHT = 2, BOT = 3, LEFT = 4;
	protected final double BOXTERM = 20;
	
	// 불러오는 매니져들
	protected ImageLoader il = ImageLoader.getInstance();
	protected ObjectManager om = ObjectManager.getInstance();

	// 히트박스들
	protected Rectangle2D[] boxs = new Rectangle2D[5];
	protected Rectangle2D hitBox;
	protected Line2D baseLine;

	// 계산시 사용되는 좌표
	protected double mapX, mapY;
	protected double fallingSpeed;
	
	//출력적인 부분에서 사용되어지는 주소
	protected int base;
	protected int dx, dy;
	protected int width, height;
	
	//움직이고 있는 방향의 여부
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
		boxs[TOP] = new Rectangle2D.Double(mapX + 3, mapY - BOXTERM, (double) width - 6, (double) BOXTERM);
		boxs[BOT] = new Rectangle2D.Double(mapX + 3, mapY + height, (double) width - 6, (double) BOXTERM); 
		boxs[LEFT] = new Rectangle2D.Double(mapX - BOXTERM, mapY + 2, (double) BOXTERM, (double) height - 4); 
		boxs[RIGHT] = new Rectangle2D.Double(mapX + width, mapY + 2, (double) BOXTERM, (double) height - 4); 
		boxs[CENTER] = new Rectangle2D.Double(mapX, mapY, (double) width, (double) height); 
		
		baseLine = new Line2D.Double(mapX, mapY, mapX + width, mapY);
		
		isRight=isLeft=isMoving=isFalling=false;
		
	}
	
	//오버라이드를 위한 메소드
	public void draw(Graphics2D g2d) {

	}

	public void update() {
	}

	//상자를 지속적으로 업데이트하기위한 메소드
	protected void updateBoxs() {
		boxs[TOP].setRect(mapX + 3, mapY - BOXTERM, (double) width - 6, (double) BOXTERM);
		boxs[BOT].setRect(mapX + 3, mapY + height, (double) width - 6, (double) BOXTERM);
		boxs[LEFT].setRect(mapX - BOXTERM, mapY + 3, (double) BOXTERM, (double) height - 6);
		boxs[RIGHT].setRect(mapX + width, mapY + 3, (double) BOXTERM, (double) height - 6);
		boxs[CENTER].setRect(mapX, mapY, (double) width, (double) height);
		
		for (double i= 0; i<PageAdapter.GAME_HEIGHT; i++) {
				baseLine.setLine(mapX + 4, mapY + i, mapX + width - 4, mapY + i);
			if(om.checkBaseLine(this)) {
				base=(int) (mapY - height + i);
				break;
			}
		}
	}
	
	// getter, setter
	public Rectangle2D getBoxs(int dir) {
		return boxs[dir];
	}
	
	public Line2D getBaseLine() {
		return baseLine;
	}
}
