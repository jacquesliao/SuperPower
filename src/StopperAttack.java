import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class StopperAttack implements Attack {

	private int loseTime = Value.towerAttackSpeed[Value.TowerStopperID];
	private int loseFrame;

	@Override
	public void attack(Mob shotMob) {

		if (Block.isShooting) {
			shotMob.setWalkFrame(-1);

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
			g.setColor(new Color(50, 255, 180, 255));
			g.drawLine(x + (width / 2), y + (height / 2), shotMob.x
					+ (shotMob.width / 2), shotMob.y + (shotMob.height / 2));
		}
	}

	@Override
	public String getAttackID() {
		return "StopperAttack";

	}

	@Override
	public void attackAll(ArrayList<Mob> mobs) {
		// TODO Auto-generated method stub

	}
}
