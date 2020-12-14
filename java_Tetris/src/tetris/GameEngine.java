package tetris;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class GameEngine extends JFrame implements KeyListener {

	private static final long serialVersionUID = 1L;

	private boolean isGame = true;

	private Thread runPaint;
	private Thread runUpdate;

	private Stick nowStick = new Stick(2, 0);
	private Stick nextStick = new Stick(2, 0);

	private GameValue value = new GameValue();

	private GameBoard game;
	private SideBoard side;

	public GameEngine() {

		game = new GameBoard();
		side = new SideBoard();

		game.setStick(nowStick);
		side.setStick(nextStick);
		side.setValue(value);
		this.setLayout(null);
		this.add(game);
		this.add(side);
		
		game.setBounds(0, 0, 600, 695);
		side.setBounds(600, 0, 300, 695);

		value.update();

		Runnable paint = () -> {
			while (true) {
				if (isGame) {
					game.repaint();
					side.repaint();
					try {
						Thread.sleep(15);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				// System.out.println("A");

			}
		};

		Runnable update = () -> {
			while (true) {
				if (isGame) {
					try {
						Thread.sleep(value.getSpeed());
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					this.Update();
				}
				// System.out.println("B");
			}
		};

		runPaint = new Thread(paint);
		runUpdate = new Thread(update);

		runPaint.start();
		runUpdate.start();

		this.addKeyListener(this);

		this.setSize(550 + 300, 695);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void Update() {
		if (game.isCrash(nowStick.getX(), nowStick.getY() + 1, nowStick.getShape(), nowStick.getDir())) {
			game.fillStick(nowStick.getX(), nowStick.getY(), nowStick.getShape(), nowStick.getDir());
			for (int i = 0; i < game.HEIGHT - 1; i++) {
				if (game.checkLine(i)) {
					game.moveLine(i);
					value.checkUp();
					value.comboUp();
				}
			}
			value.update();
			System.out.printf("%d, %d, %d, %d\n", value.getSpeed(), value.getCheck(), value.getScore(),
					value.getLevel());
			nowStick.reset(nextStick);
			nextStick.reset();
		} else {
			nowStick.down(1);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {

		TypeShapes shape = TypeShapes.values()[nowStick.getShape()];

		if (isGame) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if (!game.isCrash(nowStick.getX() - 1, nowStick.getY(), nowStick.getShape(), nowStick.getDir())) {
					nowStick.left(1);
				}
				break;
			case KeyEvent.VK_RIGHT:
				if (!game.isCrash(nowStick.getX() + 1, nowStick.getY(), nowStick.getShape(), nowStick.getDir())) {
					nowStick.right(1);
				}
				break;
			// Super Rotation System ¹ÌÀû¿ë
			case KeyEvent.VK_UP:
				if (nowStick.getX() < 1) {
					nowStick.right(shape.edgeLeft(nowStick.getDir()));
				}
				if (nowStick.getX() + shape.edgeRight(nowStick.getRightDir()) >= game.WIDTH - 1) {
					nowStick.left(shape.getLengh() - shape.edgeRight(nowStick.getDir()) - 1);
				}
				if (!game.isCrash(nowStick.getX(), nowStick.getY(), nowStick.getShape(), nowStick.getRightDir())) {
					nowStick.turnRight();
				}
				break;
			case KeyEvent.VK_DOWN:
				value.fast();
				break;
			case KeyEvent.VK_S:
				if (nowStick.getX() < 1) {
					nowStick.right(shape.edgeLeft(nowStick.getDir()));
				}
				if (nowStick.getX() + shape.edgeRight(nowStick.getLeftDir()) >= game.WIDTH - 1) {
					nowStick.left(shape.getLengh() - shape.edgeRight(nowStick.getDir()) - 1);
				}
				if (!game.isCrash(nowStick.getX(), nowStick.getY(), nowStick.getShape(), nowStick.getRightDir())) {
					nowStick.turnLeft();
				}
				break;
			case KeyEvent.VK_D:
				if (nowStick.getX() < 1) {
					nowStick.right(shape.edgeLeft(nowStick.getDir()));
				}
				if (nowStick.getX() + shape.edgeRight(nowStick.getRightDir()) >= game.WIDTH - 1) {
					nowStick.left(shape.getLengh() - shape.edgeRight(nowStick.getDir()) - 1);
				}
				if (!game.isCrash(nowStick.getX(), nowStick.getY(), nowStick.getShape(), nowStick.getRightDir())) {
					nowStick.turnRight();
				}
				break;
			case KeyEvent.VK_SPACE:
				int raw = 0;
				while (!game.isCrash(nowStick.getX(), nowStick.getY() + raw + 1, nowStick.getShape(),
						nowStick.getDir())) {
					raw++;
				}
				nowStick.down(raw);
				this.Update();
				break;
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (isGame == true) {
				isGame = false;
			} else {
				isGame = true;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_DOWN:
			value.update();
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
