import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class LaserAttack implements Attack {
	private int loseTime = Value.towerAttackSpeed[Value.TowerLaserID];
	private int loseFrame;

	@Override
	public void attack(Mob shotMob) {

		if (Block.isShooting) {
			if (loseFrame >= loseTime) {
				shotMob.loseHealth(2);
				loseFrame = 0;
			} else {
				loseFrame += 1;
			}

			if (shotMob.isNotAlive()) {
				Block.getMoney(shotMob);
				Block.isShooting = false;
			}
		}
	}

	public void draw(Graphics g, int x, int y, int width, int height,
			Mob shotMob) {

		if (!shotMob.isNotAlive()) {
			g.setColor(new Color(255, 0, 0, 255));
			g.drawLine(x + (width / 2), y + (height / 2), shotMob.x
					+ (shotMob.width / 2), shotMob.y + (shotMob.height / 2));
		}
	}

	@Override
	public String getAttackID() {
		return "LaserAttack";

	}

	@Override
	public void attackAll(ArrayList<Mob> mobs) {
		// TODO Auto-generated method stub

	}
}
