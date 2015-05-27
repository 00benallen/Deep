package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener {

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Start")) {
			do {
				if(Loader.loaded) {
					Main.startGame();
				}
			} while(!Loader.loaded);
				
		}
	}

}
