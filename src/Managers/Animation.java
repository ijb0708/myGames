package Managers;

import java.awt.image.BufferedImage;

public class Animation {
	private ImageLoader il = ImageLoader.getInstance();
	private double startTime;
	private boolean isOnce;
	private int curFrame;
	private double delay;
	private BufferedImage frameO[];
	private BufferedImage frameT[][];
	private GameTimer t;
	
	public Animation() {
		isOnce=true;
		t= new GameTimer();
	}
	
	public void setDelay(double delay) {
		t.setDelay(delay);
	}
	
	public void setFrames (BufferedImage frame[]) {
		
	}
	
	public void setFrames (BufferedImage frame[][], int p) {
		
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
