package jabberpoint;

import jabberpoint.presentationComponents.Presentation;
import jabberpoint.presentationComponents.Slide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SlideViewerComponentTest {

    JFrame frame;
    Presentation presentation;
    SlideViewerComponent svc;

    @BeforeEach
    void setup() {

        frame = new JFrame();
        presentation = new Presentation();

        svc = new SlideViewerComponent(presentation, frame);

    }

    @Test
    void getPreferredSize_default_shouldReturnDimension() {

        Dimension dim = svc.getPreferredSize();

        assertEquals(800, dim.height);
        assertEquals(1200, dim.width);

    }

    @Test
    void paintComponent_default_shouldDraw() {

        Graphics g = Mockito.mock(Graphics.class);
        svc.paintComponent(g);

    }

    @Test
    void update_emptyPresentationSlide_shouldWork() {

        Slide slide = new Slide();
        svc.update(presentation, slide);

    }

    @Test
    void update_nullSlide_shouldWork() {

        Slide slide = new Slide();
        svc.update(presentation, null);

    }

}
