package jabberpoint.creators;

import jabberpoint.presentationComponents.Slide;
import jabberpoint.presentationComponents.slideItems.SlideItem;

public abstract class SlideItemCreator {
    public abstract SlideItemCreator processArgs(String[] args);

    static public void createSlideItem(String name, Slide slide, String[] args){
        switch(name){
            case "TextItem":
                new TextItemCreator().processArgs(args);
                break;

            case "BitmapItem":
                new BitmapItemCreator().processArgs(args);
                break;

        }
    }

    public void appendToSlide(Slide slide, SlideItem item){
        slide.append(item);
    }

    public void setLevel(int level, SlideItem item){
        item.setLevel(level);
    }

    static protected String createSaveString(String name, String contents){
        return "<item name="+
                name+
                ">"+
                contents +
                "</item>";
    }
}
