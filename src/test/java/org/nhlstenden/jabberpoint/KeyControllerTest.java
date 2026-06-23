package org.nhlstenden.jabberpoint;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import org.junit.jupiter.api.Test;
import org.nhlstenden.jabberpoint.slide.Slide;

class KeyControllerTest {
  @Test
  void nextSlideOnPageDown() {
    Presentation presentation = new Presentation();
    presentation.append(new Slide("slide 1"));
    presentation.append(new Slide("slide 2"));
    presentation.setSlideNumber(0);

    KeyController controller = new KeyController(presentation);
    KeyEvent event =
        new KeyEvent(
            new JPanel(),
            KeyEvent.KEY_PRESSED,
            System.currentTimeMillis(),
            0,
            KeyEvent.VK_PAGE_DOWN,
            KeyEvent.CHAR_UNDEFINED);

    controller.keyPressed(event);

    assertEquals(1, presentation.getSlideNumber());
  }

  @Test
  void prevSlideOnPageUp() {
    Presentation presentation = new Presentation();
    presentation.append(new Slide("slide 1"));
    presentation.append(new Slide("slide 2"));
    presentation.setSlideNumber(1);

    KeyController controller = new KeyController(presentation);
    KeyEvent event =
        new KeyEvent(
            new JPanel(),
            KeyEvent.KEY_PRESSED,
            System.currentTimeMillis(),
            0,
            KeyEvent.VK_PAGE_UP,
            KeyEvent.CHAR_UNDEFINED);

    controller.keyPressed(event);

    assertEquals(0, presentation.getSlideNumber());
  }
}
