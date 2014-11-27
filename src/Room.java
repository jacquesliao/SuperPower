import java.awt.Graphics;

public class Room {
	private int worldWidth = 12;
	private int worldHeight = 8;
	private int blockSize = 52;

	public Block[][] block;

	public Room() {
		define();
	}

	public int getWorldWidth() {
		return worldWidth;
	}

	public int getWorldHeight() {
		return worldHeight;
	}

	public int getBlockSize() {
		return blockSize;
	}

	private void define() {
		block = new Block[worldHeight][worldWidth];

		for (int x = 0; x < block.length; x++) {
			for (int y = 0; y < block[0].length; y++) {
				block[x][y] = new Block((Screen.myWidth / 2)
						- ((worldWidth * blockSize) / 2) + (y * blockSize), x
						* blockSize, blockSize, blockSize, Value.groundGrass,
						Value.airAir);
			}
		}
	}

	public void physic() {
		for (int x = 0; x < block.length; x++) {
			for (int y = 0; y < block[x].length; y++) {
				block[x][y].physic();
			}
		}
	}

	public void draw(Graphics g) {
		for (int x = 0; x < block.length; x++) {
			for (int y = 0; y < block[x].length; y++) {
				block[x][y].draw(g);
			}
		}
		for (int x = 0; x < block.length; x++) {
			for (int y = 0; y < block[x].length; y++) {
				block[x][y].fight(g);
			}
		}
	}
}