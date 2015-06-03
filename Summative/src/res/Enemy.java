package res;

import java.util.Random;

public class Enemy {
	private int health, damage, prot;
	
	public Enemy() {
		Random rand = new Random();
		setHealth(10);
		setDamage(rand.nextInt(3) + 1);
		setProt(rand.nextInt(2));
	}
	
	public Enemy(int health, int damage, int prot) {
		setHealth(health);
		setDamage(damage);
		setProt(prot);
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getProt() {
		return prot;
	}

	public void setProt(int prot) {
		this.prot = prot;
	}
	
	public int hit(int[] stats) {
		Random rand = new Random();
		int r = rand.nextInt(4);
		setHealth(Math.max(getHealth() - Math.max(stats[r] - prot, 0), 0));
		return stats[r];
	}

}
