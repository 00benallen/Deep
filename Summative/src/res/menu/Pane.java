package res.menu;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

/**
 * Stores Drawables, constitutes one complete window, calls the Drawbles' draw methods
 * @author Ben Pinhorn
 *
 */
public class Pane {
	LinkedList<Drawable> drawables;
	String name;
	BufferedImage background;
	
	/**
	 * Default constructor for Pane
	 * @param name
	 * @param background
	 */
	public Pane(String name, BufferedImage background) {
		drawables = new LinkedList<Drawable>();
		this.name = name;
		this.background = background;
	}
	
	/**
	 * Constructor for pane with buttons already generated
	 * @param buttons
	 * @param name
	 * @param background
	 */
	public Pane(LinkedList<Drawable> drawables, String name, BufferedImage background) {
		this.drawables = drawables;
		this.name = name;
		this.background = background;
	}
	
	public LinkedList<Drawable> getElements() {return drawables;}
	public Drawable getElement(int index) {return drawables.get(index);}
	public String getName() {return name;}
	public void add(Drawable el) {drawables.add(el);}
	
	/**
	 * Finds and returns a drawable
	 * @param name
	 * @return
	 */
	public Drawable getElement(String name) {
		for(int i = 0; i < drawables.size(); i++) {
			if(drawables.get(i).getName().equals(name)) {
				return drawables.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Finds and removes a drawable
	 * @param name
	 */
	public void remove(String name) {
		for(int i = 0; i < drawables.size(); i++) {
			if(drawables.get(i).getName().equals(name)) {
				drawables.remove(i);
			}
		}
	}
	
	/**
	 * Calls draw on all drawables contained
	 * @param g
	 */
	public void draw(Graphics2D g) {
		g.drawImage(background, 0, 0, null);
		for(int i = 0; i < drawables.size(); i++) {
			Drawable b = drawables.get(i);
			b.draw(g);
		}
	}

}
