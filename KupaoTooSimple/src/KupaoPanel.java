import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class KupaoPanel extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;
	private Game game;

	public KupaoPanel() {
		game = new Game();
		new Thread(this).start();

	}

	public void update() {
		game.update();
		repaint();
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;
		for (Render r : game.getRenders()) {
			if (r.transform != null){
				g2D.drawImage(r.image, r.transform, null);
			} else {
				g.drawImage(r.image, r.x, r.y, null);
			}
		}

		g2D.setColor(Color.BLACK);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while (true) {
				update();
				Thread.sleep(25);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
