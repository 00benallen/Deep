package res;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;

import main.Loader;
import main.Main;

/**
 * Class defining an item
 * @author Ben Pinhorn
 *
 */
public class Item {
	public static final int HELMET = 0, CHESTPLATE = 1, LEGGINGS = 2, BOOTS = 3, AMULET = 4, RING = 5;
	private String name;
	private int prot, price, mod;
	private int modType;
	private int type;
	private BufferedImage image;
	
	/**
	 * Default constructor for Item, stats are generated based on type of item, damage mods are randomized
	 * @param type
	 * @throws IOException
	 */
	public Item(int type) throws IOException {
		Main.log.log(Level.INFO, "Creating item!");
		this.setType(type);
		if(type == HELMET) {
			setProt(1);
			setPrice(1);
			setMod(0);
			setName("Helmet");
			setImage(Loader.helmetImage);
		}
		else if(type == CHESTPLATE) {
			setProt(2);
			setPrice(3);
			setMod(0);
			setName("Chestplate");
			setImage(Loader.chestplateImage);
		}
		else if(type == LEGGINGS) {
			setProt(1);
			setPrice(1);
			setMod(0);
			setName("Leggings");
			setImage(Loader.leggingsImage);
		}
		else if(type == BOOTS) {
			setProt(1);
			setPrice(1);
			setMod(0);
			setName("Boots");
			setImage(Loader.bootsImage);
		}
		else if(type == AMULET) {
			setProt(0);
			setPrice(10);
			Random rand = new Random();
			setMod(rand.nextInt(3) + 2);
			setModType(rand.nextInt(4));
			setName("Amulet");
			setImage(Loader.amuletImage);
		}
		else if(type == RING) {
			setProt(0);
			setPrice(8);
			Random rand = new Random();
			setMod(rand.nextInt(3) + 1);
			setModType(rand.nextInt(4));
			setName("Ring");
			setImage(Loader.ringImage);
		}
	}
	
	public int getProt() {return prot;}
	public void setProt(int prot) {this.prot = prot;}
	public int getPrice() {return price;}
	public void setPrice(int price) {this.price = price;}
	public int getMod() {return mod;}
	public void setMod(int mod) {this.mod = mod;}
	public int getModType() {return modType;}
	public void setModType(int modType) {this.modType = modType;}
	public int getType() {return type;}
	public void setType(int type) {this.type = type;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public BufferedImage getImage() {return image;}
	public void setImage(BufferedImage image) {this.image = image;}
}
