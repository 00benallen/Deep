package res.menu;

import java.awt.Graphics2D;
import java.awt.event.MouseListener;
import java.util.LinkedList;

import main.GraphicsMain;

public abstract class GUI {
	private LinkedList<Pane> panes;
	private Pane curPane;
	private String name, curPaneName;
	private int x, y;
	public int width = GraphicsMain.WIDTH, height = GraphicsMain.HEIGHT;
	
	public GUI(int x, int y, String name) {
		this.x = x;
		this.y = y;
		this.name = name;
		panes = new LinkedList<Pane>();
	}
	
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
	
	public void draw(Graphics2D g) {
		for(int i = 0; i < panes.size(); i++) {
			if(panes.get(i).getName().equals(curPaneName)) {
				panes.get(i).draw(g);
				break;
			}
		}
		
	}
	
	public void removePane(String name) {
		for(int i = 0; i < panes.size(); i++) {
			if(panes.get(i).getName().equals(name)) {
				panes.remove(i);
			}
		}
	}

	public String getCurPaneName() {
		return curPaneName;
	}
	
	public Pane getCurPane() {
		return curPane;
	}

	public void setCurPane(String curPaneName) {
		this.curPaneName = curPaneName;
		
		for(int i = 0; i < panes.size(); i++) {
			if(panes.get(i).getName().equals(this.curPaneName)) {
				curPane = panes.get(i);
			}
		}
	}
	
	
}
