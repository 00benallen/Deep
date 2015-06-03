package res.menu;

import java.awt.image.BufferedImage;

/**
 * Drawable designed to display images, primarily a label for Drawables for organization
 * @author Ben Pinhorn
 *
 */
public class ImageBox extends Drawable{
	 
	/**
	  * Default constructor for ImageBoxes
	  * @param x
	  * @param y
	  * @param image
	  * @param name
	  */
	public ImageBox(int x, int y, BufferedImage image, String name) {
		super(x, y, image, name);
	}
}
