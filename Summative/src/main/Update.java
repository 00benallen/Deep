package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import res.Dungeon;
import res.Enemy;
import res.Player;

/**
 * Update class for Trash Smash, updates at 60 ups, runs game logic
 * @author Ben Pinhorn
 *
 */
public class Update implements Runnable {
	
	//Thread resources
	public volatile ReentrantReadWriteLock lck = Main.lck;
	private Thread updateThread;
	
	//app resources
	public volatile static boolean running;
	private Scoreboard sc;
	
	//game resources
	public Dungeon curDungeon;
	public boolean dungeonGenerated;
	public Player player;
	
	/**
	 * Starts update thread
	 */
	public synchronized void start() {
		running = true;
		updateThread = new Thread(this, "Update Thread");
		updateThread.start();
	}
	
	/**
	 * Run loop for update thread, keeps its own UPS
	 */
	public void run() { 
		long lastTime = System.nanoTime();
		double nanoPerUpdate = 1000000000D/60D;
		double delta = 0D;
		player = new Player();
		try {
			sc = Scoreboard.load();
		} catch (ClassNotFoundException | IOException e) {
			sc = new Scoreboard();
		}
		if(Main.appState == Main.GAME_STATE) {
			while(running) {
				long now = System.nanoTime();
				delta += (now - lastTime) / nanoPerUpdate;
				lastTime = now;
				
				while(delta >= 1) {
					update();
					delta--;
				}
			}
		}
		if(running = false) {
			return;
		}
	}
	
	/**
	 * Terminates update thread
	 */
	public synchronized void stop() {
		running  = false;
		try {
			sc.save();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	 /**
	  * Generates a new dungeon
	  */
	public void generateDungeon() {
		try {
			curDungeon = new Dungeon();
			dungeonGenerated = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Executes all game actions
	 */
	private void update() {
		if(Main.appState == Main.GAME_STATE) {
			if(Main.updateStateChange) {
				if(Main.gameState == Main.GAME_SHOP) {
					dungeonGenerated = false;
					generateDungeon();
					Main.updateStateChange = false;
				}
			}
		}
	}
	 /**
	  * Runs a battle simulation, to determine whether the player or enemy wins
	  * @return battle won/lost
	  * @throws IOException
	  */
	public boolean runBattleSim() throws IOException {
		
		Enemy en = new Enemy();
		int score = 0;
		while(en.getHealth() > 0 && player.getHealth() > 0) { //alternates blows until loser
			if(player.getMagic() > 0) {
				score += en.hit(player.getStats());
				player.setMagic(player.getMagic() - 1);
			}
			else {
				sc.addScore(score);
				sc.save(); //saves if player runs out of magic
				return true;
			}
			player.hit(en.getDamage());
			score -= en.getDamage();
		}
		sc.addScore(score);
		
		if(en.getHealth() <= 0) {
			sc.save();
			return true;
		}
		else {
			sc.save();
			return false;
		}
	}
}
