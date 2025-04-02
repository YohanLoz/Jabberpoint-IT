package jabberpoint.presentationComponents.slideItems;

import jabberpoint.creators.TextItemCreator;
import jabberpoint.presentationComponents.Slide;
import jabberpoint.style.Style;
import jabberpoint.style.StyleFactory;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.font.TextLayout;
import java.awt.font.TextAttribute;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.text.AttributedString;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

/** <p>A tekst item.</p>
 * <p>A presentationComponents.TextItem has drawingfunctionality.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class TextItem extends SlideItem {
	private String text;

	private static final String EMPTYTEXT = "No Text Given";

	private int styleId = -1;

// an empty textitem
	public TextItem() {
		super();
	}

// give the text
	public String getText() {
		return text == null ? "" : text;
	}
	public void setText(String text) {
		this.text = text;
	}

	public void setStyleId(int styleId) {
		this.styleId = styleId;
	}

// geef de AttributedString voor het item
	public AttributedString getAttributedString(float scale) {
		if(scale <= 0){
			throw new IllegalArgumentException("scale cannot be null or negative");
		}
		AttributedString attrStr = new AttributedString(getText());
		attrStr.addAttribute(TextAttribute.FONT, getStyle().getFittedFont(scale), 0, text.length());
		return attrStr;
	}

// give the bounding box of the item
	public Rectangle getBoundingBox(Graphics g, ImageObserver observer,
			float scale) {
		if(g == null){
			throw new IllegalArgumentException("graphics cannot be null");
		}
		if(observer == null){
			throw new IllegalArgumentException("imageObserver cannot be null");
		}
		if(scale <= 0){
			throw new IllegalArgumentException("scale cannot be negative");
		}
		List<TextLayout> layouts = getLayouts(g, scale);
		int xsize = 0, ysize = (int) (getStyle().leading * scale);
		Iterator<TextLayout> iterator = layouts.iterator();
		while (iterator.hasNext()) {
			TextLayout layout = iterator.next();
			Rectangle2D bounds = layout.getBounds();
			if (bounds.getWidth() > xsize) {
				xsize = (int) bounds.getWidth();
			}
			if (bounds.getHeight() > 0) {
				ysize += bounds.getHeight();
			}
			ysize += layout.getLeading() + layout.getDescent();
		}
		return new Rectangle((int) (getStyle().indent*scale), 0, xsize, ysize );
	}

// draw the item
	public void draw(float scale, Graphics g, ImageObserver o) {
		if(g == null){
			throw new IllegalArgumentException("graphics cannot be null");
		}
		if(o == null){
			throw new IllegalArgumentException("imageObserver cannot be null");
		}
		if (text == null || text.length() == 0) {
			return;
		}
		List<TextLayout> layouts = getLayouts(g, scale);
		Point pen = new Point(getX() + (int)(getStyle().indent * scale),
				getY() + (int) (getStyle().leading * scale));
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(getStyle().color);
		Iterator<TextLayout> it = layouts.iterator();
		while (it.hasNext()) {
			TextLayout layout = it.next();
			pen.y += layout.getAscent();
			layout.draw(g2d, pen.x, pen.y);
			pen.y += layout.getDescent();
		}
	  }

	@Override
	public SlideItem clone() {
		TextItem cloneItem = new TextItem();
		cloneItem.setLevel(getLevel());
		cloneItem.setText(getText());
		cloneItem.setX(getX());
		cloneItem.setY(getY());
		cloneItem.setStyleId(this.styleId);
		return cloneItem;
	}

	private List<TextLayout> getLayouts(Graphics g, float scale) {
		if(g == null){
			throw new IllegalArgumentException("graphics cannot be null");
		}
		if(scale <= 0){
			throw new IllegalArgumentException("scale cannot be 0 or negative");
		}
		List<TextLayout> layouts = new ArrayList<TextLayout>();
		AttributedString attrStr = getAttributedString(scale);
    	Graphics2D g2d = (Graphics2D) g;
    	FontRenderContext frc = g2d.getFontRenderContext();
    	LineBreakMeasurer measurer = new LineBreakMeasurer(attrStr.getIterator(), frc);
    	float wrappingWidth = (Slide.WIDTH - getStyle().indent) * scale;
    	while (measurer.getPosition() < getText().length()) {
    		TextLayout layout = measurer.nextLayout(wrappingWidth);
    		layouts.add(layout);
    	}
    	return layouts;
	}

	public String toString() {
		return "presentationComponents.TextItem[" + getLevel()+","+getText()+"]";
	}

	public Style getStyle(){
		if(this.styleId == -1){
			throw new IllegalStateException("No style has been ");
		}
		return StyleFactory.getStyleById(this.styleId);
	}

	@Override
	public String getSaveString() {
		if(this.styleId == -1){
			throw new IllegalStateException("No style has been initialized");
		}
		return TextItemCreator.getSaveString(this);
	}
}
