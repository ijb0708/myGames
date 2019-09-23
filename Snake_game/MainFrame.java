package Snake_game;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame{
	
	private JPanel gamePanel;
	
	public MainFrame() {
		
		gamePanel = new GameEngine();
		
		this.add(gamePanel);
		
		this.setSize(gamePanel.getWidth()+18, gamePanel.getHeight()+40);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
