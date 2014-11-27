
public class Value {
	public static int groundGrass = 0;
	public static int groundRoad = 1;

	public static int airAir = -1;
	public static int airBase = 0;
	public static int airTrash = 1;

	public static int airTowerLaser = 2;
	public static int airTowerFreeze = 3;
	public static int airTowerBlaster = 4;
	public static int airTowerStopper = 5;
	public static int airTowerMoneyTree = 6;
	public static int airTowerTurret = 7;

	public static int TowerLaserID = 0;
	public static int TowerFreezeID = 1;
	public static int TowerBlasterID = 2;
	public static int TowerStopperID = 3;
	public static int TowerMoneyTreeID = 4;
	public static int TowerTurretID = 5;

	public static int[] towerAttackSpeed = { 200, 200, 50, 200, 700, 150 };

	public static int mobAir = -1;
	public static int mobGreeny = 0;
	public static int mobRed = 1;
	public static int mobDark = 2;
	public static int mobGold = 3;

	public static int[] deathReward = { 5, 20, 40, 80 };
	public static int[] mobHealth = { 104, 460, 1104, 2104 };
	public static int[] mobSpeed = { 55, 47, 21, 47 };
	public static int[] mobDamage = { 1, 5, 20, 40 };

	public static int[] mobList = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0,
			0, 0, 0, 0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1,
			1, 2, 1, 2, 0, 2, 3 };

	// mobs;
	// Ulixava makes no sense.
	public static int[] backgroundWidth = mobHealth;
}
