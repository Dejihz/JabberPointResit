package org.nhlstenden.jabberpoint.command;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.GraphicsEnvironment;
import javax.swing.JFrame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nhlstenden.jabberpoint.Presentation;
import org.nhlstenden.jabberpoint.slide.SlideViewerComponent;
import org.nhlstenden.jabberpoint.utils.Constants;

class OpenFileCommandTest {
  @BeforeEach
  void loadConstants() {
    Constants.loadConstants();
  }

  @Test
  void executeLoadsTestFile() {
    if (GraphicsEnvironment.isHeadless()) {
      System.out.println("Headless environment detected. Skipping test.");
      return;
    }

    Presentation presentation = new Presentation();
    JFrame frame = new JFrame();
    presentation.setShowView(new SlideViewerComponent(presentation, frame));

    OpenFileCommand command = new OpenFileCommand(presentation);
    assertDoesNotThrow(command::execute);
    assertTrue(presentation.getSize() > 0);
  }
}
