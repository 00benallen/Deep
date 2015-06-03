package res.menu;

import java.awt.image.BufferedImage;
 
/**
 * Button for game, primarily a way of labeling a Drawable as a button
 * @author Ben Pinhorn
 *
 */
public class Button extends Drawable{
	
	/**
	  * Default constructor for a Button
	  * @param x
	  * @param y
	  * @param image
	  * @param name
	  */
	public Button(int x, int y, BufferedImage image, String name) {
		super(x, y, image, name);
	}	
}
