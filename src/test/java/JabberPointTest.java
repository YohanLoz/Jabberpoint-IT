import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JabberPointTest {

    @Test
    public void main_noParams_doesNotThrow() {
        assertDoesNotThrow(() -> JabberPoint.main(new String[0]));
    }
}
