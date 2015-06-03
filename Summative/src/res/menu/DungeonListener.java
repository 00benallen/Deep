package res.menu;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.logging.Level;

import main.Main;
import res.Item;

public class DungeonListener implements KeyListener, MouseListener{
	private DungeonGUI dg;
	private boolean invOpen;
	
	public DungeonListener(DungeonGUI dg) {
		Main.lck.readLock().lock();
		Main.lck.readLock().unlock();
		this.dg = dg;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(dg.getInv().getBound().contains(e.getPoint())) {
			for(int i = 0; i < Main.update.player.getItems(); i++) {
				if(dg.getInv().getBounds(i).contains(e.getPoint())) {
					Main.update.player.equip(i);
					dg.getInv().removeItem(i);
					break;
				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if(e.getKeyChar() == '1') {
			if(right(0)) {
				return;
			}
			if(left(0)) {
				return;
			}
			if(openChest(0)) {
				return;
			}
			if(startBattle(0)) {
				return;
			}
			e.consume();
		}
		else if(e.getKeyChar() == '2') {
			if(right(1)) {
				return;
			}
			if(left(1)) {
				return;
			}
			if(openChest(1)) {
				return;
			}
			if(startBattle(1)) {
				return;
			}
			e.consume();
		}
		else if(e.getKeyChar() == '3') {
			if(right(2)) {
				return;
			}
			if(left(2)) {
				return;
			}
			if(openChest(2)) {
				return;
			}
			if(startBattle(2)) {
				return;
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
	
	public boolean right(int key) {
		if(dg.curDungeon.getCurRoom().getDecision(key) == 1) {
			Main.lck.writeLock().lock();
			dg.curDungeon.setCurRoomNum(dg.curDungeon.getCurRoom().right.roomNum);
			dg.updateUI();
			Main.lck.writeLock().unlock();
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public boolean left(int key) {
		if(dg.curDungeon.getCurRoom().getDecision(key) == 2) {
			Main.lck.writeLock().lock();
			dg.curDungeon.setCurRoomNum(dg.curDungeon.getCurRoom().left.roomNum);
			dg.updateUI();
			Main.lck.writeLock().unlock();
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public boolean openChest(int key) {
		if(dg.curDungeon.getCurRoom().getDecision(key) == 3) {
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
			dg.curDungeon.getCurRoom().removeDecision(key);
			dg.updateUI();
			textBox.setText(textBox.getText() + "/mYou have found a " + chestItem.getName());
			Main.lck.writeLock().unlock();
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean startBattle(int key) {
		if(dg.curDungeon.getCurRoom().getDecision(key) == 4) {
			Main.lck.writeLock().lock();
			Main.log.log(Level.INFO, "Starting battle!");
			try {
				if(!Main.update.runBattleSim()) {
					Main.startShop();
				}
				else {
					dg.curDungeon.getCurRoom().removeDecision(key);
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Main.lck.writeLock().unlock();
			return true;
		}
		else {
			return false;
		}
	}
}