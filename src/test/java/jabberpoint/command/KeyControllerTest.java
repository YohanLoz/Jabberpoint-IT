package jabberpoint.command;

import jabberpoint.presentationComponents.Presentation;
import jabberpoint.presentationComponents.Slide;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.awt.*;
import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;

public class KeyControllerTest {

    Presentation p;
    Frame frame;
    KeyController keyController;

    @BeforeEach
    void setup() throws AWTException {

        p = new Presentation();
        frame = new Frame();

        keyController = new KeyController(p);

    }

    @Test
    void keyPressed_pageDown_shouldExecuteNextSlide()
    {

        p.append(new Slide());

        KeyEvent key = new KeyEvent(frame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_PAGE_DOWN);
        keyController.keyPressed(key);

        assertEquals(0, p.getSlideNumber());

    }

    @Test
    void keyPressed_rightArrow_shouldExecuteNextSlide()
    {

        p.append(new Slide());

        KeyEvent key = new KeyEvent(frame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_RIGHT);
        keyController.keyPressed(key);

        assertEquals(0, p.getSlideNumber());

    }

    @Test
    void keyPressed_enter_shouldExecuteNextSlide()
    {

        p.append(new Slide());

        KeyEvent key = new KeyEvent(frame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_ENTER);
        keyController.keyPressed(key);

        assertEquals(0, p.getSlideNumber());

    }

    @Test
    void keyPressed_plus_shouldExecutePreviousSlide()
    {

        p.append(new Slide());
        p.append(new Slide());
        p.setSlideNumber(1);

        KeyEvent key = new KeyEvent(frame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_PAGE_UP);
        keyController.keyPressed(key);

        assertEquals(0, p.getSlideNumber());

    }

}
