package res;

import java.util.Random;

/**
 * Class defining an enemy
 * @author Ben Pinhorn
 *
 */
public class Enemy {
	private int health, damage, prot;
	
	/**
	 * Default Constructor for enemy, stats are randomized
	 */
	public Enemy() {
		Random rand = new Random();
		setHealth(10);
		setDamage(rand.nextInt(3) + 1);
		setProt(rand.nextInt(2));
	}
	
	/**
	 * Constructor for enemy with stats pre-generated
	 * @param health
	 * @param damage
	 * @param prot
	 */
	public Enemy(int health, int damage, int prot) {
		setHealth(health);
		setDamage(damage);
		setProt(prot);
	}

	public int getHealth() {return health;}
	public void setHealth(int health) {this.health = health;}
	public int getDamage() {return damage;}
	public void setDamage(int damage) {this.damage = damage;}
	public int getProt() {return prot;}
	public void setProt(int prot) {this.prot = prot;}
	
	/**
	 * Damages the enemy with one of the 4 spell types from the player's stat arry, random which element is used
	 * @param stats
	 * @return
	 */
	public int hit(int[] stats) {
		Random rand = new Random();
		int r = rand.nextInt(4);
		setHealth(Math.max(getHealth() - Math.max(stats[r] - prot, 0), 0));
		return stats[r];
	}

}
