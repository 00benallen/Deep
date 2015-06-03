package res.menu;
import java.io.IOException;

import res.Dungeon;
import res.Room;
import main.Loader;
import main.Main;
 
/**
  * GUI that generates and stores all of the UI objects for the dungeon screen
  * @author Ben Pinhorn
  *
  */
public class DungeonGUI extends GUI {
	public Dungeon curDungeon;
	private String textBuff; //buffers text in text box, so inventory box can be drawn over it
	private InventoryBox inv;

	public DungeonGUI(int x, int y, String name) {
		super(x, y, name);
		try {
			curDungeon = Main.update.curDungeon;
			genGUI();
		} catch(IOException e) {
			
		}
		this.setCurPane("roomPane");
	}
	
	private void genGUI() throws IOException {
		Pane pane = new Pane("roomPane", Loader.dungeonBackground);
		ImageBox roomImage = new ImageBox((width/16)*2, (height/16)*2, Loader.roomImageI, "roomImage");
		TextBox textBox = new TextBox((width/16)*2, (height/2) + height/16, Loader.textBoxI, "textBox");
		Main.lck.readLock().lock();
		Room curRoom = curDungeon.getCurRoom();
		Main.lck.readLock().unlock();
		textBox.setText(curRoom.getDesc() + "\n" + curRoom.getDecisionText());
		ToolBar toolBar = new ToolBar(width/16, height - (height/16)*2, Loader.toolBarI, "toolBar");
		pane.add(toolBar);
		pane.add(roomImage);
		pane.add(textBox);
		addPane(pane);
		inv = new InventoryBox((width/16)*2, (height/2) + height/16, Loader.invI, "invBox");
	}
	
	/**
	 * Updates the user interface, if text or image changes occur
	 */
	public void updateUI() {
		TextBox textBox = null;
		for(int i = 0; i < getCurPane().getElements().size(); i++) {
			if(getCurPane().getElement(i) instanceof TextBox) {
				textBox = (TextBox) getCurPane().getElement(i);
			}
		}
		
		Main.lck.readLock().lock();
		curDungeon = Main.update.curDungeon;
		Room curRoom = curDungeon.getCurRoom();
		Main.lck.readLock().unlock();
		textBox.setText(curRoom.getDesc() + "\n" + curRoom.getDecisionText());
		
	}
	 
	/**
	  * Displays inventory box
	  * @throws IOException
	  */
	public void openInventory() throws IOException {
		Main.lck.readLock().lock();
		inv.clearInventory();
		inv.addInventory(Main.update.player);
		Main.lck.readLock().unlock();
		textBuff = ((TextBox) this.getCurPane().getElement("textBox")).getText();
		this.getCurPane().remove("textBox");
		this.getCurPane().add(inv);
	}
	
	/**
	 * Closes inventory box
	 * @throws IOException
	 */
	public void closeInventory() throws IOException {
		TextBox textBox = new TextBox((width/16)*2, (height/2) + height/16, Loader.textBoxI, "textBox");
		textBox.setText(textBuff);
		this.getCurPane().remove("invBox");
		this.getCurPane().add(textBox);
	}
	 /**
	  * Returns the inventory box
	  * @return inventory box
	  */
	public InventoryBox getInv() {return inv;}
}
