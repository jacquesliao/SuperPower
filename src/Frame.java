import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;

public class Frame extends JFrame {

	public static String title = "Ultimate Tower Defense 0.0.1";
	public static Dimension size = new Dimension(700, 550);

	public Frame() {
		// initalize stuff
		setTitle(title);
		setSize(size);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Activate
		init();
	}

	public void init() {
		// initialize
		setLayout(new GridLayout(1, 1, 0, 0));
		Screen screen = new Screen(this);
		add(screen);

		// I can see...
		setVisible(true);

	}

	public static void main(String[] args) {
		Frame frame = new Frame();

	}
}
