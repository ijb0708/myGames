package Floors;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Managers.ImageLoader;

public class BasicFloor {

	public static final int SIZE = 50;

	protected int dx, dy;

	protected boolean isBlocked;

	protected ImageLoader il = ImageLoader.getInstance();
	protected BufferedImage sprite;

	protected Rectangle box;

	public BasicFloor(int x, int y) {
		this.dx = x;
		this.dy = y;
		box = new Rectangle(x, y, SIZE, SIZE);
		isBlocked=false;
	}
	
	public Rectangle getBox() {
		return box;
	}
	
	public boolean getBlocked() {
		return isBlocked;
	}


	public void draw(Graphics2D g2d) {
		g2d.drawImage(sprite, dx, dy, SIZE, SIZE, null);
	}
}
