package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;

public class ButtonListener implements ActionListener {

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Start")) {
			do {
				if(Loader.loaded) {
					Main.log.log(Level.INFO, "Starting game!");
					Main.startGame();
				}
			} while(!Loader.loaded);
				
		}
	}

}
