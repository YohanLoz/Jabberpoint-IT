package jabberpoint.creators;

import jabberpoint.presentationComponents.Slide;
import jabberpoint.presentationComponents.slideItems.BitmapItem;

public class BitmapItemCreator extends SlideItemCreator {

    BitmapItem item;
    public BitmapItemCreator() {
        this.item = new BitmapItem();
    }

    @Override
    public BitmapItemCreator processArgs(String[] args) {
        args = applyDefaultVarsReturnRest(args, item);
        item.setImageName(args[0]);
        return this;
    }

    public BitmapItemCreator setImage(String path){
        item.setImageName(path);
        return this;
    }

    public BitmapItemCreator setScale(float scale){
        item.setSize(scale);
        return this;
    }

    public void appendToSlide(Slide slide) {
        super.appendToSlide(slide, item);
    }

    static public String getSaveString(BitmapItem item){
        return SlideItemCreator.getSaveString("BitmapItem", item.getName(), item);
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
}
