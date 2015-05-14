
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

import res.DungeonDrawer;
import res.ShopDrawer;

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
		
			init();
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
	
	private void init() {
		Main.log.log(Level.INFO, "Loading images");
	}
	
	/**
	 * Main command that calls functions to draw different aspects of the game
	 */
	public void draw() { 
		BufferedImage screen = new BufferedImage(GraphicsMain.WIDTH, GraphicsMain.HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) screen.getGraphics();
		drawBackground(g);
		if(Main.appState == Main.GAME_STATE) {
			if(Main.gameState == Main.GAME_SHOP) {
				sd.draw(g);
			}
			else if(Main.gameState == Main.GAME_DUNGEON) {
				dd.draw(Main.update.curRoom, Main.update.curDungeon, g);
			}
		}
		dblBuffer.add(screen);
		if(dblBuffer.size() == 2) {
			this.g.drawImage(dblBuffer.poll(), 0, 0, null);
		}
	}
	
	private void drawBackground(Graphics2D g) {
		Rectangle2D background = new Rectangle2D.Double(0, 0, GraphicsMain.WIDTH, GraphicsMain.HEIGHT);
		g.setColor(Color.CYAN);
		g.fill(background);
	}
	
	public Graphics2D getGraphics() {
		return g;
	}
	
	
}