package jabberpoint.presentationComponents;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class TextItemTest {

    TextItem textItem;

    @BeforeEach
    void setup() {
        textItem = new TextItem();
    }

    @Test
    void clone_emptyTextItem_shouldReturnEmptyTextItem()
    {
        TextItem newItem = (TextItem) textItem.clone();
        assertEquals(textItem.getLevel(), newItem.getLevel());
        assertEquals(textItem.getText(), newItem.getText());
    }

    @Test
    void clone_textItemWithLevel_shouldReturnIdenticalTextItem()
    {
        textItem = new TextItem(5, "SomethingSomething");

        TextItem newItem = (TextItem) textItem.clone();
        assertEquals(textItem.getLevel(), newItem.getLevel());
        assertEquals(textItem.getText(), newItem.getText());
    }

}
