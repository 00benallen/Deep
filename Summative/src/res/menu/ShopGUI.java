package res.menu;

import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;

import main.GraphicsMain;
import main.Loader;
import main.Main;

public class ShopGUI extends GUI {
	int width = GraphicsMain.WIDTH, height = GraphicsMain.HEIGHT;

	public ShopGUI(int x, int y, LinkedList<Pane> panes, String name) {
		super(x, y, panes, name);
		try {
			genGUI();
		} catch(IOException e) {
			Main.log.log(Level.SEVERE, "Cannot find shop gui images");
		}
		this.setCurPane("townPane");
	}
	
	public ShopGUI(int x, int y, String name) {
		super(x, y, name);
		try {
			genGUI();
		} catch(IOException e) {
			Main.log.log(Level.SEVERE, "Cannot find shop gui images");
		}
		this.setCurPane("townPane");
	}
	
	public void genGUI() throws IOException {
		createShopPane();
		createSmithPane();
		createInnPane();
		createMysticPane();
		createDungeonPane();
	}
	
	public void createShopPane() throws IOException {
		Button smithButton = new Button((width/6), (height/3)*2 - height/6, Loader.smithButtonI, "smithButton");
		Button innButton = new Button((width/6)*3, (height/3)*2 - height/6, Loader.innButtonI, "innButton");
		Button mysticButton = new Button((width/6), (height/3)*2, Loader.mysticButtonI, "mysticButton");
		Button dungeonButton = new Button((width/6)*3, (height/3)*2, Loader.dungeonButtonI, "dungeonButton");
		Pane pane = new Pane("townPane", Loader.shopBackground);
		pane.add(smithButton);
		pane.add(innButton);
		pane.add(mysticButton);
		pane.add(dungeonButton);
		addPane(pane);
	}
	
	public void createSmithPane() throws IOException {
		Pane pane = new Pane("smithPane", Loader.smithBackground);
		addPane(pane);
	}
	
	public void createInnPane() throws IOException {
		Pane pane = new Pane("innPane", Loader.innBackground);
		addPane(pane);
	}
	
	public void createMysticPane() throws IOException {
		Pane pane = new Pane("mysticPane", Loader.mysticBackground);
		addPane(pane);
	}
	
	public void createDungeonPane() throws IOException {
		Pane pane = new Pane("dungeonPane", Loader.dungeonMenuBackground);
		Button enterButton = new Button((width/3), (height/3)*2, Loader.enterButtonI, "dungeonEnterButton");
		pane.add(enterButton);
		addPane(pane);
	}
}