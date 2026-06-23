package org.nhlstenden.jabberpoint.command;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.GraphicsEnvironment;
import javax.swing.JFrame;
import org.junit.jupiter.api.Test;
import org.nhlstenden.jabberpoint.Presentation;
import org.nhlstenden.jabberpoint.slide.Slide;
import org.nhlstenden.jabberpoint.slide.SlideViewerComponent;

class NewFileCommandTest {
  @Test
  void executeClearsPresentation() {
    if (GraphicsEnvironment.isHeadless()) {
      System.out.println("Headless environment detected. Skipping test.");
      return;
    }

    Presentation presentation = new Presentation();
    JFrame frame = new JFrame();
    presentation.setShowView(new SlideViewerComponent(presentation, frame));
    presentation.append(new Slide("slide 1"));

    NewFileCommand command = new NewFileCommand(presentation);
    assertDoesNotThrow(command::execute);
    assertEquals(0, presentation.getSize());
  }
}
