import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.nhlstenden.jabberpoint.slide.Style;

class StyleTest {

    @Test
    void createStylesInitializesHeadingStyle() {
        Style.createStyles();
        assertEquals(0, Style.getStyle(0).getIndent());
    }
}
