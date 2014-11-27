import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class TurretAttack implements Attack {

	private int loseTime = Value.towerAttackSpeed[Value.TowerStopperID];
	private int loseFrame;

	@Override
	public void attack(Mob shotMob) {
	}

	@Override
	public void draw(Graphics g, int x, int y, int width, int height,
			Mob shotMob) {

		if (!shotMob.isNotAlive()) {
			g.setColor(Color.RED);
			g.drawLine(x + (width / 2), y + (height / 2), shotMob.x
					+ (shotMob.width / 2), shotMob.y + (shotMob.height / 2));
		}
	}

	@Override
	public String getAttackID() {
		return "TurretAttack";

	}

	@Override
	public void attackAll(ArrayList<Mob> mobs) {
		if (loseFrame >= loseTime) {

			loseTime = 0;
		} else {
			loseFrame += 1;
		}

	}
}
