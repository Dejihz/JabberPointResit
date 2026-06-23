package org.nhlstenden.jabberpoint;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.GraphicsEnvironment;
import javax.swing.JFrame;
import org.junit.jupiter.api.Test;

class AboutBoxTest {
  @Test
  void testShow() {
    if (GraphicsEnvironment.isHeadless()) {
      System.out.println("Headless environment detected. Skipping test.");
      return;
    }

    assertDoesNotThrow(() -> AboutBox.show(new JFrame()));
  }
}
