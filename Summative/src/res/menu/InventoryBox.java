package res.menu;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import res.Item;
import res.Player;

public class InventoryBox extends Clickable {
	private LinkedList<Item> inventory;
	private BufferedImage dungeonBackground;
	private int width, height;

	public InventoryBox(int x, int y, BufferedImage image, String name) throws IOException {
		super(x, y, image, name);
		inventory = new LinkedList<Item>();
		dungeonBackground = ImageIO.read(this.getClass().getClassLoader().getResource("dungeon/inventoryBackground.png"));
		genImage(image);
		this.width = dungeonBackground.getWidth();
		this.height = dungeonBackground.getHeight();
	}
	
	public void addItem(Item i) {
		inventory.add(i);
		genImage(getImage());
	}
	
	public void removeItem(int index) {
		inventory.remove(index);
		genImage(getImage());
	}
	
	public void addInventory(Player player) {
		for(int i = 0; i < player.getItems(); i++) {
			inventory.add(player.getItem(i));
		}
	}
	
	public void genImage(BufferedImage image) {
		Graphics2D g = image.createGraphics();
		g.drawImage(dungeonBackground, this.getX(), this.getY(), null);
		
		int cnt = 1;
		for(int i = 0; i < inventory.size(); i++) {
			g.drawImage(inventory.get(i).getImage(), (width/8)*i, (height/4)*cnt, null);
			
			if(i > 7) {
				cnt = 2;
			}
		}
		
		
		
	}
	
	

}
