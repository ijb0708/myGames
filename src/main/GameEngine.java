package main;

import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class GameEngine extends JPanel implements Runnable{
	
	private static final long serialVersionUID = 1L;

	private Thread thread;
	private BufferedImage bufferImg;
	
	private boolean isRun;
	
	public GameEngine() {
		
		thread= new Thread(this);
		thread.start();
		
		this.addKeyListener(null);
		this.setFocusable(true);
	}

	@Override
	public void run() {
		
		/*************************
		  	그래픽처리를 하는 쓰래드 
		**************************/
		
		//System.nanoTime 에서  1000000000 은 1초로 취급 즉 1/60을 의미
		final double updateMax= 60.0;
		final double oneSecond =1000000000;
		// TimeMillis에서 사용
		final double oneMinute=1000;
		
		//이전 초, 현재 초를 의미하는 변수
		long preT = System.nanoTime();
		long nowT ;
		
		//현재 시간을 분단위의 변수
		long MinT=System.currentTimeMillis();
		
		// 변화량에 관련된 변수
		double delta= 0;
		int updateCnt=0;
		int FPSCnt=0;
		
		// 실작동 부분
		while (true) {
			nowT= System.nanoTime();
			delta += (nowT - preT) * updateMax;
			preT = nowT;
			
			if(delta>=oneSecond) {
				//update부분
				updateCnt++;
				delta-=oneSecond;
			}
			
			//draw, render 그리는부분
			FPSCnt++;
			
			if(System.currentTimeMillis() - MinT > oneMinute) {
				MinT+= oneMinute;
                System.out.println("updates : " + updateCnt + " | FPS: "  + FPSCnt);
				updateCnt=FPSCnt=0;
			}
			
		}
	}
	
	private void render() {
		
	}
	
}
