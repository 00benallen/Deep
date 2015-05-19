package res.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
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
		String[] textLines = text.split("\n");
		g.drawString(textLines[0], this.getX() + this.getWidth()/8, this.getY() + this.getHeight()/4);
		for(int i = 1; i < textLines.length; i++) {
			String line = textLines[i];
			if(!line.substring(0, 2).equals("/m")) {
				line = "[" + i + "]" + line;
			}
			else {
				line = line.substring(2);
			}
			FontMetrics f = g.getFontMetrics();
			g.drawString(line, this.getX() + this.getWidth()/8, this.getY() + this.getHeight()/4 + i*f.getHeight());
		}
	}

}
