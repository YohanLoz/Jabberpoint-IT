package jabberpoint.presentationComponents.slideItems;

import java.awt.*;
import java.awt.image.ImageObserver;

/**
 * <p>The abstract class for an item on a slide<p>
 * <p>All SlideItems have drawingfunctionality.</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public abstract class SlideItem {
    private int level = 0; // determines which SlideItem is rendered first
    private int x = 0;
    private int y = 0;

    public SlideItem(int lev) {
        level = lev;
    }

    public SlideItem() {
        this(0);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    // Give the level
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    // Give the bounding box
    public abstract Rectangle getBoundingBox(Graphics g,
                                             ImageObserver observer, float scale);

    // Draw the item
    public abstract void draw(float scale,
                              Graphics g, ImageObserver observer);

    // Clone the item
    public abstract SlideItem clone();

    public abstract String toString();

    public abstract String getSaveString();
}
