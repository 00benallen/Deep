package res.menu;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.logging.Level;

import main.Main;
import res.Item;
 
/**
  * Listens for key presses and button clicks on dungeon screen
  * @author Ben Pinhorn
  *
  */
public class DungeonListener implements KeyListener, MouseListener{
	private DungeonGUI dg;
	private boolean invOpen;
	 
	/**
	 * Default constructor for DungeonListener
	 * @param dg
	 */
	public DungeonListener(DungeonGUI dg) {
		this.dg = dg;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(dg.getInv().getBound().contains(e.getPoint())) {
			for(int i = 0; i < Main.update.player.getItems(); i++) {
				if(dg.getInv().getBounds(i).contains(e.getPoint())) {
					if(Main.update.player.equip(i)) {
						dg.getInv().removeItem(i);
					}
					break;
				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void keyPressed(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {
		if(e.getKeyChar() == '1') {
			if(dg.curDungeon.getCurRoom().getDecision(0) == 1) {
				right();
			}
			else if(dg.curDungeon.getCurRoom().getDecision(0) == 2) {
				left();
			}
			else if(dg.curDungeon.getCurRoom().getDecision(0) == 3) {
				openChest();
				dg.curDungeon.getCurRoom().removeDecision(0);
			}
			else if(dg.curDungeon.getCurRoom().getDecision(0) == 4) {
				startBattle();
				dg.curDungeon.getCurRoom().removeDecision(0);
			}
			else if(dg.curDungeon.getCurRoom().getDecision(0) == 6) {
				leaveDungeon();
			}
			e.consume();
		}
		else if(e.getKeyChar() == '2') {
			if(dg.curDungeon.getCurRoom().getDecision(1) == 1) {
				right();
			}
			else if(dg.curDungeon.getCurRoom().getDecision(1) == 2) {
				left();
			}
			else if(dg.curDungeon.getCurRoom().getDecision(1) == 3) {
				openChest();
				dg.curDungeon.getCurRoom().removeDecision(1);
			}
			else if(dg.curDungeon.getCurRoom().getDecision(1) == 4) {
				startBattle();
				dg.curDungeon.getCurRoom().removeDecision(1);
			}
			else if(dg.curDungeon.getCurRoom().getDecision(1) == 6) {
				leaveDungeon();
			}
			e.consume();
		}
		else if(e.getKeyChar() == '3') {
			if(dg.curDungeon.getCurRoom().getDecision(2) == 1) {
				right();
			}
			else if(dg.curDungeon.getCurRoom().getDecision(2) == 2) {
				left();
			}
			else if(dg.curDungeon.getCurRoom().getDecision(2) == 3) {
				openChest();
				dg.curDungeon.getCurRoom().removeDecision(2);
			}
			else if(dg.curDungeon.getCurRoom().getDecision(2) == 4) {
				startBattle();
				dg.curDungeon.getCurRoom().removeDecision(2);
			}
			else if(dg.curDungeon.getCurRoom().getDecision(2) == 6) {
				leaveDungeon();
			}
			e.consume();
		}
		else if(e.getKeyChar() == 'i') {
			if(!invOpen) {
				try {
					dg.openInventory();
					invOpen = true;
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			else {
				try {
					dg.closeInventory();
					invOpen = false;
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
		}
	}
	
	private void right() {
		Main.lck.writeLock().lock();
		dg.curDungeon.setCurRoomNum(dg.curDungeon.getCurRoom().right.roomNum);
		dg.updateUI();
		Main.lck.writeLock().unlock();
	}
	
	private void left() {
		Main.lck.writeLock().lock();
		dg.curDungeon.setCurRoomNum(dg.curDungeon.getCurRoom().left.roomNum);
		dg.updateUI();
		Main.lck.writeLock().unlock();
	}
	
	private void openChest() {
		Main.lck.writeLock().lock();
		Item chestItem = null;
		try {
			chestItem = dg.curDungeon.genChestItem();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Main.update.player.addItem(chestItem);
		TextBox textBox = null;
		for(int i = 0; i < dg.getCurPane().getElements().size(); i++) {
			if(dg.getCurPane().getElement(i) instanceof TextBox) {
				textBox = (TextBox) dg.getCurPane().getElement(i);
			}
		}
		dg.updateUI();
		textBox.setText(textBox.getText() + "/mYou have found a " + chestItem.getName());
		Main.lck.writeLock().unlock();
	}
	
	private void startBattle() {
		Main.lck.writeLock().lock();
		Main.log.log(Level.INFO, "Starting battle!");
		try {
			if(!Main.update.runBattleSim()) {
				Main.startShop();
			}
			else {
				TextBox textBox = null;
				for(int i = 0; i < dg.getCurPane().getElements().size(); i++) {
					if(dg.getCurPane().getElement(i) instanceof TextBox) {
						textBox = (TextBox) dg.getCurPane().getElement(i);
					}
				}
				dg.updateUI();
				textBox.setText(textBox.getText() + "/mYou have won!");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		Main.lck.writeLock().unlock();
	}
	
	private void leaveDungeon() {
		Main.lck.writeLock().lock();
		Main.startShop();
		Main.lck.writeLock().unlock();
	}
}