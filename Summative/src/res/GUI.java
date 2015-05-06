package res;

import java.awt.Graphics2D;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public abstract class GUI implements MouseListener{
	private LinkedList<Pane> panes;
	private String name, curPane;
	private BufferedImage background;
	private int x, y;
	
	public GUI(int x, int y, BufferedImage background, String name) {
		this.x = x;
		this.y = y;
		this.background = background;
		this.name = name;
		panes = new LinkedList<Pane>();
	}
	
	public GUI(int x, int y, BufferedImage background, LinkedList<Pane> panes, String name) {
		this.x = x;
		this.y = y;
		this.background = background;
		this.name = name;
		this.panes = panes;
	}
	
	public String getName() {return name;}
	public void addPane(Pane pane) {panes.add(pane);}
	public int getX() {return x;}
	public int getY() {return y;}
	public LinkedList<Pane> getPanes() {return panes;}
	
	public void draw(Graphics2D g) {
		g.drawImage(background, x, y, null);
		for(int i = 0; i < panes.size(); i++) {
			if(panes.get(i).getName().equals(curPane)) {
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

	public String getCurPane() {
		return curPane;
	}

	public void setCurPane(String curPane) {
		this.curPane = curPane;
	}
	
	
}
