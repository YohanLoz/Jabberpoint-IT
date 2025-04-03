package jabberpoint.style;

import java.awt.*;

/**
 * <p>Style is for Indent, Color, Font and Leading.</p>
 * <p>Direct relation between style-number and item-level:
 * in presentationComponents.Slide style if fetched for an item
 * with style-number as item-level.</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class Style {
    public static int idCounter = 0;

    public String name;
    public int indent;
    public Color color;
    public Font font;
    public int fontSize;
    public int leading;
    public int id;

    public Style(String name, int indent, Color color, Font font, int fontSize, int leading) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("No name provided");
        }
        if (color == null) {
            throw new IllegalArgumentException("No color provided");
        }
        if (font == null) {
            throw new IllegalArgumentException("No font provided");
        }
        this.name = name;
        this.indent = indent;
        this.color = color;
        this.font = font;
        this.fontSize = fontSize;
        this.leading = leading;
        this.id = idCounter++;
    }

    public String toString() {
        return "[" + indent + "," + color + "; " + fontSize + " on " + leading + "]";
    }

    public Font getFittedFont(float scale) {
        return font.deriveFont(fontSize * scale);
    }

    public static void resetIdCounter() {
        idCounter = 0;
    }
}
