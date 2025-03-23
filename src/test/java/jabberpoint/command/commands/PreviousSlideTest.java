package jabberpoint.command.commands;

import jabberpoint.presentationComponents.Presentation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class PreviousSlideTest {

    Presentation presentation;
    Command previousSlide;

    @BeforeEach
    void setup()
    {

        presentation = new Presentation();
        previousSlide = new PreviousSlideCommand(presentation);

    }

    @Test
    void previousSlide_emptyPresentation_shouldNotChangeSlideNumber()
    {
        presentation.clear();

        previousSlide.execute();

        assertEquals(-1, presentation.getSlideNumber());
    }

    @Test
    void previousSlide_singleSlide_shouldNotChangeSlideNumber()
    {
        presentation.setSlideNumber(0);

        previousSlide.execute();

        assertEquals(0, presentation.getSlideNumber());
    }

    @Test
    void previousSlide_twoSlides_shouldReduceBy1()
    {
        presentation.setSlideNumber(2);

        previousSlide.execute();

        assertEquals(1, presentation.getSlideNumber());

        previousSlide.execute();

        assertEquals(0, presentation.getSlideNumber());
    }

}
