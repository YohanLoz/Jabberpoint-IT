package jabberpoint.style;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

public class StyleFactoryTest {
    String defaultName = "default";
    Style style;
    @BeforeEach
    void setUp() {
        StyleFactory.fullFlushAllStyles();
        style = StyleFactory.getStyle(defaultName, 12, Color.black, new Font("Comic Sans MS", Font.PLAIN, 12), 50, 10);
    }

    @Test
    void getStyleByName_normal_success() {
        assertEquals(Style.class, StyleFactory.getStyleByName(defaultName).getClass());
    }
    @Test
    void getStyleByName_notExist_success() {
        assertNull(StyleFactory.getStyleByName("notExist"));
    }
    @Test
    void getStyleByName_null_success() {
        assertThrows(IllegalArgumentException.class, () -> StyleFactory.getStyleByName(null));
    }

    @Test
    void getIdByNameAndGetNameById_success() {
        int id = StyleFactory.getIdByName(defaultName);
        assertEquals(defaultName, StyleFactory.getStyleById(id).name);
    }

    @Test
    void createNewStyle_withName_success() {
        String newStyleName = "aaa";
        int newStyleIndent = 12;
        Color newStyleColor = Color.black;
        Font newStyleFont = new Font("Comic Sans MS", Font.PLAIN, 12);
        int newStyleFontSize = 50;
        int newStyleLeading = 10;
        Style newstyle = StyleFactory.getStyle(newStyleName, newStyleIndent, newStyleColor, newStyleFont, newStyleFontSize, newStyleLeading);
        assertNotNull(newstyle);
        assertEquals(newStyleName, newstyle.name);
        assertEquals(newStyleIndent, newstyle.indent);
        assertEquals(newStyleColor, newstyle.color);
        assertEquals(newStyleFont, newstyle.font);
        assertEquals(newStyleFontSize, newstyle.fontSize);
        assertEquals(newStyleLeading, newstyle.leading);
    }

    @Test
    void createNewStyle_withName2_success() {
        String newStyleName = "bbb";
        int newStyleIndent = 14;
        Color newStyleColor = Color.blue;
        Font newStyleFont = new Font("Comic Sans MS", Font.ITALIC, 12);
        int newStyleFontSize = 100;
        int newStyleLeading = 34;
        Style newstyle = StyleFactory.getStyle(newStyleName, newStyleIndent, newStyleColor, newStyleFont, newStyleFontSize, newStyleLeading);
        assertNotNull(newstyle);
        assertEquals(newStyleName, newstyle.name);
        assertEquals(newStyleIndent, newstyle.indent);
        assertEquals(newStyleColor, newstyle.color);
        assertEquals(newStyleFont, newstyle.font);
        assertEquals(newStyleFontSize, newstyle.fontSize);
        assertEquals(newStyleLeading, newstyle.leading);
    }

    @Test
    void createNewStyle_noName_success() {
        int newStyleIndent = 12;
        Color newStyleColor = Color.black;
        Font newStyleFont = new Font("Comic Sans MS", Font.PLAIN, 12);
        int newStyleFontSize = 50;
        int newStyleLeading = 10;
        Style newstyle = StyleFactory.getStyle(newStyleIndent, newStyleColor, newStyleFont, newStyleFontSize, newStyleLeading);
        assertNotNull(newstyle);
        assertEquals(newStyleIndent, newstyle.indent);
        assertEquals(newStyleColor, newstyle.color);
        assertEquals(newStyleFont, newstyle.font);
        assertEquals(newStyleFontSize, newstyle.fontSize);
        assertEquals(newStyleLeading, newstyle.leading);
    }

    @Test
    void createNewStyle_noName_iteratesUnnamed(){
        int newStyleIndent = 12;
        Color newStyleColor = Color.black;
        Font newStyleFont = new Font("Comic Sans MS", Font.PLAIN, 12);
        int newStyleFontSize = 50;
        int newStyleLeading = 10;
        Style newstyle1 = StyleFactory.getStyle(newStyleIndent, newStyleColor, newStyleFont, newStyleFontSize, newStyleLeading);
        Style newstyle2 = StyleFactory.getStyle(newStyleIndent, newStyleColor, newStyleFont, newStyleFontSize, newStyleLeading);

        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(newstyle1.name);
        Matcher m2 = p.matcher(newstyle2.name);
        assertTrue(m.find());
        assertTrue(m2.find());
        assertEquals(1, Integer.parseInt(m2.group(0))-Integer.parseInt(m.group(0)));
    }

}
