package org.nhlstenden.jabberpoint.slide;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import javax.swing.JFrame;
import org.junit.jupiter.api.Test;
import org.nhlstenden.jabberpoint.Presentation;

class SlideViewerComponentTest {
  @Test
  void getPreferredSize() {
    if (GraphicsEnvironment.isHeadless()) {
      System.out.println("Headless environment detected. Skipping test.");
      return;
    }

    Presentation presentation = new Presentation();
    JFrame frame = new JFrame();
    SlideViewerComponent component = new SlideViewerComponent(presentation, frame);

    assertEquals(new Dimension(Slide.WIDTH, Slide.HEIGHT), component.getPreferredSize());
  }

  @Test
  void updateWithNullSlide() {
    if (GraphicsEnvironment.isHeadless()) {
      System.out.println("Headless environment detected. Skipping test.");
      return;
    }

    Presentation presentation = new Presentation();
    JFrame frame = new JFrame();
    SlideViewerComponent component = new SlideViewerComponent(presentation, frame);

    assertDoesNotThrow(() -> component.update(presentation, null));
  }
}
