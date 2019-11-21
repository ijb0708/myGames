package Managers;

public class Timer {
	private double startTime;
	private double delay;
	
	public void setDelay(double delay) {
		this.delay=delay;
	}
	
	public void start() {
		startTime=System.nanoTime();
	}
	
	public double getCurrent() {
		double endTime = System.nanoTime();
		
		return endTime-startTime;
	}
	
	public boolean check() {
		double endTime = System.nanoTime();
		if((endTime-startTime)>=delay) {
			return true;
		}
		return false;
	}
}
