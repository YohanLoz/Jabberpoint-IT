package jabberpoint.creators;

import jabberpoint.presentationComponents.Slide;
import jabberpoint.presentationComponents.slideItems.TextItem;
import jabberpoint.style.Style;
import jabberpoint.style.StyleFactory;

import java.awt.*;
import java.util.Arrays;

public class TextItemCreator extends SlideItemCreator {
    TextItem item;
    public TextItemCreator() {
        this.item = new TextItem();
    }

    @Override
    public SlideItemCreator processArgs(String[] args) {
        item.setText(args[0]);
        String[] styleArgs = Arrays.copyOfRange(args, 1, args.length);
        Style style = StyleFactory.getStyleFromArgs(styleArgs);
        item.setStyleId(style.id);
        return this;
    }

    private void setStyle(String name, int indent, Color color, Font font, int fontSize, int leading){
        Style style = StyleFactory.getStyle(name, indent, color, font, fontSize, leading);
        setStyle(style);
    }

    private void setStyle(String name){
        Style style = StyleFactory.getStyleByName(name);
        if(style == null){
            throw new IllegalArgumentException("No such style: " + name);
        }
        setStyle(style);
    }

    private void setStyle(Style style){
        int id = StyleFactory.getIdByName(style.name);
        item.setStyleId(id);
    }


    private void setText(String text){
        item.setText(text);
    }

    public void appendToSlide(Slide slide) {
        super.appendToSlide(slide, item);
    }

    public static String createSaveString(TextItem item) {
        String[] values = {item.getText(),
                StyleFactory.getSaveString(item.getStyle())};

        return SlideItemCreator.createSaveString("SlideItem", String.join(",", values));
    }

}
