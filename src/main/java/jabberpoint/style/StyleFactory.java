package jabberpoint.style;

import jabberpoint.creators.SlideItemCreator;

import java.awt.*;
import java.util.ArrayList;

public class StyleFactory {
    private static ArrayList<Style> styles = new ArrayList<>();

    private static int unnamedId = -1;
    public StyleFactory(){
        styles = new ArrayList<Style>();
    }

    // can also update style by name
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


    public static String getSaveString(Style style){
        String[] string = {style.name,
                Integer.toString(style.indent),
                parseToString(style.color),
                parseToString(style.font),
                Integer.toString(style.fontSize),
                Integer.toString(style.leading)};

        return String.join(SlideItemCreator.DELIMITER, string);
    }

    private static String parseToString(Font font){
        return String.format("%s-%s-%s", font.getFamily(), font.getStyle(), font.getSize());
    }

    private static String parseToString(Color color){
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }


    public static Style getStyleFromArgs(String[] args){
        return getStyle(args[0],
                Integer.parseInt(args[1]),
                Color.decode(args[2]),
                decodeFontString(args[3]),
                Integer.parseInt(args[4]),
                Integer.parseInt(args[5]));
    }

    private static Font decodeFontString(String string) {
        String[] split = string.split("-");
        return new Font(split[0], Integer.parseInt(split[1]), Integer.parseInt(split[2]));
    }

    public static void removeStyleByName(String name){
        Style style = getStyleByName(name);
        if(style == null){
            return;
        }
        styles.remove(style);
    }

    public static void fullFlushAllStyles(){
        styles.clear();
    }
}
