package jabberpoint.presentationComponents.slideItems;

import jabberpoint.SlideViewerComponent;
import jabberpoint.SlideViewerFrame;
import jabberpoint.presentationComponents.Presentation;
import jabberpoint.style.StyleFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

public class TextItemTest {

    TextItem textItem;
    String defaultTestText = "hello world";
    Presentation presentation = new Presentation();
    SlideViewerFrame slideViewerFrame = new SlideViewerFrame("AAA", presentation);
    SlideViewerComponent slideViewerComponent = new SlideViewerComponent(presentation, slideViewerFrame);

    BufferedImage image = new BufferedImage(12, 12, BufferedImage.TYPE_3BYTE_BGR);
    Graphics2D graphics = (Graphics2D) image.getGraphics();

    @BeforeEach
    void setup() {
        StyleFactory.fullResetStyles();
        StyleFactory.getStyle("aaa", 12, new Color(1, 1, 1), new Font("Papyrus", Font.PLAIN, 13), 11, 10);
        textItem = new TextItem();
        textItem.setStyleId(0);
        textItem.setText(defaultTestText);
    }

    @Test
    void getText_isNull_returnsEmpty() {
        textItem.setText(null);
        assertEquals("", textItem.getText());
    }

    @Test
    void setGetText_normal_returnsText() {
        assertEquals(defaultTestText, textItem.getText());
    }

    @Test
    void setStyleId_exists_savesId() {
        assertDoesNotThrow(() -> {
            textItem.setStyleId(0);
        });
    }

    @Test
    void setStyleId_doesNotExist_savesId() {
        assertDoesNotThrow(() -> {
            textItem.setStyleId(122);
        });
    }

    @Test
    void getAttributedString_pos_returnsAttributedString() {
        assertNotNull(textItem.getAttributedString(1));
    }

    @Test
    void getAttributedString_zero_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            textItem.getAttributedString(0);
        });
    }

    @Test
    void getAttributedString_neg_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            textItem.getAttributedString(-1);
        });
    }

    @Test
    void getBoundingBox_normal_doesNotThrow() {
        assertDoesNotThrow(() -> {
            textItem.getBoundingBox(graphics, slideViewerComponent, 1);
        });
    }

    @Test
    void getBoundingBox_graphicsNull_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            textItem.getBoundingBox(null, slideViewerComponent, 1);
        });
    }

    @Test
    void getBoundingBox_imageObserverNull_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            textItem.getBoundingBox(graphics, null, 1);
        });
    }

    @Test
    void getBoundingBox_scaleZero_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            textItem.getBoundingBox(graphics, slideViewerComponent, 0);
        });
    }

    @Test
    void getBoundingBox_scaleNeg_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            textItem.getBoundingBox(graphics, slideViewerComponent, -1);
        });
    }

    @Test
    void draw_normal_doesNotThrow() {
        assertDoesNotThrow(() -> {
            textItem.draw(1, graphics, slideViewerComponent);
        });
    }

    @Test
    void draw_graphicsNull_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            textItem.draw(1, null, slideViewerComponent);
        });
    }

    @Test
    void draw_observerNull_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            textItem.draw(1, graphics, null);
        });
    }

    @Test
    void draw_scaleZero_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            textItem.draw(0, graphics, slideViewerComponent);
        });
    }

    @Test
    void draw_scaleNeg_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            textItem.draw(-1, graphics, slideViewerComponent);
        });
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
        textItem.setStyleId(0);

        TextItem newItem = (TextItem) textItem.clone();
        assertEquals(textItem.getLevel(), newItem.getLevel());
        assertEquals(textItem.getText(), newItem.getText());
        assertEquals(StyleFactory.getStyleById(0), newItem.getStyle());
    }

    @Test
    void toString_returnsString() {
        assertNotNull(textItem.toString());
    }

    @Test
    void getStyle_hasStyle_returnsStyle() {
        textItem.setStyleId(0);
        assertDoesNotThrow(() -> {
            textItem.getStyle();
        });
        assertNotNull(textItem.getStyle());
    }

    @Test
    void getStyle_noStyle_throwsIllegalStateException() {
        textItem.setStyleId(-1);
        assertThrows(IllegalStateException.class, () -> {
            textItem.getStyle();
        });
    }

    @Test
    void getStyle_styleDoesNotExist_throwsIllegalStateException() {
        textItem.setStyleId(112);
        assertThrows(IllegalStateException.class, () -> {
            textItem.getStyle();
        });
    }

    @Test
    void getSaveString_normal_returnsString() {
        assertDoesNotThrow(() -> {
            textItem.getSaveString();
        });
        assertNotNull(textItem.getSaveString());
    }

    @Test
    void getSaveString_noStyle_throwsIllegalStateException() {
        textItem.setStyleId(-1);
        assertThrows(IllegalStateException.class, () -> {
            textItem.getSaveString();
        });
    }
}
