package res.menu;

import java.awt.Graphics2D;
import java.util.logging.Level;

import res.Dungeon;
import main.Main;
 
/**
  * Stores the objects for drawing the dungeon screen and UI, triggers the draw chain
  * @author Ben Pinhorn
  *
  */
public class DungeonDrawer {
	DungeonGUI dungeonGUI;
	
	/**
	 * Generates UI objects if required, triggers draw chain
	 * @param g
	 */
	public void draw(int curRoom, Dungeon curDungeon, Graphics2D g) {
		if(dungeonGUI != null) {
			dungeonGUI.draw(g);
		}
		else {
			if(Main.update.dungeonGenerated) {
				Main.log.log(Level.INFO, "Generating dungeon GUI");
				dungeonGUI = new DungeonGUI(0, 0, "dungeonGUI");
				DungeonListener dl = new DungeonListener(dungeonGUI);
				Main.gMain.removeKeyListeners();
				Main.gMain.removeMouseListeners();
				Main.gMain.addMouseListener(dl);
				Main.gMain.addKeyListener(dl);
			}
		}
	}

}
