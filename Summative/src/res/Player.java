package res;

import java.util.LinkedList;

public class Player {
	private LinkedList<Item> inv;
	private Item head, chest, legs, feet, neck, hand;
	private int items;
	private int health, magic, gold, curRoom, prot;
	private int[] stats;
	private LinkedList<Spell> learned;
	
	public Player() {
		inv = new LinkedList<Item>();
		health = 100;
		magic = 100;
		gold = 0;
		curRoom = 0;
		prot = 0;
		learned = new LinkedList<Spell>();
		stats = new int[4];
	}
	
	public Item getItem(int index) {return inv.get(index);}
	public int getHealth() {return health;}
	public int getMagic() {return magic;}
	public int getGold() {return gold;}
	public int getCurRoom() {return curRoom;}
	
	public void addItem(Item newItem) {
		if(items < 16) {
			inv.add(newItem);
			items++;
		}
	}
	
	public void removeItem(int index) {
		Item rItem = inv.get(index);
		setProt(getProt() - rItem.getProt());
		stats[rItem.getModType()] -= rItem.getMod();
		inv.remove(index);
		items--;
	}
	
	public int getItems() {return items;}
	
	public void addSpell(Spell spell) {
		learned.add(spell);
	}

	public int getProt() {
		return prot;
	}

	private void setProt(int prot) {
		this.prot = prot;
	}
	
	public int getStat(int type) {return stats[type];}
	
	public void equip(Item i) {
		if(i.getType() == Item.HELMET) {
			addItem(head);
			head = i;
		}
		if(i.getType() == Item.CHESTPLATE) {
			addItem(chest);
			chest = i;
		}
		if(i.getType() == Item.LEGGINGS) {
			addItem(legs);
			legs = i;
		}
		if(i.getType() == Item.BOOTS) {
			addItem(feet);
			feet = i;
		}
		if(i.getType() == Item.RING) {
			addItem(hand);
			hand = i;
		}
		if(i.getType() == Item.AMULET) {
			addItem(neck);
			neck = i;
		}
		updateStats();
	}
	
	private void updateStats() {
		for(int i = 0; i < 4; i++) {
			stats[i] = 0;
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
}
