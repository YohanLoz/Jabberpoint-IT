package jabberpoint.creators;


import jabberpoint.presentationComponents.Slide;
import jabberpoint.presentationComponents.slideItems.BitmapItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BitMapItemCreatorTest {

    BitmapItem item;
    Slide slide;

    @BeforeEach
    void setUp() {
        slide = new Slide();
    }

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
        assertEquals(Float.parseFloat(SlideItemCreatorTest.correctArgsBitMapItem[4]), item.getSize());
    }

    @Test
    void setImage_normal_addsImage(){
        String name = "aaa";
        BitmapItem item = new BitmapItemCreator().setImage(name).getItem();
        assertEquals(name, item.getName());
    }

    @Test
    void setImage_null_throwsIllegalArgumentException(){
        assertThrows(IllegalArgumentException.class, () -> new BitmapItemCreator().setImage(null));
    }

    @Test
    void setImage_emptyString_throwsIllegalArgumentException(){
        assertThrows(IllegalArgumentException.class, () -> new BitmapItemCreator().setImage(""));
    }

    @Test
    void setScale_positive_doesNotThrow(){
        assertDoesNotThrow(()->new BitmapItemCreator().setScale(1));
    }

    @Test
    void setScale_zero_throwsIllegalArgumentException(){
        assertThrows(IllegalArgumentException.class, () -> new BitmapItemCreator().setScale(0));
    }

    @Test
    void setScale_negative_illegalArgumentException(){
        assertThrows(IllegalArgumentException.class, () -> new BitmapItemCreator().setScale(-1));
    }

    @Test
    void appendToSlide_normal_addsItemToSlide(){
        item = new BitmapItemCreator().appendToSlide(slide).appendToSlide(slide).getItem();
        assertEquals(item, slide.getSlideItem(0));
    }

    @Test
    void appendToSlide_null_throwsIllegalArgumentException(){
        assertThrows(IllegalArgumentException.class, () -> new BitmapItemCreator().appendToSlide(null));
    }

    @Test
    void getSaveString_normal_returnsString(){
        item = new BitmapItemCreator().getItem();
        assertEquals(String.class, BitmapItemCreator.getSaveString(item).getClass());
    }

    @Test
    void getSaveString_null_throwsIllegalArgumentException(){
        item = new BitmapItemCreator().getItem();
        assertThrows(IllegalArgumentException.class, () -> BitmapItemCreator.getSaveString(null));
    }

    @Test
    void setLevel_positive_doesNotThrow(){
        assertDoesNotThrow(()->new BitmapItemCreator().setLevel(1));
    }

    @Test
    void setLevel_zero_doesNotThrow(){
        assertDoesNotThrow(()->new BitmapItemCreator().setLevel(0));
    }

    @Test
    void setLevel_negative_doesNotThrow(){
        assertDoesNotThrow(()->new BitmapItemCreator().setLevel(-1));
    }

    @Test
    void setCoords_posPos_doesNotThrow(){
        assertDoesNotThrow(()->new BitmapItemCreator().setCoords(1, 1));
    }

    @Test
    void setCoords_posZero_doesNotThrow(){
        assertDoesNotThrow(()->new BitmapItemCreator().setCoords(1, 0));
    }

    @Test
    void setCoords_posNeg_doesNotThrow(){
        assertDoesNotThrow(()->new BitmapItemCreator().setCoords(1, -1));
    }

    @Test
    void setCoords_zeroPos_doesNotThrow(){
        assertDoesNotThrow(()->new BitmapItemCreator().setCoords(0, 1));
    }

    @Test
    void setCoords_zeroZero_doesNotThrow(){
        assertDoesNotThrow(()->new BitmapItemCreator().setCoords(0, 0));
    }

    @Test
    void setCoords_zeroNeg_doesNotThrow(){
        assertDoesNotThrow(()->new BitmapItemCreator().setCoords(0, -1));
    }

    @Test
    void setCoords_NegPos_doesNotThrow(){
        assertDoesNotThrow(()->new BitmapItemCreator().setCoords(-1, 1));
    }

    @Test
    void setCoords_NegZero_doesNotThrow(){
        assertDoesNotThrow(()->new BitmapItemCreator().setCoords(-1, 0));
    }

    @Test
    void setCoords_NegNeg_doesNotThrow(){
        assertDoesNotThrow(()->new BitmapItemCreator().setCoords(-1, -1));
    }

    @Test
    void setX_positive_doesNotThrow(){
        assertDoesNotThrow(()->new BitmapItemCreator().setX(1));
    }

    @Test
    void setX_zero_doesNotThrow(){
        assertDoesNotThrow(()->new BitmapItemCreator().setX(0));
    }

    @Test
    void setX_negative_doesNotThrow(){
        assertDoesNotThrow(()->new BitmapItemCreator().setX(-1));
    }

    @Test
    void setY_positive_doesNotThrow(){
        assertDoesNotThrow(()->new BitmapItemCreator().setY(1));
    }

    @Test
    void setY_zero_doesNotThrow(){
        assertDoesNotThrow(()->new BitmapItemCreator().setY(0));
    }

    @Test
    void setY_negative_doesNotThrow(){
        assertDoesNotThrow(()->new BitmapItemCreator().setY(-1));
    }

    @Test
    void getItem_returnsItem(){
        assertEquals(BitmapItem.class, new BitmapItemCreator().getItem().getClass());
    }


}
