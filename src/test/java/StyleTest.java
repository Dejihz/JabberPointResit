import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class StyleTest {

    @Test
    void createStylesInitializesHeadingStyle() {
        Style.createStyles();
        assertEquals(0, Style.getStyle(0).indent);
    }
}
