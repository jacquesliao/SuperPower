import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Block extends Rectangle {
	public static Rectangle towerSquare;
	public int towerSquareSize = 130;
	public int groundID;
	public int airID;
	public int loseTime = 100, loseFrame;

	private Attack Laser = new LaserAttack();
	private Attack Freeze = new FreezeAttack();
	private Attack Blaster = new BlasterAttack();
	private Attack Stopper = new StopperAttack();
	private Attack MoneyTree = new MoneyTreeAttack();
	private Attack Turret = new TurretAttack();

	public int shotMob = -1;
	public static boolean isShooting = false;

	public Block(int x, int y, int width, int height, int groundID, int airID) {
		setBounds(x, y, width, height);
		towerSquare = new Rectangle(x - (towerSquareSize / 2), y
				- (towerSquareSize / 2), width + (towerSquareSize), height
				+ (towerSquareSize));
		this.groundID = groundID;
		this.airID = airID;
	}

	public void physic() {
		if (shotMob != -1 && towerSquare.intersects(Screen.mobs[shotMob])) {
			isShooting = true;
		} else {
			isShooting = false;
		}

		if (!isShooting) {
			if (airID == Value.airTowerLaser || airID == Value.airTowerFreeze
					|| airID == Value.airTowerBlaster
					|| airID == Value.airTowerStopper
					|| airID == Value.airTowerMoneyTree
					|| airID == Value.airTowerTurret) {
				for (int i = 0; i < Screen.mobs.length; i++) {
					if (!Screen.mobs[i].isNotAlive()) {
						System.out.println("alive");
						if (towerSquare.intersects(Screen.mobs[i])) {
							isShooting = true;
							shotMob = i;
						}
					}
				}
			}
		}

		if (shotMob >= 0) {
			Mob currentMob = Screen.mobs[shotMob];
			getAttack(airID).attack(currentMob);
		} else {
			System.out.println("poloi");
		}
	}

	public static void getMoney(Mob mob) {
		Screen.coinage += mob.getAllMoney();
	}

	public void draw(Graphics g) {
		g.drawImage(Screen.tileset_ground[groundID], x, y, width, height, null);

		if (airID != Value.airAir) {
			g.drawImage(Screen.tileset_air[airID], x, y, width, height, null);

		}

		if (isShooting && shotMob >= 0
				&& Screen.mobs[shotMob].intersects(towerSquare)) {
			Mob currentMob = Screen.mobs[shotMob];
			getAttack(airID).draw(g, loseTime, loseFrame, groundID, height,
					currentMob);
		}
	}

	public void fight(Graphics g) {
		if (Screen.isDebug) {
			if (airID == Value.airTowerLaser) {
				g.setColor(new Color(0, 0, 0));
				g.drawRect(towerSquare.x, towerSquare.y, towerSquare.width,
						towerSquare.height);
			}
		}
		if (shotMob >= 0) {
			getAttack(airID).draw(g, x, y, width, height, Screen.mobs[shotMob]);
		}
	}

	private Attack getAttack(int airID) {
		if (airID == Value.airTowerLaser) {
			return Laser;
		} else if (airID == Value.airTowerFreeze) {
			return Freeze;
		} else if (airID == Value.airTowerBlaster) {
			return Blaster;
		} else if (airID == Value.airTowerStopper) {
			return Stopper;
		} else if (airID == Value.airTowerMoneyTree) {
			return MoneyTree;
		} else if (airID == Value.airTowerTurret) {
			return Turret;
		}
		throw new AssertionError("Unsupported airID: " + airID);
	}
}
