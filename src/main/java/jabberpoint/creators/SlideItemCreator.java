package jabberpoint.creators;

import jabberpoint.presentationComponents.Slide;
import jabberpoint.presentationComponents.slideItems.SlideItem;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

import java.util.Arrays;


public abstract class SlideItemCreator {

    protected static final String SLIDEITEMNAME = "name";
    protected static final String DELIMITER= "||";

    public abstract SlideItemCreator processArgs(String[] args);

    static public void createSlideItem(Slide slide, Element element){
        NamedNodeMap attributes = element.getAttributes();
        String itemName = attributes.getNamedItem(SLIDEITEMNAME).getTextContent();
        String content = element.getTextContent();
        SlideItemCreator.createSlideItem(itemName, slide, content);
    }

    static public void createSlideItem(String name, Slide slide, String content){
        String[] contentList = content.split(",");
        createSlideItem(name, slide, contentList);
    }

    static public void createSlideItem(String name, Slide slide, String[] args){
        switch(name){
            case "TextItem":
                new TextItemCreator().processArgs(args).appendToSlide(slide);
                break;

            case "BitmapItem":
                new BitmapItemCreator().processArgs(args).appendToSlide(slide);
                break;

        }
    }

    static protected String[] applyDefaultVarsReturnRest(String[] args, SlideItem item){
        item.setLevel(Integer.parseInt(args[0]));
        item.setX(Integer.parseInt(args[1]));
        item.setY(Integer.parseInt(args[2]));
        return createShortenedArrayFromStart(args, 3);
    }

    static protected String[] createShortenedArrayFromStart(String[] array, int itemsRemoved){
        return Arrays.copyOfRange(array, itemsRemoved, array.length);
    }

    protected abstract void appendToSlide(Slide slide);

    protected void appendToSlide(Slide slide, SlideItem item){
        slide.append(item);
    }

    public abstract SlideItemCreator setLevel(int level);

    protected void setLevel(int level, SlideItem item){
        item.setLevel(level);
    }

    public abstract SlideItemCreator setX(int x);
    protected void setX(int x, SlideItem item){
        item.setX(x);
    }

    public abstract SlideItemCreator setY(int y);
    protected void setY(int y, SlideItem item){
        item.setY(y);
    }

    static protected String getSaveString(String name, String newContent, SlideItem item){
        String[] defaultValuesArray = {
                Integer.toString(item.getLevel()),
                Integer.toString(item.getX()),
                Integer.toString(item.getY())
        };
        String defaultContent = String.join(DELIMITER, defaultValuesArray);
        String fullContent = defaultContent + DELIMITER + newContent;

        return String.format("<item %s=%s>%s</item>", SLIDEITEMNAME, name, fullContent);
    }
}
