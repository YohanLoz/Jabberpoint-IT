package jabberpoint.Creators;


import jabberpoint.creators.BitmapItemCreator;
import jabberpoint.presentationComponents.Presentation;
import jabberpoint.presentationComponents.slideItems.BitmapItem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class BitMapItemCreatorTest {

    BitmapItem item;

    @Test
    void createBitmapItemCreator_noArgs_success(){
        BitmapItemCreator creator = new BitmapItemCreator();
    }

    @Test
    void processArgs_correctArgs_success() {
        item = new BitmapItemCreator().processArgs(SlideItemCreatorTest.correctArgsBitMapItem).getItem();
        assertNotNull(item);
        assertEquals(Integer.parseInt(SlideItemCreatorTest.correctArgsBitMapItem[0]), item.getLevel());
        assertEquals(Integer.parseInt(SlideItemCreatorTest.correctArgsBitMapItem[1]), item.getX());
        assertEquals(Integer.parseInt(SlideItemCreatorTest.correctArgsBitMapItem[2]), item.getY());
        assertEquals(SlideItemCreatorTest.correctArgsBitMapItem[3], item.getName());
        assertEquals(Integer.parseInt(SlideItemCreatorTest.correctArgsBitMapItem[4]), item.getSize());
    }



}
