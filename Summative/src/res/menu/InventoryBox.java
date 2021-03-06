package res.menu;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import res.Item;
import res.Player;

/**
 * Object that displays the inventory of the player
 * @author Ben Pinhorn
 *
 */
public class InventoryBox extends Drawable {
	private LinkedList<Item> inventory;
	private LinkedList<Rectangle2D> bounds;
	private BufferedImage inventoryBackground;
	private int width, height;
	
	/**
	 * Default constructor for InventoryBoxes
	 * @param x
	 * @param y
	 * @param image
	 * @param name
	 * @throws IOException
	 */
	public InventoryBox(int x, int y, BufferedImage image, String name) throws IOException {
		super(x, y, image, name);
		inventory = new LinkedList<Item>();
		inventoryBackground = image;
		this.width = inventoryBackground.getWidth();
		this.height = inventoryBackground.getHeight();
		bounds = new LinkedList<Rectangle2D>();
		genImage();
	}
	
	public Item getItem(int index) {return inventory.get(index);}
	public Rectangle2D getBounds(int index) {return bounds.get(index);}
	 
	/**
	 * Adds item to inventory box
	 * @param i
	 */
	public void addItem(Item i) {
		inventory.add(i);
		genImage();	
	}
	
	/**
	 * Removes item from inventory box
	 * @param index
	 */
	public void removeItem(int index) {
		inventory.remove(index);
		bounds.remove(index);
		genImage();
	}
	
	/**
	 * Adds whole inventory of player to inventory box
	 * @param player
	 */
	public void addInventory(Player player) {
		for(int i = 0; i < player.getItems(); i++) {
			inventory.add(player.getItem(i));
		}
		genImage();
	}
	
	/**
	 * Clears the inventory box of items
	 */
	public void clearInventory() {
		inventory.clear();
		genImage();
	}
	
	private void genImage() {
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) image.getGraphics();
		g.drawImage(inventoryBackground, this.getX(), this.getY(), null);
		
		int cnt = 1;
		bounds.clear();
		for(int i = 0; i < inventory.size(); i++) {
			bounds.add(new Rectangle2D.Double(getX() + (width/8)*i, getY() + (height/4)*cnt, inventory.get(i).getImage().getWidth(), inventory.get(i).getImage().getHeight()));
			g.drawImage(inventory.get(i).getImage(), (width/8)*i, (height/4)*cnt, null);
			if(i > 7) {
				cnt = 2;
			}
		}
		this.setImage(image);
	}
}
