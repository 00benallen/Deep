package res;

import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;

import javax.imageio.ImageIO;

import main.GraphicsMain;
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
		BufferedImage smithButtonI = ImageIO.read(this.getClass().getClassLoader().getResource("shop/smithButton.png"));
		Button smithButton = new Button((width/6), (height/3)*2 - height/6, smithButtonI, "smithButton");
		BufferedImage innButtonI = ImageIO.read(this.getClass().getClassLoader().getResource("shop/innButton.png"));
		Button innButton = new Button((width/6)*3, (height/3)*2 - height/6, innButtonI, "innButton");
		BufferedImage mysticButtonI = ImageIO.read(this.getClass().getClassLoader().getResource("shop/mysticButton.png"));
		Button mysticButton = new Button((width/6), (height/3)*2, mysticButtonI, "mysticButton");
		BufferedImage dungeonButtonI = ImageIO.read(this.getClass().getClassLoader().getResource("shop/dungeonButton.png"));
		Button dungeonButton = new Button((width/6)*3, (height/3)*2, dungeonButtonI, "dungeonButton");
		BufferedImage shopBackground = ImageIO.read(this.getClass().getClassLoader().getResource("shop/shopBackground.png"));
		Pane pane = new Pane("townPane", shopBackground);
		pane.add(smithButton);
		pane.add(innButton);
		pane.add(mysticButton);
		pane.add(dungeonButton);
		addPane(pane);
	}
	
	public void createSmithPane() throws IOException {
		BufferedImage smithBackground = ImageIO.read(this.getClass().getClassLoader().getResource("shop/smithBackground.png"));
		Pane pane = new Pane("smithPane", smithBackground);
		addPane(pane);
	}
	
	public void createInnPane() throws IOException {
		BufferedImage innBackground = ImageIO.read(this.getClass().getClassLoader().getResource("shop/innBackground.png"));
		Pane pane = new Pane("innPane", innBackground);
		addPane(pane);
	}
	
	public void createMysticPane() throws IOException {
		BufferedImage mysticBackground = ImageIO.read(this.getClass().getClassLoader().getResource("shop/mysticBackground.png"));
		Pane pane = new Pane("mysticPane", mysticBackground);
		addPane(pane);
	}
	
	public void createDungeonPane() throws IOException {
		BufferedImage dungeonBackground = ImageIO.read(this.getClass().getClassLoader().getResource("shop/dungeonBackground.png"));
		Pane pane = new Pane("dungeonPane", dungeonBackground);
		BufferedImage enterButtonI = ImageIO.read(this.getClass().getClassLoader().getResource("shop/dungeonEnterButton.png"));
		Button enterButton = new Button((width/3), (height/3)*2, enterButtonI, "dungeonEnterButton");
		pane.add(enterButton);
		addPane(pane);
	}
	
	public void mouseClicked(MouseEvent e) {
		for(int i = 0; i < getCurPane().getElements().size(); i++) {
			if(getCurPane().getElement(i).getBound().contains(e.getPoint())) {
				if(getCurPane().getName().equals("townPane")) {
					scanTownButtons(i);
					break;
				}
				if(getCurPane().getName().equals("dungeonPane")) {
					scanDungeonButtons(i);
					break;
				}
						
			}
		}
	}
	
	private void scanTownButtons(int i) {
		if(getCurPane().getElement(i).getName().equals("smithButton")) {
			this.setCurPane("smithPane");
		}
		else if(getCurPane().getElement(i).getName().equals("innButton")) {
			this.setCurPane("innPane");
		}
		else if(getCurPane().getElement(i).getName().equals("mysticButton")) {
			this.setCurPane("mysticPane");
		}
		else if(getCurPane().getElement(i).getName().equals("dungeonButton")) {
			this.setCurPane("dungeonPane");
		}
	}
	
	private void scanDungeonButtons(int i) {
		if(getCurPane().getElement(i).getName().equals("dungeonEnterButton")) {
			Main.startDungeon();
		}
	}
	
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
}
