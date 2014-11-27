import java.awt.Graphics;
import java.util.ArrayList;

public interface Attack {
	public abstract void attack(Mob shotMob);

	public abstract void attackAll(ArrayList<Mob> mobs);

	public abstract void draw(Graphics g, int x, int y, int width, int height,
			Mob shotMob);

	public abstract String getAttackID();
}
