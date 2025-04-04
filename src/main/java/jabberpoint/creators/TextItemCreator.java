package jabberpoint.creators;

import jabberpoint.presentationComponents.Slide;
import jabberpoint.presentationComponents.slideItems.TextItem;
import jabberpoint.style.Style;
import jabberpoint.style.StyleFactory;

import java.awt.*;

public class TextItemCreator extends SlideItemCreator {

    static final String CLASSNAME = "TextItem";

    private TextItem item;

    public TextItemCreator() {
        this.item = new TextItem();
    }

    @Override
    public TextItemCreator processArgs(String[] args) {
        if (args == null || args.length == 0) {
            throw new IllegalArgumentException("No args provided");
        }
        args = applyDefaultVarsReturnRest(args, item);
        item.setText(args[0]);
        String[] styleArgs = createShortenedArrayFromStart(args, 1);
        Style style = StyleFactory.getStyleFromArgs(styleArgs);
        item.setStyleId(style.id);
        return this;
    }

    public TextItemCreator setStyle(String name, int indent, Color color, Font font, int fontSize, int leading) {
        if (color == null) {
            throw new IllegalArgumentException("No color provided");
        }
        if (font == null) {
            throw new IllegalArgumentException("No font provided");
        }
        Style style = StyleFactory.getStyle(name, indent, color, font, fontSize, leading);
        setStyle(style);
        return this;
    }

    public TextItemCreator setStyle(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Style name cannot be null or empty");
        }
        Style style = StyleFactory.getStyleByName(name);
        if (style == null) {
            throw new IllegalArgumentException("No such style: " + name);
        }
        setStyle(style);
        return this;
    }

    public TextItemCreator setStyle(Style style) {
        if (style == null) {
            throw new IllegalArgumentException("No style provided");
        }
        int id = StyleFactory.getIdByName(style.name);
        item.setStyleId(id);
        return this;
    }

    public TextItemCreator setText(String text) {
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException("Text cannot be null or empty");
        }
        item.setText(text);
        return this;
    }

    @Override
    public TextItemCreator appendToSlide(Slide slide) {
        if (slide == null) {
            throw new IllegalArgumentException("No slide provided");
        }
        super.appendToSlide(slide, item);
        return this;
    }

    @Override
    public TextItemCreator setLevel(int level) {
        item.setLevel(level);
        return this;
    }

    @Override
    public TextItemCreator setCoords(int X, int Y) {
        setCoords(X, Y, item);
        return this;
    }

    @Override
    public TextItemCreator setX(int x) {
        item.setX(x);
        return this;
    }

    @Override
    public TextItemCreator setY(int y) {
        item.setY(y);
        return this;
    }

    @Override
    public TextItem getItem() {
        return item;
    }

    public static String getSaveString(TextItem item) {
        if (item == null) {
            throw new IllegalArgumentException("No TextItem provided");
        }
        String[] values = {item.getText(),
                StyleFactory.getSaveString(item.getStyle())};
        String joinedString = String.join(DELIMITER, values);
        return SlideItemCreator.getSaveString("TextItem", joinedString, item);
    }

}
