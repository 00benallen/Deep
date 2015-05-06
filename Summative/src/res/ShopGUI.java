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

	public ShopGUI(int x, int y, BufferedImage background, LinkedList<Pane> panes, String name) {
		super(x, y, background, panes, name);
		try {
			genGUI();
		} catch(IOException e) {
			Main.log.log(Level.SEVERE, "Cannot find shop gui images");
		}
	}
	
	public ShopGUI(int x, int y, BufferedImage background, String name) {
		super(x, y, background, name);
		try {
			genGUI();
		} catch(IOException e) {
			Main.log.log(Level.SEVERE, "Cannot find shop gui images");
		}
	}
	
	public void genGUI() throws IOException {
		createShopButtons();
		/*createSmithButtons();
		createMysticButtons();*/
		
	}
	
	public void createShopButtons() throws IOException {
		BufferedImage smithButtonI = ImageIO.read(this.getClass().getClassLoader().getResource("shop/smithButton.png"));
		Clickable smithButton = new Clickable((width/6), (height/3)*2, smithButtonI, "smithButton");
		BufferedImage innButtonI = ImageIO.read(this.getClass().getClassLoader().getResource("shop/innButton.png"));
		Clickable innButton = new Clickable((width/6)*3, (height/3)*2, innButtonI, "innButton");
		BufferedImage mysticButtonI = ImageIO.read(this.getClass().getClassLoader().getResource("shop/mysticButton.png"));
		Clickable mysticButton = new Clickable((width/6), (height/3)*2 + height/6, mysticButtonI, "mysticButton");
		BufferedImage dungeonButtonI = ImageIO.read(this.getClass().getClassLoader().getResource("shop/dungeonButton.png"));
		Clickable dungeonButton = new Clickable((width/6)*3, (height/3)*2 + height/6, dungeonButtonI, "dungeonButton");
		Pane pane = new Pane("shopButtons");
		pane.add(smithButton);
		pane.add(innButton);
		pane.add(mysticButton);
		pane.add(dungeonButton);
		addPane(pane);
		this.setCurPane(pane.getName());
	}
	
	public void mouseClicked(MouseEvent e) {
		for(int i = 0; i < getPanes().size(); i++) {
			for(int j = 0; j < getPanes().get(i).getElements().size(); i++) {
				if(getPanes().get(i).getElement(j).getBound().contains(e.getPoint())) {
					Clickable clicked = getPanes().get(i).getElement(j);
					if(clicked.getName().equals("smithButton")) {
						this.setCurPane("smithButtons");
					}
				}
			}
		}
	}
	
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
}
