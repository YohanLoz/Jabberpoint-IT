package jabberpoint.style;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

public class StyleFactoryTest {

    String correctDefaultStyleName = "default";
    String correctStyleName = "newDefault";
    int correctStyleIndent = 12;
    Color correctStyleColor = Color.black;
    Font correctStyleFont = new Font("Comic Sans MS", Font.PLAIN, 12);
    int correctStyleFontSize = 50;
    int correctStyleLeading = 10;

    static final String[] correctArgsStyle = {"default", "12", "#000000", "Comic Sans MS-0-12", "50", "10"};

    Style style;

    @BeforeEach
    void setUp() {
        StyleFactory.fullResetStyles();
        style = StyleFactory.getStyle(correctDefaultStyleName, correctStyleIndent, correctStyleColor, correctStyleFont, correctStyleFontSize, correctStyleLeading);
    }

    @Test
    void getStyleFromArgs_normal_returnsStyle() {
        assertEquals(Style.class, StyleFactory.getStyleFromArgs(correctArgsStyle).getClass());
    }

    @Test
    void getStyleFromArgs_null_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> StyleFactory.getStyleFromArgs(null));
    }

    @Test
    void getStyleFromArgs_empty_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> StyleFactory.getStyleFromArgs(new String[0]));
    }

    @Test
    void getStyle_withName_normal_returnsStyle() {
        assertEquals(Style.class, StyleFactory.getStyle("aaa", correctStyleIndent, correctStyleColor, correctStyleFont, correctStyleFontSize, correctStyleLeading).getClass());
    }

    @Test
    void getStyle_withName_nullName_doesNotThrow() {
        assertDoesNotThrow(() -> {
            StyleFactory.getStyle(null, correctStyleIndent, correctStyleColor, correctStyleFont, correctStyleFontSize, correctStyleLeading).getClass();
        });
    }

    @Test
    void getStyle_withName_emptyName_doesNotThrow() {
        assertDoesNotThrow(() -> {
            StyleFactory.getStyle("", correctStyleIndent, correctStyleColor, correctStyleFont, correctStyleFontSize, correctStyleLeading).getClass();
        });
    }

    @Test
    void getStyle_withName_nullColor_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            StyleFactory.getStyle("aaa", correctStyleIndent, null, correctStyleFont, correctStyleFontSize, correctStyleLeading);
        });
    }

    @Test
    void getStyle_withName_nullFont_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            StyleFactory.getStyle("aaa", correctStyleIndent, correctStyleColor, null, correctStyleFontSize, correctStyleLeading);
        });
    }

    @Test
    void getStyle_withName_alreadyExists_doesNotCreateNew() {
        StyleFactory.getStyle(correctDefaultStyleName, correctStyleIndent, correctStyleColor, correctStyleFont, correctStyleFontSize, correctStyleLeading);
        assertEquals(1, StyleFactory.getNumberOfStyles());
    }

    @Test
    void getStyle_withName_doesNotAlreadyExist_CreatesNew() {
        StyleFactory.getStyle("aaa", correctStyleIndent, correctStyleColor, correctStyleFont, correctStyleFontSize, correctStyleLeading);
        assertEquals(2, StyleFactory.getNumberOfStyles());
    }

    @Test
    void getStyle_withName_sameNameDifferentValues_UpdatesOldStyle() {
        int newFontSize = 99;
        StyleFactory.getStyle(correctDefaultStyleName, correctStyleIndent, correctStyleColor, correctStyleFont, newFontSize, correctStyleLeading);
        assertEquals(newFontSize, StyleFactory.getStyleByName(correctDefaultStyleName).fontSize);
        assertEquals(1, StyleFactory.getNumberOfStyles());
    }

    @Test
    void getStyle_withoutName_alreadyExists_doesNotCreateNew() {
        StyleFactory.getStyle(correctStyleIndent, correctStyleColor, correctStyleFont, correctStyleFontSize, correctStyleLeading);
        assertEquals(1, StyleFactory.getNumberOfStyles());
    }

    @Test
    void getStyle_withoutName_doesNotAlreadyExist_CreatesNew() {
        StyleFactory.getStyle(44, correctStyleColor, correctStyleFont, correctStyleFontSize, correctStyleLeading);
        assertEquals(2, StyleFactory.getNumberOfStyles());
    }


    @Test
    void getStyleByName_normal_success() {
        assertEquals(Style.class, StyleFactory.getStyleByName(correctDefaultStyleName).getClass());
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
        int id = StyleFactory.getIdByName(correctDefaultStyleName);
        assertEquals(correctDefaultStyleName, StyleFactory.getStyleById(id).name);
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
    void createNewStyle_noName_iteratesUnnamed() {
        int newStyleIndent = 12;
        Color newStyleColor = Color.black;
        Font newStyleFont = new Font("Comic Sans MS", Font.PLAIN, 12);
        int newStyleFontSize = 50;
        int newStyleLeading = 10;
        Style newstyle1 = StyleFactory.getStyle(13, newStyleColor, newStyleFont, newStyleFontSize, newStyleLeading);
        Style newstyle2 = StyleFactory.getStyle(12, newStyleColor, newStyleFont, newStyleFontSize, newStyleLeading);

        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(newstyle1.name);
        Matcher m2 = p.matcher(newstyle2.name);
        assertTrue(m.find());
        assertTrue(m2.find());
        assertEquals(1, Integer.parseInt(m2.group(0)) - Integer.parseInt(m.group(0)));
    }


    @Test
    void getIdByName_normal_returnsId() {
        assertEquals(style.id, StyleFactory.getIdByName(correctDefaultStyleName));
    }

    @Test
    void getIdByName_null_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> StyleFactory.getIdByName(null));
    }

    @Test
    void getIdByName_empty_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> StyleFactory.getIdByName(""));
    }

    @Test
    void getIdByName_doesNotExist_throwsIllegalArgumentException() {
        assertThrows(IllegalStateException.class, () -> StyleFactory.getIdByName("DoesNotExist"));
    }

    @Test
    void getStyleById_normal_returnsStyle() {
        int id = style.id;
        assertEquals(style, StyleFactory.getStyleById(id));
    }

    @Test
    void getStyleById_doesNotExist_returnsIllegalStateException() {
        int id = style.id + 1;
        assertThrows(IllegalStateException.class, () -> StyleFactory.getStyleById(id));
    }

    @Test
    void getStyleById_0_getsStyle() {
        assertEquals(style, StyleFactory.getStyleById(0));
    }

    @Test
    void getStyleById_negative_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> StyleFactory.getStyleById(-1));
    }

    @Test
    void getSaveString_normal_returnsString() {
        assertEquals(String.class, StyleFactory.getSaveString(style).getClass());
    }

    @Test
    void getSaveString_null_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> StyleFactory.getSaveString(null));
    }

    @Test
    void removeStyleByName_normal_removesStyle() {
        StyleFactory.removeStyleByName(correctDefaultStyleName);
        assertEquals(0, StyleFactory.getNumberOfStyles());
    }

    @Test
    void removeStyleByName_doesNotExist_doesNothing() {
        assertDoesNotThrow(() -> {
            StyleFactory.removeStyleByName("SCREAMER");
        });
    }

    @Test
    void removeStyleByName_null_throwsIllegalArgumentException() {
        assertDoesNotThrow(() -> {
            StyleFactory.removeStyleByName(null);
        });
    }

    @Test
    void removeStyleByName_empty_throwsIllegalArgumentException() {
        assertDoesNotThrow(() -> {
            StyleFactory.removeStyleByName("");
        });
    }

    @Test
    void getNumberOfStyles_returnsNumberOfStyles() {
        assertEquals(1, StyleFactory.getNumberOfStyles());
    }

    @Test
    void fullFlushAllStyles_flushesAllStyles() {
        StyleFactory.fullResetStyles();
        assertEquals(0, StyleFactory.getNumberOfStyles());
    }

}
