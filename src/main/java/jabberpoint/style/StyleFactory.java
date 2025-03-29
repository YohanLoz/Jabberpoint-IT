package jabberpoint.style;

import java.awt.*;
import java.util.ArrayList;

public class StyleFactory {
    private static ArrayList<Style> styles;

    private static int unnamedId = -1;
    public StyleFactory(){
        styles = new ArrayList<Style>();
    }

    public static Style getStyle(int indent, Color color, Font font, int fontSize, int leading){
        for (Style style : styles) {
            if(style.indent == indent
            && style.color == color
            && style.font == font
            && style.fontSize == fontSize
            && style.leading == leading){
                return style;
            }
        }

        return createStyle(indent, color, font, fontSize, leading);
    }

    // also updates
    public static Style getStyle(String name, int indent, Color color, Font font, int fontSize, int leading){
        Style style = getStyleByName(name);
        if(style == null){
            return createStyle(name, indent, color, font, fontSize, leading);
        }
        style.indent = indent;
        style.color = color;
        style.font = font;
        style.fontSize = fontSize;
        style.leading = leading;
        return style;
    }

    public static Style getStyleByName(String name) {
        for (Style style : styles) {
            if(style.name.equals(name)){
                return style;
            }
        }
        return null;
    }

    public static Style getStyleById(int id) {
        for (Style style : styles) {
            if(style.id == id){
                return style;
            }
        }
        return null;
    }

    public static int getIdByName(String name) {
        for (Style style : styles) {
            if(style.name.equals(name)){
                return style.id;
            }
        }
        throw new IllegalArgumentException("No Style with name " + name + " found");
    }

    private static Style createStyle(String name, int indent, Color color, Font font, int fontSize, int leading){
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
    private static Style createStyle(int indent, Color color, Font font, int fontSize, int leading){
        Style style = new Style(
                getAndUpdateUnnamed(),
                indent,
                color,
                font,
                fontSize,
                leading);
        styles.add(style);
        return style;
    }

    private static String getAndUpdateUnnamed(){
        return "Unnamed" + unnamedId++;
    }

    public static String getSaveString(Style style){
        String[] string = {style.name,
                Integer.toString(style.indent),
                style.color.toString(),
                style.font.toString(),
                Integer.toString(style.fontSize),
                Integer.toString(style.leading)};

        return String.join(",", string);
    }

    public static Style getStyleFromArgs(String[] args){
        return getStyle(args[0],
                Integer.parseInt(args[1]),
                Color.getColor(args[2]),
                Font.getFont(args[3]),
                Integer.parseInt(args[4]),
                Integer.parseInt(args[5]));
    }
}
