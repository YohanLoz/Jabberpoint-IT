package jabberpoint.command;

import jabberpoint.presentationComponents.Presentation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class MenuControllerTest
{

    Frame frame;
    Presentation presentation;
    MenuController menu;

    @BeforeEach
    void setup()
    {

        frame = new Frame();
        presentation = new Presentation();

        menu = new MenuController(frame, presentation);

    }

    @Test
    void mkMenuItem_inputOpen_shouldReturnMenuItemCorrectName()
    {

        String open = "Open";

        assertEquals(open, menu.mkMenuItem(open).getLabel());

    }

}
