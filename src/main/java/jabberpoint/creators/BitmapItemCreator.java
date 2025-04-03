package jabberpoint.creators;

import jabberpoint.presentationComponents.Slide;
import jabberpoint.presentationComponents.slideItems.BitmapItem;

public class BitmapItemCreator extends SlideItemCreator {

    static final String CLASSNAME = "BitmapItem";

    BitmapItem item;

    public BitmapItemCreator() {
        this.item = new BitmapItem();
    }

    @Override
    public BitmapItemCreator processArgs(String[] args) {
        if (args == null || args.length == 0) {
            throw new IllegalArgumentException("No args provided");
        }
        args = applyDefaultVarsReturnRest(args, item);
        item.setImageName(args[0]);
        item.setSize(Float.parseFloat(args[1]));
        return this;
    }

    public BitmapItemCreator setImage(String path) {
        if (path == null || path.isEmpty()) {
            throw new IllegalArgumentException("No path provided");
        }
        item.setImageName(path);
        return this;
    }

    public BitmapItemCreator setScale(float scale) {
        if (scale <= 0.0f) {
            throw new IllegalArgumentException("Scale must be greater than zero");
        }
        item.setSize(scale);
        return this;
    }

    public BitmapItemCreator appendToSlide(Slide slide) {
        if (slide == null) {
            throw new IllegalArgumentException("Slide cannot be null");
        }
        super.appendToSlide(slide, item);
        return this;
    }

    static public String getSaveString(BitmapItem item) {
        if (item == null) {
            throw new IllegalArgumentException("BitmapItem cannot be null");
        }
        String[] values = {item.getName(), item.getSize().toString()};
        String saveString = String.join(DELIMITER, values);
        return SlideItemCreator.getSaveString(CLASSNAME, saveString, item);
    }

    @Override
    public BitmapItemCreator setLevel(int level) {
        item.setLevel(level);
        return this;
    }

    @Override
    public BitmapItemCreator setCoords(int X, int Y) {
        setCoords(X, Y, item);
        return this;
    }

    @Override
    public BitmapItemCreator setX(int x) {
        item.setX(x);
        return this;
    }

    @Override
    public BitmapItemCreator setY(int y) {
        item.setY(y);
        return this;
    }

    public BitmapItem getItem() {
        return item;
    }
}
