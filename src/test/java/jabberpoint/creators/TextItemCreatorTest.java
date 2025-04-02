package jabberpoint.creators;

import jabberpoint.presentationComponents.Slide;
import jabberpoint.presentationComponents.slideItems.TextItem;
import jabberpoint.style.Style;
import jabberpoint.style.StyleFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class TextItemCreatorTest {

    String correctDefaultStyleName = "default";
    String correctStyleName = "newDefault";
    int correctStyleIndent = 12;
    Color correctStyleColor = Color.black;
    Font correctStyleFont = new Font("Comic Sans MS", Font.PLAIN, 12);
    int correctStyleFontSize = 50;
    int correctStyleLeading = 10;

    TextItem item;
    Slide slide;


    @BeforeEach
    public void setUp(){
        slide = new Slide();
        StyleFactory.getStyle(correctDefaultStyleName, correctStyleIndent, correctStyleColor, correctStyleFont, correctStyleFontSize, correctStyleLeading);
    }

    @AfterEach
    public void tearDown(){
        StyleFactory.fullResetStyles();
    }
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
    void processArgs_null_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {new TextItemCreator().processArgs(null);});
    }

    @Test
    void processArgs_empty_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {new TextItemCreator().processArgs(new String[0]);});
    }

    @Test
    void setStyle_fullArgs_normal_success() {
        item = new TextItemCreator().setStyle(correctStyleName, correctStyleIndent, correctStyleColor, correctStyleFont, correctStyleFontSize, correctStyleLeading).getItem();
        assertNotNull(item.getStyle());
    }

    @Test
    void setStyle_fullArgs_nullColor_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {new TextItemCreator().setStyle(correctStyleName, correctStyleIndent, null, correctStyleFont, correctStyleFontSize, correctStyleLeading);});
    }

    @Test
    void setStyle_fullArgs_nullFont_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {new TextItemCreator().setStyle(correctStyleName, correctStyleIndent, correctStyleColor, null, correctStyleFontSize, correctStyleLeading);});
    }

    @Test
    void setStyle_fromName_normal_success(){
        item = new TextItemCreator().setStyle(correctDefaultStyleName).getItem();
        assertNotNull(item.getStyle());
    }

    @Test
    void setStyle_fromName_null_throwsIllegalArgumentException(){
        assertThrows(IllegalArgumentException.class, () -> {new TextItemCreator().setStyle((String) null);});
    }

    @Test
    void setStyle_fromStyle_normal_success(){
        Style style = StyleFactory.getStyleByName(correctDefaultStyleName);
        item = new TextItemCreator().setStyle(style).getItem();
        assertNotNull(item.getStyle());
    }

    @Test
    void setStyle_fromStyle_null_throwsIllegalArgumentException(){
        assertThrows(IllegalArgumentException.class, () -> {new TextItemCreator().setStyle((Style) null);});
    }

    @Test
    void setText_normal_success(){
        item = new TextItemCreator().setText("test").setStyle(correctDefaultStyleName).getItem();
        assertNotNull(item.getStyle());
        assertNotEquals("", item.getText());
    }

    @Test
    void setText_null_success(){
        assertThrows(IllegalArgumentException.class, () -> {new TextItemCreator().setText(null);});
    }

    @Test
    void setText_empty_success(){
        assertThrows(IllegalArgumentException.class, () -> {new TextItemCreator().setText("");});
    }

    @Test
    void appendToSlide_normal_success(){
        new TextItemCreator().appendToSlide(slide);
        assertEquals(TextItem.class, slide.getSlideItem(0).getClass());
    }

    @Test
    void appendToSlide_null_throwsIllegalArgumentException(){
        assertThrows(IllegalArgumentException.class, () -> {new TextItemCreator().appendToSlide(null);});
    }


    @Test
    void getSaveString_normal_returnsString(){
        item = new TextItemCreator().setStyle(correctDefaultStyleName).getItem();
        assertEquals(String.class, TextItemCreator.getSaveString(item).getClass());
    }

    @Test
    void getSaveString_null_throwsIllegalArgumentException(){
        item = new TextItemCreator().getItem();
        assertThrows(IllegalArgumentException.class, () -> TextItemCreator.getSaveString(null));
    }

    @Test
    void setLevel_positive_doesNotThrow(){
        assertDoesNotThrow(()->new TextItemCreator().setLevel(1));
    }

    @Test
    void setLevel_zero_doesNotThrow(){
        assertDoesNotThrow(()->new TextItemCreator().setLevel(0));
    }

    @Test
    void setLevel_negative_doesNotThrow(){
        assertDoesNotThrow(()->new TextItemCreator().setLevel(-1));
    }

    @Test
    void setCoords_posPos_doesNotThrow(){
        assertDoesNotThrow(()->new TextItemCreator().setCoords(1, 1));
    }

    @Test
    void setCoords_posZero_doesNotThrow(){
        assertDoesNotThrow(()->new TextItemCreator().setCoords(1, 0));
    }

    @Test
    void setCoords_posNeg_doesNotThrow(){
        assertDoesNotThrow(()->new TextItemCreator().setCoords(1, -1));
    }

    @Test
    void setCoords_zeroPos_doesNotThrow(){
        assertDoesNotThrow(()->new TextItemCreator().setCoords(0, 1));
    }

    @Test
    void setCoords_zeroZero_doesNotThrow(){
        assertDoesNotThrow(()->new TextItemCreator().setCoords(0, 0));
    }

    @Test
    void setCoords_zeroNeg_doesNotThrow(){
        assertDoesNotThrow(()->new TextItemCreator().setCoords(0, -1));
    }

    @Test
    void setCoords_NegPos_doesNotThrow(){
        assertDoesNotThrow(()->new TextItemCreator().setCoords(-1, 1));
    }

    @Test
    void setCoords_NegZero_doesNotThrow(){
        assertDoesNotThrow(()->new TextItemCreator().setCoords(-1, 0));
    }

    @Test
    void setCoords_NegNeg_doesNotThrow(){
        assertDoesNotThrow(()->new TextItemCreator().setCoords(-1, -1));
    }

    @Test
    void setX_positive_doesNotThrow(){
        assertDoesNotThrow(()->new TextItemCreator().setX(1));
    }

    @Test
    void setX_zero_doesNotThrow(){
        assertDoesNotThrow(()->new TextItemCreator().setX(0));
    }

    @Test
    void setX_negative_doesNotThrow(){
        assertDoesNotThrow(()->new TextItemCreator().setX(-1));
    }

    @Test
    void setY_positive_doesNotThrow(){
        assertDoesNotThrow(()->new TextItemCreator().setY(1));
    }

    @Test
    void setY_zero_doesNotThrow(){
        assertDoesNotThrow(()->new TextItemCreator().setY(0));
    }

    @Test
    void setY_negative_doesNotThrow(){
        assertDoesNotThrow(()->new TextItemCreator().setY(-1));
    }

    @Test
    void getItem_returnsItem(){
        assertEquals(TextItem.class, new TextItemCreator().getItem().getClass());
    }


}
