package tetris;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GameBoard extends JPanel {

	private static final long serialVersionUID = 1L;

	public final int WIDTH = 12;
	public final int HEIGHT = 22;

	public final int oneBlock = 30;

	private Color[][] Board = new Color[HEIGHT][WIDTH];

	private Stick stick;

	public GameBoard() {
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				if (i == HEIGHT - 1 || j == 0 || j == WIDTH - 1)
					Board[i][j] = Color.black;
				else
					Board[i][j] = Color.white;
			}
		}

	}

	public void fillStick(int curX, int curY, int sh, int dir) {
		TypeShapes shape = TypeShapes.values()[sh];

		for (int i = 0; i < shape.getLengh(); i++) {
			for (int j = 0; j < shape.getLengh(); j++) {
				if (shape.isVisible(dir, j, i) == true) {
					Board[curY + i][curX + j] = shape.getColor();
				}
			}
		}
	}

	public boolean isCrash(int curX, int curY, int sh, int dir) {
		TypeShapes shape = TypeShapes.values()[sh];

		for (int i = 0; i < shape.getLengh(); i++) {
			for (int j = 0; j < shape.getLengh(); j++) {
				if (shape.isVisible(dir, j, i) == true && Board[curY + i][curX + j] != Color.white) {
					return true;
				}
			}
		}

		return false;
	}

	public boolean checkLine(int line) {
		for (int i = 1; i < WIDTH - 1; i++) {
			if (Board[line][i] == Color.white) {
				return false;
			}
		}

		return true;
	}

	public void moveLine(int line) {
		while (line > 0) {
			for (int i = 1; i < WIDTH - 1; i++) {
				Board[line][i] = Board[line - 1][i];
			}
			line--;
		}
		return;
	}

	public void setStick(Stick stick) {
		this.stick = stick;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		TypeShapes shape = TypeShapes.values()[stick.getShape()];

		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				g.setColor(Board[i][j]);
				g.fillRect(j * oneBlock, i * oneBlock, oneBlock, oneBlock);
			}
		}

		for (int i = 0; i < shape.getLengh(); i++) {
			for (int j = 0; j < shape.getLengh(); j++) {
				if (shape.isVisible(stick.getDir(), j, i)) {
					g.setColor(shape.getColor());
					g.fillRect((stick.getX() + j) * oneBlock, (stick.getY() + i) * oneBlock, oneBlock, oneBlock);
				}
			}
		}

		int raw = 0;
		while (!this.isCrash(stick.getX(), stick.getY() + raw + 1, stick.getShape(), stick.getDir())) {
			raw++;
		}

		for (int i = 0; i < shape.getLengh(); i++) {
			for (int j = 0; j < shape.getLengh(); j++) {
				if (shape.isVisible(stick.getDir(), j, i)) {
					g.setColor(Color.gray);
					g.drawRect((stick.getX() + j) * oneBlock, (stick.getY() + i + raw) * oneBlock, oneBlock, oneBlock);
				}
			}
		}

	}
}
