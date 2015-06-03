package res.menu;

import java.awt.Graphics2D;
import java.util.LinkedList;

import main.GraphicsMain;
 /**
  * Abstract class defining a GUI object
  * @author Ben Pinhorn
  *
  */
public abstract class GUI {
	private LinkedList<Pane> panes;
	private Pane curPane;
	private String name, curPaneName;
	private int x, y;
	public int width = GraphicsMain.WIDTH, height = GraphicsMain.HEIGHT;
	 
	/**
	  * Default constructor for GUI
	  * @param x
	  * @param y
	  * @param name
	  */
	public GUI(int x, int y, String name) {
		this.x = x;
		this.y = y;
		this.name = name;
		panes = new LinkedList<Pane>();
	}
	 /**
	  * Constructor of GUI with Panes already made
	  * @param x
	  * @param y
	  * @param panes
	  * @param name
	  */
	public GUI(int x, int y, LinkedList<Pane> panes, String name) {
		this.x = x;
		this.y = y;
		this.name = name;
		this.panes = panes;
	}
	
	public String getName() {return name;}
	public void addPane(Pane pane) {panes.add(pane);}
	public int getX() {return x;}
	public int getY() {return y;}
	public LinkedList<Pane> getPanes() {return panes;}
	public String getCurPaneName() {return curPaneName;}
	public Pane getCurPane() {return curPane;}
	 
	/**
	 * Calls the draw chain for all Panes in GUI
	 * @param g
	 */
	public void draw(Graphics2D g) {
		for(int i = 0; i < panes.size(); i++) {
			if(panes.get(i).getName().equals(curPaneName)) {
				panes.get(i).draw(g);
				break;
			}
		}
		
	}
	
	/**
	 * Finds and removes a pane from the GUI
	 * @param name
	 */
	public void removePane(String name) {
		for(int i = 0; i < panes.size(); i++) {
			if(panes.get(i).getName().equals(name)) {
				panes.remove(i);
			}
		}
	}
	
	/**
	 * Sets the current pane being drawn with active Drawables
	 * @param curPaneName
	 */
	public void setCurPane(String curPaneName) {
		this.curPaneName = curPaneName;
		
		for(int i = 0; i < panes.size(); i++) {
			if(panes.get(i).getName().equals(this.curPaneName)) {
				curPane = panes.get(i);
			}
		}
	}
	
	
}
