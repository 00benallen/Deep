import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;



public class GraphicsMain {
	
	//Window variables
	public JFrame window = new JFrame("Summative");
	public final static int WIDTH = 1024;
	public final static int HEIGHT = 768;
	
	//Thread variables
	private Thread renderThread;
	public Render render;
	
	/**
	 * Constructor for graphics main, opens the window
	 * @param k
	 */
	public GraphicsMain() {
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		window.setResizable(false);
		window.pack();
		window.setFocusable(true);
		init();
	}
	
	/**
	 * Loads images for use in menus.
	 */
	private void init() {
	
	}
	
	/**
	 * Starts render thread 
	 */
	public synchronized void start() {
		renderThread = new Thread(render, "Render Thread");
		renderThread.start();
	}
	
	/**
	 * Generates the Main Menu (and clears any other display)
	 */
	public JPanel createContentPane() {
		return null;
	}
}
