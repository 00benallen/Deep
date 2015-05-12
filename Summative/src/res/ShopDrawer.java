package res;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;

import javax.imageio.ImageIO;

import main.Main;

public class ShopDrawer {
	ShopGUI shopGUI = null;
	
	
	public void draw(Graphics2D g) {
		if(shopGUI != null) {
			shopGUI.draw(g);
		}
		else {
			Main.log.log(Level.INFO, "Generating shop GUI");
			shopGUI = new ShopGUI(0, 0, "shopGUI");
			Main.gMain.addMouseListener(shopGUI);
		}
		
	}
}