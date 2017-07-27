import javax.swing.*;
public class App {
    public static int WIDTH = 500;
    public static int HEIGHT = 500;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH,HEIGHT);
		frame.setLocationRelativeTo(null);
		KupaoPanel panel = new KupaoPanel();
		frame.add(panel);
	}

}
	