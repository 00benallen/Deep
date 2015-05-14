package res;

import java.awt.Graphics2D;
import java.util.logging.Level;

import main.Main;

public class DungeonDrawer {
	DungeonGUI dungeonGUI;
	
	public void draw(int curRoom, Dungeon curDungeon, Graphics2D g) {
		if(dungeonGUI != null) {
			dungeonGUI.draw(g);
		}
		else {
			if(Main.update.dungeonGenerated) {
				Main.log.log(Level.INFO, "Generating dungeon GUI");
				dungeonGUI = new DungeonGUI(0, 0, "dungeonGUI");
				Main.gMain.addMouseListener(dungeonGUI);
				Main.gMain.addKeyListener(dungeonGUI);
			}
		}
	}

}
