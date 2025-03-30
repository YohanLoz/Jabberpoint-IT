package jabberpoint.creators;

import jabberpoint.presentationComponents.Slide;
import jabberpoint.presentationComponents.slideItems.BitmapItem;

public class BitmapItemCreator extends SlideItemCreator {

    BitmapItem item;
    public BitmapItemCreator() {
        this.item = new BitmapItem();
    }

    @Override
    public SlideItemCreator processArgs(String[] args) {
        args = applyDefaultVarsReturnRest(args, item);
        item.setImageName(args[0]);
        return this;
    }

    private void setImage(String path){
        item.setImageName(path);
    }

    public void appendToSlide(Slide slide) {
        super.appendToSlide(slide, item);
    }

    static public String getSaveString(BitmapItem item){
        return SlideItemCreator.getSaveString("BitmapItem", item.getName(), item);
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
}
