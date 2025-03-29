package jabberpoint.style;

import java.awt.Color;
import java.awt.Font;

/** <p>Style is for Indent, Color, Font and Leading.</p>
 * <p>Direct relation between style-number and item-level:
 * in presentationComponents.Slide style if fetched for an item
 * with style-number as item-level.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class Style {
	public static int idCounter = -1;

	public String name;
	public int indent;
	public Color color;
	public Font font;
	public int fontSize;
	public int leading;
	public int id;

	public Style(String name, int indent, Color color, Font font, int fontSize, int leading) {
		this.name = name;
		this.indent = indent;
		this.color = color;
		this.font = font;
		this.fontSize = fontSize;
		this.leading = leading;
		this.id = idCounter++;
	}

	public String toString() {
		return "["+ indent + "," + color + "; " + fontSize + " on " + leading +"]";
	}

	public Font getFont(float scale) {
		return font.deriveFont(fontSize * scale);
	}
}
