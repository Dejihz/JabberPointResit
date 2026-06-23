package org.nhlstenden.jabberpoint.slide;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.GraphicsEnvironment;
import org.junit.jupiter.api.Test;
import org.nhlstenden.jabberpoint.Presentation;

class SlideViewerFrameTest {
  @Test
  void constructFrame() {
    if (GraphicsEnvironment.isHeadless()) {
      System.out.println("Headless environment detected. Skipping test.");
      return;
    }

    Presentation presentation = new Presentation();
    assertDoesNotThrow(() -> new SlideViewerFrame("test", presentation));
    assertNotNull(presentation.getParent());
  }
}
