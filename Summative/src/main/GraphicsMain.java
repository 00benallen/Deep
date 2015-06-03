package main;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GraphicsMain {
	
	//Window variables
	public JFrame window = new JFrame("Deep");
	public final static int WIDTH = 1024;
	public final static int HEIGHT = 768;
	private int appState;
	private ButtonListener bl;
	
	//Thread variables
	private Thread renderThread;
	public static Render render;
	
	/**
	 * Constructor for graphics main, opens the window
	 * @param k
	 */
	public GraphicsMain() {
		Main.log.log(Level.INFO, "Generating window");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		window.setResizable(false);
		window.pack();
		window.setFocusable(true);
		window.setVisible(true);
		appState = Main.appState;
		bl = new ButtonListener();
	}
	
	public void addMouseListener(MouseListener l) {
		Main.log.log(Level.INFO, "Adding mouse listener to window!");
		window.addMouseListener(l);
	}
	
	public void addKeyListener(KeyListener l) {
		Main.log.log(Level.INFO, "Adding key listener to window!");
		window.addKeyListener(l);
	}
	
	public void startGame() {
		Main.log.log(Level.INFO, "Starting game graphics");
		appState = Main.appState;
		window.setContentPane(genGamePane());
		window.revalidate();
		render = new Render(window.getContentPane().getGraphics());
		start();
	}
	
	public JPanel genGamePane() {
		JPanel gamePane = new JPanel();
		gamePane.setSize(GraphicsMain.WIDTH, GraphicsMain.HEIGHT);
		gamePane.setLocation(0, 0);
		return gamePane;
	}
	
	public void startBattle() {
		this.removeMouseListeners();
		this.removeKeyListeners();
	}
	
	/**
	 * Starts render thread 
	 */
	public synchronized void start() {
		Main.log.log(Level.INFO, "Starting render thread");
		renderThread = new Thread(render, "Render Thread");
		renderThread.start();
	}
	
	/**
	 * Generates the Main Menu (and clears any other display)
	 */
	public void genMenu() {
		if(appState == Main.MAIN_MENU_STATE) {
			window.setContentPane(genMainMenu());
		}
	}
	
	public JPanel genMainMenu() {
		Main.log.log(Level.INFO, "Creating main menu");
		JPanel mainPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		JButton startButton = new JButton("Start");
		startButton.setVisible(true);
		startButton.setActionCommand("Start");
		startButton.addActionListener(bl);
		
		c.gridheight = 1;
		c.gridwidth = 3;
		c.anchor = GridBagConstraints.CENTER;
		mainPanel.add(startButton, c);
		
		return mainPanel;
	}
	
	public void removeKeyListeners() {
		for(int i = 0; i < window.getKeyListeners().length; i++) {
			window.removeKeyListener(window.getKeyListeners()[i]);
		}
	}
	
	public void removeMouseListeners() {
		for(int i = 0; i < window.getMouseListeners().length; i++) {
			window.removeMouseListener(window.getMouseListeners()[i]);
		}
	}
}
