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
		  	�׷���ó���� �ϴ� ������ 
		**************************/
			
		this.init();
		
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
		
		isRun=true;
		
		// ���۵� �κ�
		while (isRun) {
			
			nowT= System.nanoTime();
			delta += (nowT - preT) * updateMax;
			preT = nowT;
			
			if(delta>=oneSecond) {
				gm.update();
				//update�κ�
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
			//draw, render �׸��ºκ�
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
