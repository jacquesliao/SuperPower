import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class FreezeAttack implements Attack {
	private int loseTime = Value.towerAttackSpeed[Value.TowerFreezeID];
	private int loseFrame;

	@Override
	public void attack(Mob shotMob) {

		if (Block.isShooting) {
			shotMob.setWalkSpeed(Value.mobSpeed[shotMob.mobID] * 2);

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
			g.setColor(new Color(0, 0, 255, 255));
			g.drawLine(x + (width / 2), y + (height / 2), shotMob.x
					+ (shotMob.width / 2), shotMob.y + (shotMob.height / 2));
		}
	}

	@Override
	public String getAttackID() {
		return "FreezeAttack";

	}

	@Override
	public void attackAll(ArrayList<Mob> mobs) {
		// TODO Auto-generated method stub

	}
}
