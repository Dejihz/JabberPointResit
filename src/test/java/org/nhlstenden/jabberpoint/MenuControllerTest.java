package org.nhlstenden.jabberpoint;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;
import org.junit.jupiter.api.Test;

public class MenuControllerTest {

  @Test
  public void MenuItemCreation_withoutShortcut() {
    if (GraphicsEnvironment.isHeadless()) {
      System.out.println("Headless environment detected. Skipping test.");
      return;
    }

    Frame testFrame = new Frame();
    Presentation testPresentation = new Presentation();
    MenuController testMenuController = new MenuController(testFrame, testPresentation);

    MenuItem testMenuItem = testMenuController.createMenuItem("Sample", e -> {});

    assertNotNull(testMenuItem);
    assertEquals(testMenuItem.getLabel(), "Sample");

    assertNull(testMenuItem.getShortcut());
  }

  @Test
  public void MenuItemCreation_withShortcut() {
    if (GraphicsEnvironment.isHeadless()) {
      System.out.println("Headless environment detected. Skipping test.");
      return;
    }

    Frame testFrame = new Frame();
    Presentation testPresentation = new Presentation();
    MenuController testMenuController = new MenuController(testFrame, testPresentation);

    MenuItem testMenuItem = testMenuController.createMenuItem("Sample", e -> {}, 'S');

    assertNotNull(testMenuItem);
    assertEquals(testMenuItem.getLabel(), "Sample");
    assertEquals(testMenuItem.getShortcut(), new MenuShortcut('S'));
  }
}
