package Managers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class ImageLoader {
	
	BufferedImage protoTank;
	
	BufferedImage tank[][] = new BufferedImage[1][2];
	BufferedImage tankFire[][] = new BufferedImage[1][2];
	
	BufferedImage shell;
	
	BufferedImage Bomb[] = new BufferedImage[3];
	
	BufferedImage background[] = new BufferedImage[2];
	BufferedImage floor[] = new BufferedImage[2];

	private static ImageLoader IL = new ImageLoader();

	public static ImageLoader getInstance() {
		return IL;
	}

	private ImageLoader() {
		try {
			background[0] = ImageIO.read(new File("Picture/BackGround/Background_black.png"));
			background[1] = ImageIO.read(new File("Picture/BackGround/Background_mountain.png"));
			
			protoTank = ImageIO.read(new File("Picture/character/actor1/tank.png"));
			
			tank[0][0] = ImageIO.read(new File("Picture/character/actor1/tank1.png"));
			tank[0][1] = ImageIO.read(new File("Picture/character/actor1/tank2.png"));
			
			tankFire[0][0] = ImageIO.read(new File("Picture/character/actor1/tankShut1.png"));
			tankFire[0][1] = ImageIO.read(new File("Picture/character/actor1/tankShut2.png"));
			
			shell = ImageIO.read(new File("Picture/character/actor1/tankShut2.png"));
			
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
	
	public BufferedImage getTank(int p, int i) {
		return tank[p][i];
	}
	
	public BufferedImage getStone() {
		return floor[0];
	}
}
