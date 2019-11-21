package Managers;

import java.awt.image.BufferedImage;

public class Animation {
	private ImageLoader il = ImageLoader.getInstance();
	private double startTime;
	private boolean isOnce;
	private Timer t;
	
	public Animation() {
		isOnce=true;
		t= new Timer();
	}
	
	public void setDelay(double delay) {
		t.setDelay(delay);
	}
	
	public BufferedImage getTankRight(int p) {
		return il.getBackground_black();
	}
	
	public BufferedImage getTankleft(int p) {
		return il.getBackground_black();
	}	
	public BufferedImage getTankRightFire(int p) {
		return il.getBackground_black();
	}
	
	public BufferedImage getTankleftFire(int p) {
		return il.getBackground_black();
	}
}
