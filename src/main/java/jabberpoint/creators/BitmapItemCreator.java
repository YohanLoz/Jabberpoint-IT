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
        item.setImageName(args[0]);
        return this;
    }

    private void setImage(String path){
        item.setImageName(path);
    }

    public void appendToSlide(Slide slide) {
        super.appendToSlide(slide, item);
    }

    static public String createSaveString(BitmapItem item){
        return SlideItemCreator.createSaveString("BitmapItem", item.getName());
    }
}
