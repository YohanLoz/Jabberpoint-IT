package jabberpoint.creators;

import jabberpoint.presentationComponents.Slide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SlideItemCreatorTest {

    static final String correctStringTextItem = "1,,,2,,,3,,,The Java presentationComponents.Presentation Tool!,,,default,,,12,,,#000000,,,Comic Sans MS-0-12,,,50,,,10";
    static final String correctStringBitmapItem = "0,,,600,,,400,,,serclogo_fc.jpg,,,2.0";
    static final String[] correctArgsTextItem = {"1", "2", "3", "The Java presentationComponents.Presentation Tool!", "default", "12", "#000000", "Comic Sans MS-0-12", "50", "10"};
    static final String[] correctArgsBitMapItem = {"0", "600", "400", "serclogo_fc.jpg", "2.0"};
    static final String expectedTextItemSaveString = "<item name=\"TextItem\">1,,,2,,,3,,,The Java presentationComponents.Presentation Tool!,,,default,,,12,,,#000000,,,Comic Sans MS-0-12,,,50,,,10</item>";

    Slide slide;

    @BeforeEach
    void setup() {
        slide = new Slide();
    }

    // software will still work if this fails, but compatibility will fail, including the tests.
    @Test
    void DelimiterIsTheSame() {
        assertEquals(SlideItemCreator.DELIMITER, ",,,");
    }

    @Test
    void createSlideItem_correctTextItemString_succeeds() {
        SlideItemCreator.createSlideItem(TextItemCreator.CLASSNAME, slide, correctStringTextItem);
        assertEquals(1, slide.getSize());
    }

    @Test
    void createSlideItem_correctBitmapItemString_succeeds() {
        SlideItemCreator.createSlideItem(BitmapItemCreator.CLASSNAME, slide, correctStringBitmapItem);
        assertEquals(1, slide.getSize());
    }

    @Test
    void createSlideItem_emptyName_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> SlideItemCreator.createSlideItem("", slide, correctStringTextItem));
    }

    @Test
    void createSlideItem_emptyContent_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> SlideItemCreator.createSlideItem("TextItem", slide, ""));
    }

    @Test
    void createSlideItem_nullName_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> SlideItemCreator.createSlideItem(null, slide, correctStringTextItem));
    }

    @Test
    void createSlideItem_nullContent_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> SlideItemCreator.createSlideItem("TextItem", slide, (String) null));
    }

    @Test
    void createSlideItem_unknownName_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> SlideItemCreator.createSlideItem("JPEG", slide, correctStringTextItem));
    }

}
