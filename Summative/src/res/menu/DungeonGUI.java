package res.menu;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import res.Dungeon;
import res.Room;
import main.Main;

public class DungeonGUI extends GUI {
	private Dungeon curDungeon;
	private String textBuff;

	public DungeonGUI(int x, int y, String name) {
		super(x, y, name);
		try {
			curDungeon = Main.update.curDungeon;
			genGUI();
		} catch(IOException e) {
			
		}
		this.setCurPane("roomPane");
	}
	
	public void genGUI() throws IOException {
		BufferedImage dungeonBackground = ImageIO.read(this.getClass().getClassLoader().getResource("dungeon/dungeonBackground.png"));
		Pane pane = new Pane("roomPane", dungeonBackground);
		BufferedImage roomImageI = ImageIO.read(this.getClass().getClassLoader().getResource("dungeon/roomImage.png"));
		ImageBox roomImage = new ImageBox((width/16)*2, (height/16)*2, roomImageI, "roomImage");
		BufferedImage textBoxI = ImageIO.read(this.getClass().getClassLoader().getResource("dungeon/textBox.png"));
		TextBox textBox = new TextBox((width/16)*2, (height/2) + height/16,textBoxI, "textBox");
		Main.lck.readLock().lock();
		Room curRoom = curDungeon.getCurRoom();
		Main.lck.readLock().unlock();
		textBox.setText(curRoom.getDesc() + "\n" + curRoom.getDecisionText());
		BufferedImage toolBarI = ImageIO.read(this.getClass().getClassLoader().getResource("dungeon/toolBar.png"));
		ToolBar toolBar = new ToolBar(width/16, height - (height/16)*2, toolBarI, "toolBar");
		pane.add(toolBar);
		pane.add(roomImage);
		pane.add(textBox);
		addPane(pane);
	}
	
	public void updateUI() {
		ImageBox roomImage = null;
		TextBox textBox = null;
		for(int i = 0; i < getCurPane().getElements().size(); i++) {
			if(getCurPane().getElement(i) instanceof ImageBox) {
				roomImage = (ImageBox) getCurPane().getElement(i);
			}
			else if(getCurPane().getElement(i) instanceof TextBox) {
				textBox = (TextBox) getCurPane().getElement(i);
			}
		}
		
		Main.lck.readLock().lock();
		curDungeon = Main.update.curDungeon;
		Room curRoom = Main.update.curDungeon.getCurRoom();
		Main.lck.readLock().unlock();
		textBox.setText(curRoom.getDesc() + "\n" + curRoom.getDecisionText());
		
	}
	
	public void openInventory() throws IOException {
		BufferedImage invI = ImageIO.read(this.getClass().getClassLoader().getResource("dungeon/inventoryBackground.png"));
		InventoryBox inv = new InventoryBox((width/16)*2, (height/2) + height/16, invI, "invBox");
		Main.lck.readLock().lock();
		inv.addInventory(Main.update.player);
		Main.lck.readLock().unlock();
		textBuff = ((TextBox) this.getCurPane().getElement("textBox")).getText();
		this.getCurPane().remove("textBox");
		this.getCurPane().add(inv);
	}

	public void closeInventory() throws IOException {
		BufferedImage textBoxI = ImageIO.read(this.getClass().getClassLoader().getResource("dungeon/textBox.png"));
		TextBox textBox = new TextBox((width/16)*2, (height/2) + height/16,textBoxI, "textBox");
		textBox.setText(textBuff);
		this.getCurPane().remove("invBox");
		this.getCurPane().add(textBox);
	}
}
