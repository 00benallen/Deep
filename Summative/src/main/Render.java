
package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Level;

import res.menu.DungeonDrawer;
import res.menu.ShopDrawer;
 /**
  * Render class for game, contains all draw objects, changes what to draw based on game state, runs on render thread
  * @author Ben Pinhorn
  *
  */
public class Render implements Runnable {
	//graphics resources
	private Graphics2D g;
	private Queue<BufferedImage> dblBuffer = new LinkedList<BufferedImage>();
	private ShopDrawer sd;
	private DungeonDrawer dd;
	
	//thread resources
	public volatile ReentrantReadWriteLock lck = Main.lck;
	
	/**
	 * Constructs the render object
	 * @param graphics
	 */
	public Render(Graphics g) {
		Main.log.log(Level.INFO, "Constructing render");
		this.g = (Graphics2D) g;
		sd = new ShopDrawer();
		dd = new DungeonDrawer();
	}

	/**
	 * Run method for render thread, triggers the draw list, keeps its own fps
	 */
	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double nanoPerUpdate = 1000000000D/50D;
		double delta = 0D;
			while(Update.running) {
				long now = System.nanoTime();
				delta += (now - lastTime) / nanoPerUpdate;
				lastTime = now;
				
				while(delta >= 1) {
					draw();
					delta--;
				}
			}
		
		if(Update.running = false) {
			return;
		}
	}
	
	/**
	 * Main command that calls functions to draw different part of the game, double buffers frame
	 */
	public void draw() { 
		BufferedImage screen = new BufferedImage(GraphicsMain.WIDTH, GraphicsMain.HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) screen.getGraphics(); 
		drawBackground(g);
		if(Main.appState == Main.GAME_STATE) {
			if(Main.gameState == Main.GAME_SHOP) { //if game is in shop mode
				sd.draw(g);
			}
			else if(Main.gameState == Main.GAME_DUNGEON) { //if game is in dungeon mode
				dd.draw(Main.update.player.getCurRoom(), Main.update.curDungeon, g);
			}
			
			if(Main.renderStateChange) { //resets graphical data when state is changed
					dd = new DungeonDrawer();
					sd = new ShopDrawer();
					Main.renderStateChange = false; //notifies itself that data has been reset
			}
		}
		dblBuffer.add(screen);
		if(dblBuffer.size() == 2) { //turns buffered drawing into double buffered drawing
			this.g.drawImage(dblBuffer.poll(), 0, 0, null);
		}
	}
	
	private void drawBackground(Graphics2D g) {
		Rectangle2D background = new Rectangle2D.Double(0, 0, GraphicsMain.WIDTH, GraphicsMain.HEIGHT);
		g.setColor(Color.CYAN);
		g.fill(background);
	}	
}