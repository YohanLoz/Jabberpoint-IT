package jabberpoint.creators;

import jabberpoint.presentationComponents.slideItems.TextItem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TextItemCreatorTest {

    TextItem item;

    @Test
    void createTextItemCreator_noArgs_success() {
        assertDoesNotThrow(() -> {
            new TextItemCreator();
        });
    }

    @Test
    void processArgs_correctArgs_success() {
        item = new TextItemCreator().processArgs(SlideItemCreatorTest.correctArgsTextItem).getItem();
        assertNotNull(item);
        assertNotNull(item.getStyle());
        assertEquals(Integer.parseInt(SlideItemCreatorTest.correctArgsTextItem[0]), item.getLevel());
        assertEquals(Integer.parseInt(SlideItemCreatorTest.correctArgsTextItem[1]), item.getX());
        assertEquals(Integer.parseInt(SlideItemCreatorTest.correctArgsTextItem[2]), item.getY());
        assertEquals(SlideItemCreatorTest.correctArgsTextItem[3], item.getText());
    }

    @Test
    void saveSlideItem_success() {
        assertEquals(SlideItemCreatorTest.expectedTextItemSaveString, item.getSaveString());
    }



}
