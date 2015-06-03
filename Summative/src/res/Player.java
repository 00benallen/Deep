package res;

import java.util.LinkedList;
import java.util.logging.Level;

import main.Main;

/**
 * Class defining a player
 * @author Ben Pinhorn
 *
 */
public class Player {
	private LinkedList<Item> inv;
	private Item head, chest, legs, feet, neck, hand;
	private int items;
	private int health, magic, gold, curRoom, prot;
	private int[] stats;
	
	/**
	 * Default constructor for player
	 */
	public Player() {
		inv = new LinkedList<Item>();
		health = 100;
		magic = 100;
		gold = 0;
		curRoom = 0;
		prot = 0;
		stats = new int[4];
		updateStats();
	}
	
	public Item getItem(int index) {return inv.get(index);}
	public int getHealth() {return health;}
	public void setHealth(int health) {this.health = health;}
	public int getMagic() {return magic;}
	public int getGold() {return gold;}
	public int getCurRoom() {return curRoom;}
	public int getItems() {return items;}
	public int getProt() {return prot;}
	public int[] getStats() {return stats;}
	private void setProt(int prot) {this.prot = prot;}
	public void setMagic(int magic) {this.magic = magic;}
	
	/**
	 * Adds an item to the players inventory, as long as it isn't full
	 * @param newItem
	 */
	public void addItem(Item newItem) {
		if(items < 16) {
			inv.add(newItem);
			items++;
		}
	}
	
	/**
	 * Deletes an item from the player's inventory
	 * @param index
	 */
	public void removeItem(int index) {
		Item rItem = inv.get(index);
		setProt(getProt() - rItem.getProt());
		stats[rItem.getModType()] -= rItem.getMod();
		inv.remove(index);
		items--;
	}
	
	/**
	 * Equips an item, removing it from the inventory, updating the player's stats, returns false if the item cannot be equipped
	 * @param index
	 * @return equiped/not equipped
	 */
	public boolean equip(int index) {
		Main.log.log(Level.INFO, "Equipping item!");
		if(getItem(index).getType() == Item.HELMET) {
			if(head == null) {
				head = getItem(index);
				removeItem(index);
				return true;
			}
			return false;
		}
		else if(getItem(index).getType() == Item.CHESTPLATE) {
			if(chest == null) {
				chest = getItem(index);
				removeItem(index);
				return true;
			}
			return false;
		}
		else if(getItem(index).getType() == Item.LEGGINGS) {
			if(legs == null) {
				legs = getItem(index);
				removeItem(index);
				return true;
			}
			return false;
		}
		else if(getItem(index).getType() == Item.BOOTS) {
			if(feet == null) {
				feet = getItem(index);
				removeItem(index);
				return true;
			}
			return false;
		}
		else if(getItem(index).getType() == Item.RING) {
			if(hand == null) {
				hand = getItem(index);
				removeItem(index);
				return true;
			}
			return false;
		}
		else if(getItem(index).getType() == Item.AMULET) {
			if(hand == null) {
				hand = getItem(index);
				removeItem(index);
				return true;
			}
			return false;
		}
		updateStats();
		return false;
	}
	
	private void updateStats() {
		for(int i = 0; i < 4; i++) {
			stats[i] = 1;
		}
		if(hand != null) {
			stats[hand.getModType()] += hand.getMod();
		}
		if(neck != null) {
			stats[neck.getModType()] += neck.getMod();
		}
		
		setProt(0);
		if(head != null) {
			setProt(getProt() + head.getProt());
		}
		if(chest != null) {
			setProt	(getProt() + chest.getProt());
		}
		if(legs != null) {
			setProt(getProt() + legs.getProt());
		}
		if(feet != null) {
			setProt(getProt() + feet.getProt());
		}
	}
	
	/**
	 * Damages the player by the given amount
	 * @param damage
	 */
	public void hit(int damage) {
		setHealth(Math.max(getHealth() - Math.max(damage - prot, 0), 0));
	}
}
