package jabberpoint.style;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class StyleTest {
    static String correctName = "default";
    static int correctIndent = 12;
    static Color correctColor = Color.cyan;
    static Font correctFont = new Font("Impact", Font.PLAIN, 12);
    static int correctFontSize = 40;
    static int correctLeading = 12;

    Style style;

    @BeforeEach
    void setUp() {
        style = new Style(correctName, correctIndent, correctColor, correctFont, correctFontSize, correctLeading);
    }
    @Test
    void getSet_normal_success(){
        assertEquals(correctName, style.name);
        assertEquals(correctIndent, style.indent);
        assertEquals(correctColor, style.color);
        assertEquals(correctFont, style.font);
        assertEquals(correctFontSize, style.fontSize);
        assertEquals(correctLeading, style.leading);
    }

    @Test
    void idIncrements_2times_success(){
        Style style1 = new Style(correctName, correctIndent, correctColor, correctFont, correctFontSize, correctLeading);
        Style style2 = new Style(correctName, correctIndent, correctColor, correctFont, correctFontSize, correctLeading);

        assertEquals(1, style2.id-style1.id);
    }

    @Test
    void getFittedFont_returnsDuplicateFont(){
        Font fittedfont = style.getFittedFont(1);
        assertEquals(style.font.getAttributes(), fittedfont.getAttributes());
        assertEquals(style.font.getStyle(), fittedfont.getStyle());
    }

    @Test
    void getFittedFont_1_success(){
        assertEquals(style.font.getSize(), style.getFittedFont(1).getSize());
    }
    @Test
    void getFittedFont_5x_success(){
        assertEquals(style.font.getSize()*5, style.getFittedFont(5).getSize());
    }
    @Test
    void getFittedFont_0_success(){
        assertEquals(0, style.getFittedFont(0).getSize());
    }
    @Test
    void getFittedFont_neg5x_success(){
        assertEquals(style.font.getSize()*-5, style.getFittedFont(-5).getSize());
    }
}
