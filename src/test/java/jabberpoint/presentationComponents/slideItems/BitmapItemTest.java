package jabberpoint.presentationComponents.slideItems;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class BitmapItemTest {

    BitmapItem bitmapItem;

    @BeforeEach
    void setup() {
        bitmapItem = new BitmapItem(); //null name causes an error
    }

    @Test
    void clone_emptyBitmapItem_shouldReturnEmptyBitmapItem()
    {
        BitmapItem newItem = (BitmapItem) bitmapItem.clone();
        assertEquals(bitmapItem.getLevel(), newItem.getLevel());
    }

    @Test
    void clone_bitmapItemWithLevel_shouldReturnIdenticalItem()
    {
        bitmapItem = new BitmapItem();
        bitmapItem.setLevel(1);
        bitmapItem.setImageName("Something");

        BitmapItem newItem = (BitmapItem) bitmapItem.clone();
        assertEquals(bitmapItem.getLevel(), newItem.getLevel());
        assertEquals(bitmapItem.getName(), newItem.getName());
    }

}
