package jabberpoint.command.commands;

import jabberpoint.presentationComponents.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class NextSlideTest {

    Presentation presentation;
    Command nextSlide;

    @BeforeEach
    void setup()
    {

        presentation = new Presentation();
        nextSlide = new NextSlideCommand(presentation);

    }

    @Test
    void nextSlide_emptyPresentation_shouldNotChangeSlideNumber()
    {
        presentation.clear();

        nextSlide.execute();

        assertEquals(-1, presentation.getSlideNumber());
    }

    @Test
    void nextSlide_singleSlide_shouldNotChangeSlideNumber()
    {
        presentation.append(new Slide());

        nextSlide.execute();

        assertEquals(0, presentation.getSlideNumber());
    }

    @Test
    void nextSlide_twoSlides_shouldIncreaseBy1()
    {
        presentation.clear();
        presentation.append(new Slide());
        presentation.append(new Slide());

        nextSlide.execute();

        assertEquals(0, presentation.getSlideNumber());

        nextSlide.execute();

        assertEquals(1, presentation.getSlideNumber());
    }

    @Test
    void nextSlide_endOfPresentation_shouldNotIncrease()
    {
        presentation.clear();
        presentation.append(new Slide());
        presentation.append(new Slide());
        presentation.setSlideNumber(1);

        nextSlide.execute();

        assertEquals(1, presentation.getSlideNumber());

    }

}
