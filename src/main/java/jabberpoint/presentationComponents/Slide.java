package jabberpoint.presentationComponents;

import jabberpoint.presentationComponents.slideItems.SlideItem;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.Comparator;
import java.util.Vector;

/**
 * <p>A slide. This class has a drawing functionality.</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class Slide {
    public final static int WIDTH = 1200;
    public final static int HEIGHT = 800;
    protected String title; // title is saved separately
    protected Vector<SlideItem> items; // slide items are saved in a Vector

    public Slide() {
        items = new Vector<SlideItem>();
    }

    // Add a slide item
    public void append(SlideItem anItem) {
        items.addElement(anItem);
    }

    // give the title of the slide
    public String getTitle() {
        return title;
    }

    // change the title of the slide
    public void setTitle(String newTitle) {
        title = newTitle;
    }

    // give the  presentationComponents.SlideItem
    public SlideItem getSlideItem(int number) {
        return (SlideItem) items.elementAt(number);
    }

    // give all SlideItems in a Vector
    public Vector<SlideItem> getSlideItems() {
        return items;
    }

    // give the size of the presentationComponents.Slide
    public int getSize() {
        return items.size();
    }

    // draw the slide
    public void draw(Graphics g, Rectangle area, ImageObserver view) {
        float scale = getScale(area);

        items.sort(Comparator.comparingInt(SlideItem::getLevel));

        for (SlideItem slideItem : items) {
            slideItem.draw(scale, g, view);
        }
    }

    // Give the scale for drawing
    private float getScale(Rectangle area) {
        return Math.min(((float) area.width) / ((float) WIDTH), ((float) area.height) / ((float) HEIGHT));
    }
}
