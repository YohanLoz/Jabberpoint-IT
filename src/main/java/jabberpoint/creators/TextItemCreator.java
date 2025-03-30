package jabberpoint.creators;

import jabberpoint.presentationComponents.Slide;
import jabberpoint.presentationComponents.slideItems.TextItem;
import jabberpoint.style.Style;
import jabberpoint.style.StyleFactory;

import java.awt.*;

public class TextItemCreator extends SlideItemCreator {
    TextItem item;
    public TextItemCreator() {
        this.item = new TextItem();
    }

    @Override
    public SlideItemCreator processArgs(String[] args) {
        args = applyDefaultVarsReturnRest(args, item);
        item.setText(args[0]);
        String[] styleArgs = createShortenedArrayFromStart(args, 1);
        Style style = StyleFactory.getStyleFromArgs(styleArgs);
        item.setStyleId(style.id);
        return this;
    }

    public TextItemCreator setStyle(String name, int indent, Color color, Font font, int fontSize, int leading){
        Style style = StyleFactory.getStyle(name, indent, color, font, fontSize, leading);
        setStyle(style);
        return this;
    }

    public TextItemCreator setStyle(String name){
        Style style = StyleFactory.getStyleByName(name);
        if(style == null){
            throw new IllegalArgumentException("No such style: " + name);
        }
        setStyle(style);
        return this;
    }

    public TextItemCreator setStyle(Style style){
        int id = StyleFactory.getIdByName(style.name);
        item.setStyleId(id);
        return this;
    }


    public TextItemCreator setText(String text){
        item.setText(text);
        return this;
    }

    public void appendToSlide(Slide slide) {
        super.appendToSlide(slide, item);
    }

    @Override
    public SlideItemCreator setLevel(int level) {
        item.setLevel(level);
        return this;
    }

    @Override
    public SlideItemCreator setX(int x) {
        item.setX(x);
        return this;
    }

    @Override
    public SlideItemCreator setY(int y) {
        item.setY(y);
        return this;
    }

    public static String getSaveString(TextItem item) {
        String[] values = {item.getText(),
                StyleFactory.getSaveString(item.getStyle())};
        String joinedString = String.join(DELIMITER, values);
        return SlideItemCreator.getSaveString("SlideItem", joinedString, item);
    }

}
