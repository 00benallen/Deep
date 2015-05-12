package res;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class TextBox extends Clickable {
	String text = "";

	public TextBox(int x, int y, BufferedImage image, String name) {
		super(x, y, image, name);
	}
	
	public void setText(String text) {
		this.text = text;
	}
	public String getText() {return text;}
	
	public void draw(Graphics2D g) {
		super.draw(g);
		g.setColor(Color.black);
		g.setFont(new Font("Arial", Font.BOLD, 22));
		g.drawString(text, this.getX() + this.getWidth()/8, this.getY() + this.getHeight()/4);
	}

}
