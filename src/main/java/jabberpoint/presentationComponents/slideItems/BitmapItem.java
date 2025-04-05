package jabberpoint.presentationComponents.slideItems;

import jabberpoint.creators.BitmapItemCreator;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;


/**
 * <p>De klasse voor een Bitmap item</p>
 * <p>Bitmap items have the responsibility to draw themselves.</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
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

    public String getName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        if (imageName == null || imageName.isEmpty()) {
            imageName = "NoImage";
        }
        this.imageName = imageName;

        try {
            bufferedImage = ImageIO.read(new File(imageName));
        } catch (IOException e) {
            System.err.println(FILE + imageName + NOTFOUND);
        }
    }

    public Float getSize() {
        return size;
    }

    public void setSize(Float size) {
        this.size = size;
    }

    @Override
    public void draw(float scale, Graphics g, ImageObserver observer) {
        Rectangle boundingBox = getBoundingBox(g, observer, scale);
        g.drawImage(bufferedImage, getX(), getY(), boundingBox.width, boundingBox.height, observer);
    }

    @Override
    public Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale) {
        return new Rectangle((int) (1 * scale), 0,
                (int) (bufferedImage.getWidth(observer) * scale * size),
                ((int) (1 * scale)) +
                        (int) (bufferedImage.getHeight(observer) * scale * size));
    }

    @Override
    public SlideItem clone() {
        BitmapItem cloneItem = new BitmapItem();
        cloneItem.setLevel(getLevel());
        cloneItem.setImageName(this.imageName);
        cloneItem.setSize(this.size);
        return cloneItem;
    }

    @Override
    public String toString() {
        return "presentationComponents.BitmapItem[" + getLevel() + "," + imageName + "]";
    }

    @Override
    public String getSaveString() {
        return BitmapItemCreator.getSaveString(this);
    }
}
