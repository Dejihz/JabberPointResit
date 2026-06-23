package org.nhlstenden.jabberpoint;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.GraphicsEnvironment;
import org.junit.jupiter.api.Test;

public class JabberPointTest {
  @Test
  void testMain() {
    if (GraphicsEnvironment.isHeadless()) {
      System.out.println("Headless environment detected. Skipping test.");
      return;
    }

    assertDoesNotThrow(() -> JabberPoint.main(new String[] {}));
  }

  @Test
  void testMainWithArguments() {
    if (GraphicsEnvironment.isHeadless()) {
      System.out.println("Headless environment detected. Skipping test.");
      return;
    }

    assertDoesNotThrow(() -> JabberPoint.main(new String[] {"test.xml"}));
  }
}
