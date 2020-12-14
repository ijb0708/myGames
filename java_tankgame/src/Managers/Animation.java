
package Managers;

import java.awt.image.BufferedImage;

public class Animation {
	public boolean isOnce;
	private boolean isFirst;
	
	public boolean isRun;
	public boolean isFinish;
	public boolean isStart;

	private BufferedImage[] frames;

	private int curFrame;

	private double startTime;
	private double delay;

	public Animation(BufferedImage[] frames, double delay, boolean isOnce) {
		this.frames = frames;
		this.delay = delay;
		this.isOnce = isOnce;
		isStart=false;
		isFinish=false;
		isFirst = true;
		curFrame = 0;
	}

	public Animation(BufferedImage[][] frames, int player, double delay, boolean isOnce) {
		for (int i = 0; i < frames[player].length; i++) {
			this.frames[i] = frames[player][i];
		}

		this.delay = delay;
		this.isOnce = isOnce;
		isFirst = true;
		curFrame = 0;
	}

	public void start() {
		if (isFirst) {
			startTime = System.nanoTime();
			isStart=true;
			isFinish=false;
			isRun = true;
			isFirst = false;
		}
	}

	public void end() {
		isRun = false;
		isFirst = true;
		
	}

	public BufferedImage getFrame() {
		if (isRun) {
			double endTime = System.nanoTime();
			if ((endTime - startTime) >= delay) {
				startTime = System.nanoTime();
				curFrame++;
			}
			if (curFrame == frames.length) {
				curFrame = 0;
				if (isOnce) {
					isFinish=true;
					isRun = false;
				}
			}
		}
		return frames[curFrame];
	}
}
