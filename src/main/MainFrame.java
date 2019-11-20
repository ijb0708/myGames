package Main;

import javax.swing.JFrame;

public class MainFrame extends JFrame{
	public MainFrame() {
		
		this.setContentPane(new GameEngine());
		
		this.setSize(1200+16, 600+38);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
