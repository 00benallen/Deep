package main;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.logging.Level;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import res.DeepButton;



public class GraphicsMain {
	
	//Window variables
	public JFrame window = new JFrame("Deep");
	public final static int WIDTH = 1024;
	public final static int HEIGHT = 768;
	private int appState;
	private ButtonListener bl;
	
	//Thread variables
	private Thread renderThread;
	public Render render;
	
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
	
	public void startGame() {
		Main.log.log(Level.INFO, "Starting game graphics");
		appState = Main.appState;
		window.setContentPane(genGamePane());
		window.revalidate();
		render = new Render(window.getGraphics());
		start();
	}
	
	public JPanel genGamePane() {
		JPanel gamePane = new JPanel();
		return gamePane;
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
		
		//JLabel background = new JLabel();
		//background.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("")));
		
		DeepButton startButton = new DeepButton("Start");
		startButton.setActionCommand("Start");
		startButton.addActionListener(bl);
		
		c.gridheight = 1;
		c.gridwidth = 3;
		c.anchor = GridBagConstraints.CENTER;
		mainPanel.add(startButton, c);
		
		return mainPanel;
	}
}
