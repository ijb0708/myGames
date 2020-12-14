package tetris;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class SideBoard extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private static final int oneBlock=20;
	
	private Font minFont= new Font("±¼¸²", Font.BOLD, 20);
	
	private Stick stick;
	private GameValue value;
	
	public void setStick(Stick stick) {
		this.stick=stick;
	}
	
	public void setValue(GameValue value) {
		this.value=value;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		TypeShapes shape = TypeShapes.values()[stick.getShape()];
		
		for (int i=0; i<shape.getLengh(); i++) {
			for (int j=0; j<shape.getLengh(); j++) {
				if(shape.isVisible(stick.getDir(), j, i)) {
					g.setColor(shape.getColor());
					g.fillRect(j * oneBlock, i * oneBlock, oneBlock, oneBlock);
				}
			}
		}
		
		g.setColor(Color.BLACK);
		g.setFont(minFont);
		g.drawString(value.getScore()+"Á¡", 30, 100);
	}

}
