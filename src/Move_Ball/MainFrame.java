package Move_Ball;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	public MainFrame() {
		this.add(new GameEngine());
		
		this.setSize(GameEngine.BOARD_WIDTH, GameEngine.BOARD_HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
