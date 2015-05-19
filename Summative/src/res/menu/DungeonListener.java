package res.menu;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import main.Main;
import res.Dungeon;
import res.Item;

public class DungeonListener implements KeyListener, MouseListener{
	Dungeon curDungeon;
	DungeonGUI dg;
	
	public DungeonListener(DungeonGUI dg) {
		Main.lck.readLock().lock();
		curDungeon = Main.update.curDungeon;
		Main.lck.readLock().unlock();
		this.dg = dg;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
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
			e.consume();
		}
	}
	
	public boolean right(int key) {
		if(curDungeon.getCurRoom().getDecision(key) == 1) {
			Main.lck.writeLock().lock();
			curDungeon.setCurRoomNum(curDungeon.getCurRoom().right.roomNum);
			dg.updateUI();
			Main.lck.writeLock().unlock();
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public boolean left(int key) {
		if(curDungeon.getCurRoom().getDecision(key) == 2) {
			Main.lck.writeLock().lock();
			curDungeon.setCurRoomNum(curDungeon.getCurRoom().left.roomNum);
			dg.updateUI();
			Main.lck.writeLock().unlock();
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public boolean openChest(int key) {
		if(curDungeon.getCurRoom().getDecision(key) == 3) {
			Main.lck.writeLock().lock();
			Item chestItem = curDungeon.genChestItem();
			Main.update.player.addItem(chestItem);
			TextBox textBox = null;
			for(int i = 0; i < dg.getCurPane().getElements().size(); i++) {
				if(dg.getCurPane().getElement(i) instanceof TextBox) {
					textBox = (TextBox) dg.getCurPane().getElement(i);
				}
			}
			curDungeon.getCurRoom().removeDecision(key);
			dg.updateUI();
			textBox.setText(textBox.getText() + "/mYou have found a " + chestItem.getName());
			Main.lck.writeLock().unlock();
			return true;
		}
		else {
			return false;
		}
	}
}