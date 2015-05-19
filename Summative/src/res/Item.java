package res;

import java.util.Random;

public class Item {
	public static final int HELMET = 0, CHESTPLATE = 1, LEGGINGS = 2, BOOTS = 3, AMULET = 4, RING = 5;
	private String name;
	private int prot, price, mod;
	private int modType;
	private int type;
	
	public Item(int type) {
		this.setType(type);
		if(type == HELMET) {
			setProt(1);
			setPrice(1);
			setMod(0);
			setName("Helmet");
		}
		else if(type == CHESTPLATE) {
			setProt(2);
			setPrice(3);
			setMod(0);
			setName("Chestplate");
		}
		else if(type == LEGGINGS) {
			setProt(1);
			setPrice(1);
			setMod(0);
			setName("Leggings");
		}
		else if(type == BOOTS) {
			setProt(1);
			setPrice(1);
			setMod(0);
			setName("Boots");
		}
		else if(type == AMULET) {
			setProt(0);
			setPrice(10);
			Random rand = new Random();
			setMod(rand.nextInt(3) + 2);
			setModType(rand.nextInt(4));
			setName("Amulet");
			
		}
		else if(type == RING) {
			setProt(0);
			setPrice(8);
			Random rand = new Random();
			setMod(rand.nextInt(3) + 1);
			setModType(rand.nextInt(4));
			setName("Ring");
		}
	}
	
	public int getProt() {
		return prot;
	}

	public void setProt(int prot) {
		this.prot = prot;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getMod() {
		return mod;
	}

	public void setMod(int mod) {
		this.mod = mod;
	}

	public int getModType() {
		return modType;
	}

	public void setModType(int modType) {
		this.modType = modType;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
