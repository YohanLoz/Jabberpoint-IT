package jabberpoint.command.commands;

import jabberpoint.presentationComponents.Presentation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OpenPresentationTest {

    Frame frame;
    Presentation presentation;
    Command openPresentation;

    @BeforeEach
    void setup() {

        frame = new Frame();
        presentation = new Presentation();
        openPresentation = new OpenPresentationCommand(frame, presentation);

    }

    @Test
    void openPresentation_emptyPresentation_shouldNotChangeSlideNumber() {
        presentation.clear();

        openPresentation.execute();

        assertEquals(0, presentation.getSlideNumber());
    }

    @Test
    void openPresentation_singleSlide_shouldNotChangeSlideNumber() {
        presentation.setSlideNumber(0);

        openPresentation.execute();

        assertEquals(0, presentation.getSlideNumber());
    }

    @Test
    void openPresentation_twoSlides_shouldReduceBy1() {
        presentation.setSlideNumber(2);

        openPresentation.execute();

        assertEquals(0, presentation.getSlideNumber());

    }

}
