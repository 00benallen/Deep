package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
 /**
  * Action listener, receives events from buttons in the main menu
  * @author Ben Pinhorn
  *
  */
public class ButtonListener implements ActionListener {
	 
	/**
	  * If start button is pressed, game is started
	  */
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Start")) {
			do {
				if(Loader.loaded) {
					Main.log.log(Level.INFO, "Starting game!");
					Main.startShop();
				}
			} while(!Loader.loaded);
				
		}
	}

}
