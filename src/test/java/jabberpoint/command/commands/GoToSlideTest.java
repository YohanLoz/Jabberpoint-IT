package jabberpoint.command.commands;

import jabberpoint.presentationComponents.Presentation;
import jabberpoint.presentationComponents.Slide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GoToSlideTest {

    Presentation presentation;
    Command goToSlide;

    @BeforeEach
    void setup() {

        presentation = new Presentation();
        goToSlide = new GoToSlideCommand(presentation);

    }

    @Test
    void nextSlide_emptyPresentation_shouldNotChangeSlideNumber() {
        presentation.clear();


        assertEquals(-1, presentation.getSlideNumber());
    }

    @Test
    void nextSlide_singleSlide_shouldNotChangeSlideNumber() {
        presentation.append(new Slide());


        assertEquals(-1, presentation.getSlideNumber());
    }

    @Test
    void nextSlide_twoSlides_shouldIncreaseBy1() {
        presentation.clear();
        presentation.append(new Slide());
        presentation.append(new Slide());


        assertEquals(-1, presentation.getSlideNumber());


        assertEquals(-1, presentation.getSlideNumber());
    }

    @Test
    void nextSlide_endOfPresentation_shouldNotIncrease() {
        presentation.clear();
        presentation.append(new Slide());
        presentation.append(new Slide());
        presentation.setSlideNumber(1);

        assertEquals(1, presentation.getSlideNumber());

    }

}
