import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class SlideTest {

    Slide slide;

    @BeforeEach
    void setUp()
    {
        slide = new Slide();
    }

    @Test
    void startsEmpty(){
        assertEquals(0, slide.getSlideItems().size());
    }

    @Test
    void getSetTitle_normal_getsAndSetsTitle()
    {
        slide.setTitle("ABC123");
        assertEquals("ABC123", slide.getTitle());
    }

    @Test
    void appendItemGetItem_normal_appendsAndGetsItem()
    {
        TextItem textItem = new TextItem();
        slide.append(new TextItem());
        assertEquals(1, slide.getSlideItems().size());
        assertEquals(textItem, slide.getSlideItem(0));
    }

    @Test
    void append10SlideItems_normal_returns10()
    {
        for(int i = 0; i < 10; i++){
            slide.append(new TextItem());
        }
        assertEquals(10, slide.getSlideItems().size());
    }

    @Test
    void getSizeGetsSize()
    {
        for(int i = 0; i < 3; i++){
            slide.append(new TextItem());
        }
        assertEquals(3, slide.getSize());
    }
}
