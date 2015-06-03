package res.menu;

import java.awt.Graphics2D;
import java.util.logging.Level;

import main.Main;

/**
 * Contains all shop UI objects, triggers shop draw chain
 * @author Ben Pinhorn
 *
 */
public class ShopDrawer {
	ShopGUI shopGUI = null;
	
	/**
	 * Generates UI objects if required, triggers draw chain
	 * @param g
	 */
	public void draw(Graphics2D g) {
		if(shopGUI != null) {
			shopGUI.draw(g);
		}
		else {
			Main.log.log(Level.INFO, "Generating shop GUI");
			shopGUI = new ShopGUI(0, 0, "shopGUI");
			ShopListener sl = new ShopListener(shopGUI);
			Main.gMain.removeMouseListeners();
			Main.gMain.addMouseListener(sl);
		}
	}
}
