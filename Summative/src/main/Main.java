package main;


import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Level;
import java.util.logging.Logger;
 /**
  * Main class for game, starts all threads, generates all game structure and threading objects, stores game and app states
  * @author Ben Pinhorn
  *
  */
public class Main {
	
	//app resources
	public static final int GAME_STATE = 1, MAIN_MENU_STATE = 2;
	public static final int GAME_SHOP = 0 , GAME_DUNGEON = 1;
	public static GraphicsMain gMain;
	public static int appState = 0, gameState = 0;
	public static final ReentrantReadWriteLock lck = new ReentrantReadWriteLock(); //used to maintain concurrency
	public static boolean isNew = true;
	public static Logger log;
	
	//thread resources
	public static Update update;
	public static boolean renderStateChange, updateStateChange; //tells update and render threads if there has been a state change
	
	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args) {
		log = Logger.getLogger("Deep.main");
		log.log(Level.INFO, "Starting main menu");
		appState = MAIN_MENU_STATE;
		log.log(Level.INFO, "Starting graphics");
		Loader l = new Loader();
		l.start();
		gMain = new GraphicsMain();
		gMain.genMenu();
	}
	
	 /**
	  * Changes game state to shop state, if first time, also starts game threads
	  */
	public static void startShop() {
		log.log(Level.INFO, "Starting game");
		if(appState == MAIN_MENU_STATE) {
			appState = GAME_STATE;
		}
		gameState = GAME_SHOP;
		if(update == null) { //only runs on startup of game
			update = new Update();
			if(!Update.running) {
				update.start();
				gMain.startGame();
			}
		}
		renderStateChange = true;
		updateStateChange = true;
	}
	 /**
	  * Changes game state to dungeon state
	  */
	public static void startDungeon() {
		log.log(Level.INFO, "Starting dungeon");
		appState = GAME_STATE;
		gameState = GAME_DUNGEON;
		Main.update.generateDungeon();
		renderStateChange = true;
		updateStateChange = true;
	}
	
	/**
	 * Exits game safely by terminating the threads, and disposing the window
	 */
	public static void exit() {
		log.log(Level.INFO, "Exiting game");
		if(Update.running) { //prevents null pointer if exit from menu
			update.stop();
		}
		gMain.window.dispose();
	}
}
