package jabberpoint.presentationComponents;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class BitmapItemTest {

    BitmapItem bitmapItem;

    @BeforeEach
    void setup() {
        bitmapItem = new BitmapItem(1, "Something"); //null name causes an error
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
        bitmapItem = new BitmapItem(5, "SomethingSomething");

        BitmapItem newItem = (BitmapItem) bitmapItem.clone();
        assertEquals(bitmapItem.getLevel(), newItem.getLevel());
        assertEquals(bitmapItem.getName(), newItem.getName());
    }

}
