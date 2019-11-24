package Managers;

import java.awt.image.BufferedImage;

public class Animation {
	public boolean isOnce;
	private boolean isRun;
	private boolean isFirst;
	
	private BufferedImage[] frames;
	
	private int curFrame;
	
	private double startTime;
	private double delay;
	
	public Animation(BufferedImage[] frames, double delay) {
		this.frames=frames;
		this.delay=delay;
		isFirst=true;
		curFrame=0;
	}
	
	public Animation(BufferedImage[][] frames, int player, double delay) {
		for (int i=0; i<frames[player].length; i++) {
			this.frames[i] = frames[player][i];
		}
		
		this.delay=delay;
		isFirst=true;
		curFrame=0;
	}
	
	public void start() {
		if(isFirst) {
			startTime = System.nanoTime();
			isRun=true;
			isFirst=false;
		}
	}
	
	public void end() {
		isRun=false;
		isFirst=true;
	}
	
	public BufferedImage getFrame() {
		if(isRun) {
		double endTime = System.nanoTime();
		if((endTime - startTime)>= delay) {
			startTime = System.nanoTime();
			curFrame++;
		}
		if(curFrame==frames.length) {
			curFrame=0;
		}
		}
		return frames[curFrame];
	}
}
