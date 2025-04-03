package jabberpoint;

import jabberpoint.presentationComponents.Presentation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DemoPresentationTest {

    Accessor demo;
    Presentation presentation;
    String fileName;

    @BeforeEach
    void setup() {

        demo = new DemoPresentation();

        presentation = new Presentation();
        fileName = "Unused Filename";

    }

    @Test
    void saveFile_callMethod_shouldThrowIllegalStateException() {

        assertThrows(IllegalStateException.class, () -> {
            demo.saveFile(presentation, fileName);
        });

    }

    @Test
    void loadFile_callMethod_shouldHaveTitleDemo() throws IOException {

        demo.loadFile(presentation, fileName);

        String title = presentation.getTitle();

        assertEquals("Demo presentationComponents.Presentation", title);

    }

}
