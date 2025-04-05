package jabberpoint.style;

import jabberpoint.creators.SlideItemCreator;

import java.awt.*;
import java.util.ArrayList;

public class StyleFactory {
    private static ArrayList<Style> styles = new ArrayList<>();

    private static int unnamedId = 0;

    public static Style getStyleFromArgs(String[] args) {
        if (args == null || args.length == 0) {
            throw new IllegalArgumentException("No args provided");
        }
        if (args.length < 6) {
            throw new IllegalArgumentException("not enough args were passed. expected 6, got: " + args.length);
        }
        return getStyle(args[0],
                Integer.parseInt(args[1]),
                Color.decode(args[2]),
                decodeFontString(args[3]),
                Integer.parseInt(args[4]),
                Integer.parseInt(args[5]));
    }

    private static Font decodeFontString(String string) {
        if (string == null) {
            throw new IllegalArgumentException("No string provided");
        }
        String[] split = string.split("-");
        return new Font(split[0], Integer.parseInt(split[1]), Integer.parseInt(split[2]));
    }

    // can also update style by name
    public static Style getStyle(String name, int indent, Color color, Font font, int fontSize, int leading) {
        // if name is missing: redirect to non-named version
        if (name == null || name.isEmpty()) {
            return getStyle(indent, color, font, fontSize, leading);
        }
        Style style = getStyleByName(name);
        if (style == null) {
            return createStyle(name, indent, color, font, fontSize, leading);
        }
        style.indent = indent;
        style.color = color;
        style.font = font;
        style.fontSize = fontSize;
        style.leading = leading;
        return style;
    }

    public static Style getStyle(int indent, Color color, Font font, int fontSize, int leading) {
        for (Style style : styles) {
            if (style.indent == indent
                    && style.color == color
                    && style.font == font
                    && style.fontSize == fontSize
                    && style.leading == leading) {
                return style;
            }
        }

        return createStyle(indent, color, font, fontSize, leading);
    }

    private static Style createStyle(int indent, Color color, Font font, int fontSize, int leading) {
        return createStyle(getAndUpdateUnnamed(), indent, color, font, fontSize, leading);
    }

    private static Style createStyle(String name, int indent, Color color, Font font, int fontSize, int leading) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("No name provided");
        }
        if (color == null) {
            throw new IllegalArgumentException("No color provided");
        }
        if (font == null) {
            throw new IllegalArgumentException("No font provided");
        }
        Style style = new Style(
                name,
                indent,
                color,
                font,
                fontSize,
                leading);
        styles.add(style);
        return style;
    }

    private static String getAndUpdateUnnamed() {
        return "Unnamed" + unnamedId++;
    }

    public static Style getStyleById(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("Negative IDs not allowed");
        }
        for (Style style : styles) {
            if (style.id == id) {
                return style;
            }
        }
        throw new IllegalStateException("ID for style does not exist");
    }

    public static int getIdByName(String name) {
        Style style = getStyleByName(name);
        if (style == null) {
            throw new IllegalStateException("No Style with name " + name + " found");
        }
        return style.id;
    }

    public static Style getStyleByName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("No name provided");
        }
        for (Style style : styles) {
            if (style.name.equals(name)) {
                return style;
            }
        }
        return null;
    }

    public static String getSaveString(Style style) {
        if (style == null) {
            throw new IllegalArgumentException("No style provided");
        }
        String[] string = {style.name,
                Integer.toString(style.indent),
                parseToString(style.color),
                parseToString(style.font),
                Integer.toString(style.fontSize),
                Integer.toString(style.leading)};

        return String.join(SlideItemCreator.DELIMITER, string);
    }

    private static String parseToString(Font font) {
        if (font == null) {
            throw new IllegalArgumentException("No font provided");
        }
        return String.format("%s-%s-%s", font.getFamily(), font.getStyle(), font.getSize());
    }

    private static String parseToString(Color color) {
        if (color == null) {
            throw new IllegalArgumentException("No color provided");
        }
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }

    public static void removeStyleByName(String name) {
        if (name == null || name.isEmpty()) {
            return;
        }
        Style style = getStyleByName(name);
        if (style == null) {
            return;
        }
        styles.remove(style);
    }

    public static int getNumberOfStyles() {
        return styles.size();
    }

    public static void fullResetStyles() {
        styles.clear();
        Style.resetIdCounter();
        unnamedId = 0;
    }
}
