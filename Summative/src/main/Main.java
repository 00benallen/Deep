package main;


import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
	
	//app resources
	public static final int GAME_STATE = 1, MAIN_MENU_STATE = 2;
	public static final int GAME_SHOP = 0 , GAME_DUNGEON = 1, GAME_BATTLE = 2;
	public static GraphicsMain gMain;
	public static int appState = 0, gameState = 0;
	public static final ReentrantReadWriteLock lck = new ReentrantReadWriteLock();
	public static boolean isNew = true;
	public static Logger log;
	public static boolean renderStateChange, updateStateChange;
	
	//thread resources
	public static Update update;
	
	
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
	
	public static void startGame() {
		log.log(Level.INFO, "Starting game");
		appState = GAME_STATE;
		gameState = GAME_SHOP;
		update = new Update();
		update.start();
		gMain.startGame();
		renderStateChange = true;
		updateStateChange = true;
	}
	
	public static void startDungeon() {
		log.log(Level.INFO, "Starting dungeon");
		appState = GAME_STATE;
		gameState = GAME_DUNGEON;
		Main.update.generateDungeon();
		renderStateChange = true;
		updateStateChange = true;
	}
	
	public static void startBattle() {
		gameState = GAME_BATTLE;
		gMain.startBattle();
		renderStateChange = true;
		updateStateChange = true;
	}
	
	/**
	 * Exits game safely by terminating the threads, stopping the music player, and disposing the window
	 */
	public static void exit() {
		log.log(Level.INFO, "Exiting game");
		Update.running = false;
		gMain.window.dispose();
	}
}
