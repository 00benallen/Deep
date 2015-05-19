package res.menu;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Pane {
	LinkedList<Clickable> buttons;
	String name;
	BufferedImage background;
	
	public Pane(String name, BufferedImage background) {
		buttons = new LinkedList<Clickable>();
		this.name = name;
		this.background = background;
	}
	
	public Pane(LinkedList<Clickable> buttons, String name, BufferedImage background) {
		this.buttons = buttons;
		this.name = name;
		this.background = background;
	}
	
	public LinkedList<Clickable> getElements() {return buttons;}
	public Clickable getElement(int index) {return buttons.get(index);}
	public String getName() {return name;}
	public void add(Clickable el) {buttons.add(el);}
	
	public void remove(String name) {
		for(int i = 0; i < buttons.size(); i++) {
			if(buttons.get(i).getName().equals(name)) {
				buttons.remove(i);
			}
		}
	}
	
	public void draw(Graphics2D g) {
		g.drawImage(background, 0, 0, null);
		for(int i = 0; i < buttons.size(); i++) {
			Clickable b = buttons.get(i);
			b.draw(g);
		}
	}

}
