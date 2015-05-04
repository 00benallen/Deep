

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Main {
	
	//game variables should not be stored here, for game logic and updates, go to Update.java
	
	//app resources
	public static final int MENU_BUILD_STATE = 0, GAME_STATE = 1, MENU_STATE = 2;
	public static GraphicsMain gMain;
	public static int appState = 0;
	public static final ReentrantReadWriteLock lck = new ReentrantReadWriteLock();
	public static boolean isNew = true;
	
	//thread resources
	public static Update update;
	
	/**
	 * Builds game, can be used to reset game state
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) {
		if(appState == GAME_STATE) {
			update = new Update();
			update.start();
			init();
		}
		else if(appState == MENU_BUILD_STATE) {
			if(isNew) { //stops game from rebuilding entire thing if it is already running
				gMain = new GraphicsMain();
				isNew = false;
			}
			gMain.createContentPane();
			appState = MENU_STATE;
		}
	}
	
	private static void init() {
		gMain.start();
	}
	
	/**
	 * Exits game safely by terminating the threads, stopping the music player, and disposing the window
	 */
	public static void exit(){
		Update.running = false;
		gMain.window.dispose();
		
	}
}
