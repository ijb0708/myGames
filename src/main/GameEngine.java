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
		  	�׷���ó���� �ϴ� ������ 
		**************************/
		
		//System.nanoTime ����  1000000000 �� 1�ʷ� ��� �� 1/60�� �ǹ�
		final double updateMax= 60.0;
		final double oneSecond =1000000000;
		// TimeMillis���� ���
		final double oneMinute=1000;
		
		//���� ��, ���� �ʸ� �ǹ��ϴ� ����
		long preT = System.nanoTime();
		long nowT ;
		
		//���� �ð��� �д����� ����
		long MinT=System.currentTimeMillis();
		
		// ��ȭ���� ���õ� ����
		double delta= 0;
		int updateCnt=0;
		int FPSCnt=0;
		
		// ���۵� �κ�
		while (true) {
			nowT= System.nanoTime();
			delta += (nowT - preT) * updateMax;
			preT = nowT;
			
			if(delta>=oneSecond) {
				//update�κ�
				updateCnt++;
				delta-=oneSecond;
			}
			
			//draw, render �׸��ºκ�
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
