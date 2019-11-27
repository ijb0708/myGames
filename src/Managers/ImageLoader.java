package Managers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class ImageLoader {
	
	private BufferedImage protoTank;
	
	private BufferedImage[] tank1 = new BufferedImage[2];
	private BufferedImage[] tankFire1 = new BufferedImage[2];
	
	private BufferedImage bullet;
	
	private BufferedImage[] Bomb = new BufferedImage[3];
	
	private BufferedImage[] background = new BufferedImage[2];
	private BufferedImage[] floor = new BufferedImage[2];

	private static ImageLoader IL = new ImageLoader();

	public static ImageLoader getInstance() {
		return IL;
	}

	private ImageLoader() {
		try {
			background[0] = ImageIO.read(new File("Picture/BackGround/Background_black.png"));
			background[1] = ImageIO.read(new File("Picture/BackGround/Background_mountain.png"));
			
			protoTank = ImageIO.read(new File("Picture/character/actor1/tank.png"));
			
			tank1[0] = ImageIO.read(new File("Picture/character/actor1/tank1.png"));
			tank1[1] = ImageIO.read(new File("Picture/character/actor1/tank2.png"));
			
			tankFire1[0] = ImageIO.read(new File("Picture/character/actor1/tankShut1.png"));
			tankFire1[1] = ImageIO.read(new File("Picture/character/actor1/tankShut2.png"));
			
			bullet = ImageIO.read(new File("Picture/bullets/bullet.png"));
			
			Bomb[0] = ImageIO.read(new File("Picture/effect/bomb1.png"));
			Bomb[1] = ImageIO.read(new File("Picture/effect/bomb2.png"));
			Bomb[2] = ImageIO.read(new File("Picture/effect/bomb3.png"));
			
			floor[0] = ImageIO.read(new File("Picture/floor/Stone.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public BufferedImage getBackground_black() {
		return background[0];
	}

	public BufferedImage getBackground_mountain() {
		return background[1];
	}
	
	public BufferedImage[] getTank1() {
		return tank1;
	}
	
	public BufferedImage getProtoTank(int delta) {
		return protoTank;
	}
	
	public BufferedImage getStone() {
		return floor[0];
	}
	
	public BufferedImage[] getBomb() {
		return Bomb;
	}
	
	public BufferedImage getBullet() {
		return bullet;
	}
}
