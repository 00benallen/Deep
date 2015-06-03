package main;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;

import javax.imageio.ImageIO;
 /**
  * Class that loads all resources for game
  * @author Ben Pinhorn
  *
  */
public class Loader implements Runnable {
	private Thread loadThread;
	public static boolean loaded;
	public static BufferedImage dungeonBackground, roomImageI, textBoxI, toolBarI, invI,
		smithButtonI, innButtonI, mysticButtonI, dungeonButtonI, shopBackground, 
		smithBackground, innBackground, mysticBackground, dungeonMenuBackground, enterButtonI, 
		itemImage, helmetImage, amuletImage, chestplateImage, leggingsImage, ringImage, 
		bootsImage;
	
	 /**
	  * Starts loader thread
	  */
	public synchronized void start() {
		loadThread = new Thread(this, "Update Thread");
		loadThread.start();
	}
	
	 /**
	  * Method loads all resources for game, on a separate thread
	  */
	public void run() {
		try {
			Main.log.log(Level.INFO, "Loading resources!");
			dungeonBackground = ImageIO.read(this.getClass().getClassLoader().getResource("dungeon/dungeonBackground.png"));
			roomImageI  = ImageIO.read(this.getClass().getClassLoader().getResource("dungeon/roomImage.png"));
			textBoxI = ImageIO.read(this.getClass().getClassLoader().getResource("dungeon/textBox.png"));
			toolBarI = ImageIO.read(this.getClass().getClassLoader().getResource("dungeon/toolBar.png"));
			invI = ImageIO.read(this.getClass().getClassLoader().getResource("dungeon/inventoryBackground.png"));
			smithButtonI = ImageIO.read(this.getClass().getClassLoader().getResource("shop/smithButton.png"));
			innButtonI = ImageIO.read(this.getClass().getClassLoader().getResource("shop/innButton.png"));
			mysticButtonI = ImageIO.read(this.getClass().getClassLoader().getResource("shop/mysticButton.png"));
			dungeonButtonI = ImageIO.read(this.getClass().getClassLoader().getResource("shop/dungeonButton.png"));
			shopBackground = ImageIO.read(this.getClass().getClassLoader().getResource("shop/shopBackground.png"));
			smithBackground = ImageIO.read(this.getClass().getClassLoader().getResource("shop/smithBackground.png"));
			innBackground = ImageIO.read(this.getClass().getClassLoader().getResource("shop/innBackground.png"));
			mysticBackground = ImageIO.read(this.getClass().getClassLoader().getResource("shop/mysticBackground.png"));
			dungeonMenuBackground = ImageIO.read(this.getClass().getClassLoader().getResource("shop/dungeonBackground.png"));
			enterButtonI = ImageIO.read(this.getClass().getClassLoader().getResource("shop/dungeonEnterButton.png"));
			itemImage = ImageIO.read(this.getClass().getClassLoader().getResource("dungeon/itemImage.png"));
			helmetImage = ImageIO.read(this.getClass().getClassLoader().getResource("dungeon/helmetImage.png"));
			amuletImage = ImageIO.read(this.getClass().getClassLoader().getResource("dungeon/amuletImage.png"));
			chestplateImage = ImageIO.read(this.getClass().getClassLoader().getResource("dungeon/chestplateImage.png"));
			leggingsImage = ImageIO.read(this.getClass().getClassLoader().getResource("dungeon/leggingsImage.png"));
			ringImage = ImageIO.read(this.getClass().getClassLoader().getResource("dungeon/ringImage.png"));
			bootsImage = ImageIO.read(this.getClass().getClassLoader().getResource("dungeon/bootsImage.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}		
		loaded = true;
	}
}
