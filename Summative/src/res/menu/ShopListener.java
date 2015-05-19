package res.menu;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import main.Main;

public class ShopListener implements MouseListener {
	ShopGUI sg;
	
	public ShopListener(ShopGUI sg) {
		this.sg = sg;
	}
	
	public void mouseClicked(MouseEvent e) {
		for(int i = 0; i < sg.getCurPane().getElements().size(); i++) {
			if(sg.getCurPane().getElement(i).getBound().contains(e.getPoint())) {
				if(sg.getCurPane().getName().equals("townPane")) {
					scanTownButtons(i);
					break;
				}
				if(sg.getCurPane().getName().equals("dungeonPane")) {
					scanDungeonButtons(i);
					break;
				}
						
			}
		}
	}
	
	private void scanTownButtons(int i) {
		if(sg.getCurPane().getElement(i).getName().equals("smithButton")) {
			sg.setCurPane("smithPane");
		}
		else if(sg.getCurPane().getElement(i).getName().equals("innButton")) {
			sg.setCurPane("innPane");
		}
		else if(sg.getCurPane().getElement(i).getName().equals("mysticButton")) {
			sg.setCurPane("mysticPane");
		}
		else if(sg.getCurPane().getElement(i).getName().equals("dungeonButton")) {
			sg.setCurPane("dungeonPane");
		}
	}
	
	private void scanDungeonButtons(int i) {
		if(sg.getCurPane().getElement(i).getName().equals("dungeonEnterButton")) {
			Main.startDungeon();
		}
	}
	
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
}
