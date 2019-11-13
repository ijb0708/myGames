package main;

import javax.swing.JFrame;

public class MainFrame extends JFrame{
	public MainFrame() {
		
		this.setContentPane(new GameEngine());
		
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
