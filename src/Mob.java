import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Mob extends Rectangle {
	public int mobSize = 52;

	public int up = 0, down = 1, right = 2, left = 3;
	public int direction = right;
	public boolean hasUp = false;
	public boolean hasDown = false;
	public boolean hasRight = false;
	public boolean hasLeft = false;
	public int healthSpace = 3, healthHeight = 6;

	public int mobWalk = 0;
	public int mobID = Value.mobAir;
	private boolean inGame = false;
	public int xC, yC;
	public int health;
	public int mobCash = 0;

	public Mob() {

	}

	public int getAllMoney() {
		int result = mobCash;
		mobCash = 0;
		return result;
	}

	public void spawnMob(int mobID) {
		for (int y = 0; y < Screen.room.block.length; y++) {
			if (Screen.room.block[y][0].groundID == Value.groundRoad) {
				setBounds(Screen.room.block[y][0].x, Screen.room.block[y][0].y,
						mobSize, mobSize);
				xC = 0;
				yC = y;
			}
		}
		this.mobID = mobID;
		this.health = Value.mobHealth[mobID]; // My design!
		this.mobCash = Value.deathReward[mobID]; // Value.deathReward;
		this.setWalkSpeed(Value.mobSpeed[mobID]);
		// Screen.mobs[mobID].walkSpeed = 0;
		inGame = true;
	}

	public void deleteMob() {
		inGame = false;
		direction = right;
		mobWalk = 0;
	}

	public void loseHealth() {
		Screen.health -= Value.mobDamage[mobID];
	}

	private int walkFrame = 0;
	public int setterWalkSpeed = getMobSpeed(mobID);
	public int walkSpeed = setterWalkSpeed;

	public int getWalkFrame() {
		return walkFrame;
	}

	public void setWalkFrame(int walkFrame) {
		this.walkFrame = walkFrame;
	}

	public int getWalkSpeed() {
		return walkSpeed;

	}

	public void setWalkSpeed(int walkSpeed) {
		this.walkSpeed = walkSpeed;
	}

	public int getMobSpeed(int mobID) {
		if (mobID >= 0) {
			return Value.mobSpeed[mobID];
		}
		return 50;
	}

	public void physics() {
		if (walkFrame >= walkSpeed) {
			if (direction == right) {
				x += 1;
				hasRight = true;
			} else if (direction == down) {
				y += 1;
				hasDown = true;
			} else if (direction == up) {
				y -= 1;
				hasUp = true;
			} else if (direction == left) {
				x -= 1;
				hasLeft = true;
			}

			if (direction != left) {
				hasLeft = false;
			}
			if (direction != right) {
				hasRight = false;
			}
			if (direction != up) {
				hasUp = false;
			}
			if (direction != down) {
				hasDown = false;
			}

			mobWalk += 1;
			if (mobWalk == Screen.room.getBlockSize()) {
				if (direction == right) {
					xC += 1;
				} else if (direction == down) {
					yC += 1;
				} else if (direction == up) {
					yC -= 1;
				} else if (direction == left) {
					xC -= 1;
				}

				if (!hasUp)
					try {
						if (Screen.room.block[yC + 1][xC].groundID == Value.groundRoad) {
							direction = down;
						}
					} catch (Exception e) {
					}
				if (!hasLeft) {
					try {
						if (Screen.room.block[yC][xC + 1].groundID == Value.groundRoad) {
							direction = right;
						}
					} catch (Exception e) {
					}
				}
				if (!hasDown) {
					try {
						if (Screen.room.block[yC - 1][xC].groundID == Value.groundRoad) {
							direction = up;
						}
					} catch (Exception e) {
					}
				}
				if (!hasRight) {
					try {
						if (Screen.room.block[yC][xC - 1].groundID == Value.groundRoad) {
							direction = left;
						}
					} catch (Exception e) {
					}
				}

				if (Screen.room.block[yC][xC].airID == Value.airBase) {
					deleteMob();
					loseHealth();
				}

				mobWalk = 0;
			}

			walkFrame = 0;
		} else {
			walkFrame += 1;
		}

	}

	public void loseHealth(int amo) {
		health -= amo;
		checkDeath();
	}

	public void checkDeath() {
		if (health <= 0) {
			deleteMob();
		}
	}

	public boolean isNotAlive() {
		if (inGame) {
			return false;
		} else {
			return true;
		}
	}

	public void draw(Graphics g) {
		g.drawImage(Screen.tileset_mob[mobID], x, y, width, height, null);

		// HealthBar
		g.setColor(new Color(0, 0, 0));
		g.fillRect(x, y - (healthSpace + healthHeight),
				(int) Math.sqrt(Value.backgroundWidth[mobID]) * 3, healthHeight);

		g.setColor(new Color(50, 180, 50));
		g.fillRect(x, y - (healthSpace + healthHeight),
				(int) Math.sqrt(health) * 3, healthHeight);

		g.setColor(new Color(0, 0, 0));
		g.drawRect(x, y - (healthSpace + healthHeight),
				(int) Math.sqrt(Value.backgroundWidth[mobID]) * 3 - 1,
				healthHeight - 1);
	}

}
