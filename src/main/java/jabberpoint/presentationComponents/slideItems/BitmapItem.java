package jabberpoint.presentationComponents.slideItems;

import jabberpoint.creators.BitmapItemCreator;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;

import javax.imageio.ImageIO;

import java.io.IOException;


/** <p>De klasse voor een Bitmap item</p>
 * <p>Bitmap items have the responsibility to draw themselves.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
*/

public class BitmapItem extends SlideItem {
  private BufferedImage bufferedImage;
  private String imageName;
  private Float size = 1f;
  
  protected static final String FILE = "File ";
  protected static final String NOTFOUND = " not found";

	public BitmapItem() {
		super();
	}

// give the filename of the image
	public String getName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
		try {
			bufferedImage = ImageIO.read(new File(imageName));
		}
		catch (IOException e) {
			System.err.println(FILE + imageName + NOTFOUND) ;
		}
	}

	public Float getSize() {
		return size;
	}
	public void setSize(Float size) {
		this.size = size;
	}

// draw the image
	public void draw(float scale, Graphics g, ImageObserver observer) {
		Rectangle boundingBox = getBoundingBox(g, observer,scale);
		g.drawImage(bufferedImage, getX(), getY(), boundingBox.width, boundingBox.height, observer);
	}

	// give the  bounding box of the image
	public Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale) {
		return new Rectangle((int) (1 * scale), 0,
				(int) (bufferedImage.getWidth(observer) * scale * size),
				((int) (1 * scale)) +
						(int) (bufferedImage.getHeight(observer) * scale * size));
	}

	public String toString() {
		return "presentationComponents.BitmapItem[" + getLevel() + "," + imageName + "]";
	}

	@Override
	public String getSaveString() {
		return BitmapItemCreator.getSaveString(this);
	}
}
