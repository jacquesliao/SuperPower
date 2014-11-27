import java.awt.Graphics;
import java.util.ArrayList;

public class MoneyTreeAttack implements Attack {

	private int loseTime = Value.towerAttackSpeed[Value.TowerMoneyTreeID];
	private int loseFrame;

	@Override
	public void attack(Mob shotMob) {
		if (Block.isShooting) {
			if (loseFrame >= loseTime) {
				Screen.coinage += 5;
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

	@Override
	public void draw(Graphics g, int x, int y, int width, int height,
			Mob shotMob) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getAttackID() {
		return "MoneyTreeAttack";

	}

	@Override
	public void attackAll(ArrayList<Mob> mobs) {
		// TODO Auto-generated method stub

	}

}
