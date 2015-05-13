package res;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public abstract class Clickable {
	private Rectangle2D bound;
	private BufferedImage image;
	private int x, y, width, height;
	private boolean clicked = false;
	private String name;
	
	public Clickable(int x, int y, BufferedImage image, String name) {
		this.x = x;
		this.y = y;
		this.bound = new Rectangle2D.Double(x, y, image.getWidth(), image.getHeight());
		this.image = image;
		this.width = image.getWidth();
		this.height = image.getHeight();
		this.name = name;
	}
	
	public int getX() {return x;}
	public int getY() {return y;}
	public void setX(int x) {this.x = x;}
	public void setY(int y) {this.y = y;}
	public int getWidth() {return width;}
	public int getHeight() {return height;}
	public String getName() {return name;}
	public Rectangle2D getBound() {return bound;}
	public boolean isClicked() {return clicked;}
	public void setImage(BufferedImage image) {this.image = image;}
	
	public void draw(Graphics2D g) {
		g.drawImage(image, x, y, width, height, null);
	}
}
