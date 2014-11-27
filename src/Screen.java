import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Screen extends JPanel implements Runnable { // makes stuff on the
															// Frame
	public Thread thread = new Thread(this);

	public static Image[] tileset_ground = new Image[100];
	public static Image[] tileset_air = new Image[100];
	public static Image[] tileset_res = new Image[100];
	public static Image[] tileset_mob = new Image[100];

	public static boolean isFirst = true;
	public static boolean isDebug = false;

	private int mobNum = 0;
	public static int coinage = 500, health = 100;
	public static int kills = 0, killsToWin = 0, level = 1, max = 3;
	public static int myWidth, myHeight;

	public static Point mse = new Point(0, 0);

	public static Room room;
	public static Save save;
	public static Store store;

	public static Mob[] mobs = new Mob[100];

	public Screen(Frame frame) { // constructor
		frame.addMouseListener(new KeyHandle());
		frame.addMouseMotionListener(new KeyHandle());

		thread.start();
	}

	public void define() {
		room = new Room();
		save = new Save();
		store = new Store();

		for (int i = 0; i < tileset_ground.length; i++) {
			tileset_ground[i] = new ImageIcon("res/tileset_ground.png")
					.getImage();
			tileset_ground[i] = createImage(new FilteredImageSource(
					tileset_ground[i].getSource(), new CropImageFilter(0,
							26 * i, 26, 26)));
		}
		for (int y = 0; y < tileset_air.length; y++) {
			tileset_air[y] = new ImageIcon("res/tileset_air.png").getImage();
			tileset_air[y] = createImage(new FilteredImageSource(
					tileset_air[y].getSource(), new CropImageFilter(0, 26 * y,
							26, 26)));
		}
		for (int y = 0; y < tileset_air.length; y++) {
			tileset_mob[y] = new ImageIcon("res/mob.png").getImage();
			tileset_mob[y] = createImage(new FilteredImageSource(
					tileset_mob[y].getSource(), new CropImageFilter(0, 26 * y,
							26, 26)));
		}

		tileset_res[0] = new ImageIcon("res/cell.png").getImage();
		tileset_res[1] = new ImageIcon("res/heart.png").getImage();
		tileset_res[2] = new ImageIcon("res/coin.png").getImage();

		save.loadSave(new File("save/mission1.ulivaxa"));

		for (int i = 0; i < mobs.length; i++) {
			mobs[i] = new Mob();
		}

	}

	@Override
	// ????
	public void paintComponent(Graphics g) { // uses Graphic object to paint
		if (isFirst) {
			myWidth = getWidth();
			myHeight = getHeight();
			define();

			isFirst = false;
		}

		g.setColor(new Color(70, 70, 70));
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(new Color(0, 0, 0));
		// left border
		g.drawLine(
				room.block[0][0].x - 1,
				0,
				room.block[0][0].x - 1,
				room.block[room.getWorldHeight() - 1][0].y
						+ room.getBlockSize() + 1);
		// right border
		g.drawLine(
				room.block[0][room.getWorldWidth() - 1].x + room.getBlockSize(),
				0,
				room.block[0][room.getWorldWidth() - 1].x + room.getBlockSize(),
				room.block[room.getWorldHeight() - 1][0].y
						+ room.getBlockSize() + 1);
		// last border
		g.drawLine(
				room.block[0][0].x,
				room.block[room.getWorldHeight() - 1][0].y
						+ room.getBlockSize(),
				room.block[0][room.getWorldWidth() - 1].x + room.getBlockSize(),
				room.block[room.getWorldHeight() - 1][0].y
						+ room.getBlockSize());
		room.draw(g); // draws room

		for (int i = 0; i < mobs.length; i++) {
			if (!mobs[i].isNotAlive()) {
				mobs[i].draw(g);
			}
		}

		store.draw(g);// draws the store

		// Game Over is easy!
		if (health <= 0) {
			g.setColor(new Color(240, 20, 20, 100));
			g.fillRect(0, 0, myWidth, myHeight);
			g.setColor(new Color(240, 20, 20, 255));
			g.setFont(new Font("Courier New", Font.BOLD, 120));
			g.drawString("Game Over", 25, 175);
			g.drawString("You Lost", 55, 275);
		}
	}

	public int spawnTime = 2400, spawnFrame = 0;

	public void mobSpawner(int mobID) {
		if (spawnFrame >= spawnTime) {
			for (int i = 0; i < mobs.length; i++) {
				if (mobs[i].isNotAlive()) {
					mobs[i].spawnMob(mobID);
					mobNum += 1;
					break;
				}
			}
			spawnFrame = 0; // resets spawn frame

		} else {
			spawnFrame += 1;
		}

	}

	public static int fpsFrame = 0, fps = 1000;

	public void run() {
		while (true) { // gameloop
			if (!isFirst && health > 0) {
				room.physic();
				// mobSpawner(Value.mobList[0]);
				if (!(mobNum < Value.mobList.length)) {
					mobNum = 0;
				}

				mobSpawner(Value.mobList[mobNum]);

				for (int i = 0; i < mobs.length; i++) {
					if (!mobs[i].isNotAlive()) {
						mobs[i].physics();
					}
				}
			}
			repaint();
			try {
				Thread.sleep(1);
			} catch (Exception e) {
			}
		}
	}
}
