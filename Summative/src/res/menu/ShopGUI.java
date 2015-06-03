package res.menu;

import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;

import main.GraphicsMain;
import main.Loader;
import main.Main;

/**
 * GUI object for shop screen
 * @author Ben Pinhorn
 *
 */
public class ShopGUI extends GUI {
	int width = GraphicsMain.WIDTH, height = GraphicsMain.HEIGHT;

	/**
	 * Constructor for ShopGUI with panes already generated
	 * @param x
	 * @param y
	 * @param panes
	 * @param name
	 */
	public ShopGUI(int x, int y, LinkedList<Pane> panes, String name) {
		super(x, y, panes, name);
	}
	
	/**
	 * Default constructor for ShopGUI
	 * @param x
	 * @param y
	 * @param name
	 */
	public ShopGUI(int x, int y, String name) {
		super(x, y, name);
		try {
			genGUI();
		} catch(IOException e) {
			Main.log.log(Level.SEVERE, "Cannot find shop gui images");
		}
		this.setCurPane("townPane");
	}
	
	private void genGUI() throws IOException {
		createShopPane();
		createDungeonPane();
	}
	
	private void createShopPane() throws IOException {
		Button dungeonButton = new Button((width/6)*3, (height/3)*2, Loader.dungeonButtonI, "dungeonButton");
		Pane pane = new Pane("townPane", Loader.shopBackground);
		pane.add(dungeonButton);
		addPane(pane);
	}
	
	private void createDungeonPane() throws IOException {
		Pane pane = new Pane("dungeonPane", Loader.dungeonMenuBackground);
		Button enterButton = new Button((width/3), (height/3)*2, Loader.enterButtonI, "dungeonEnterButton");
		pane.add(enterButton);
		addPane(pane);
	}
}