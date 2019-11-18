package main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import Managers.GameManager;

public class GameEngine extends JPanel implements Runnable{
	
	private static final long serialVersionUID = 1L;
	
	public static final int GAMEWIDTH=600;
	public static final int GAMEHEIGHT=600;

	private Thread thread;
	private GameManager gm;
	
	private BufferedImage bufferImg;
	private Graphics2D g2d;
	
	private boolean isRun;
	
	public GameEngine() {
		
		gm = GameManager.getInstance();
		gm.setState(0);
		
		thread= new Thread(this);
		thread.start();
		
		this.addKeyListener(gm);
		this.setFocusable(true);
	}

	@Override
	public void run() {
		
		/*************************
		  	그래픽처리를 하는 쓰래드 
		**************************/
			
		this.init();
		
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
		
		isRun=true;
		
		// 실작동 부분
		while (isRun) {
			
			nowT= System.nanoTime();
			delta += (nowT - preT) * updateMax;
			preT = nowT;
			
			if(delta>=oneSecond) {
				gm.update();
				//update부분
				updateCnt++;
				delta-=oneSecond;
			}
			
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.draw();
			this.repaint();
			//draw, render 그리는부분
			FPSCnt++;
			
			if(System.currentTimeMillis() - MinT > oneMinute) {
				MinT+= oneMinute;
                System.out.println("updates : " + updateCnt + " | FPS: "  + FPSCnt);
				updateCnt=FPSCnt=0;
			}
		}
		
	}
	
	private void init() {
		bufferImg= new BufferedImage(GAMEWIDTH, GAMEHEIGHT, BufferedImage.TYPE_INT_RGB);
		g2d =(Graphics2D) bufferImg.getGraphics();
	}
	
	private void draw() {
		gm.draw(g2d);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(bufferImg, 0, 0, GAMEWIDTH, GAMEHEIGHT, null);
		g.dispose();
	}
	
}
