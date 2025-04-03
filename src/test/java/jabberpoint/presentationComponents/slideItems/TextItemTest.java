package jabberpoint.presentationComponents.slideItems;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextItemTest {

    TextItem textItem;

    @BeforeEach
    void setup() {
        textItem = new TextItem();
    }

    @Test
    void clone_emptyTextItem_shouldReturnEmptyTextItem() {
        TextItem newItem = (TextItem) textItem.clone();
        assertEquals(textItem.getLevel(), newItem.getLevel());
        assertEquals(textItem.getText(), newItem.getText());
    }

    @Test
    void clone_textItemWithLevel_shouldReturnIdenticalTextItem() {
        textItem = new TextItem();
        textItem.setLevel(1);
        textItem.setText("Something");

        TextItem newItem = (TextItem) textItem.clone();
        assertEquals(textItem.getLevel(), newItem.getLevel());
        assertEquals(textItem.getText(), newItem.getText());
    }

}
