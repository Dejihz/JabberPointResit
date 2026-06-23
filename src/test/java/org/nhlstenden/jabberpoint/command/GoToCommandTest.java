package org.nhlstenden.jabberpoint.command;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.nhlstenden.jabberpoint.Presentation;

class GoToCommandTest {
  @Test
  void constructCommand() {
    Presentation presentation = new Presentation();
    assertNotNull(new GoToCommand(presentation));
  }
}
