package org.nhlstenden.jabberpoint.command;

import org.junit.jupiter.api.Test;
import org.nhlstenden.jabberpoint.Presentation;

import com.ginsberg.junit.exit.ExpectSystemExit;

class QuitCommandTest {
  @Test
  @ExpectSystemExit
  void executeExits() {
    QuitCommand command = new QuitCommand(new Presentation());
    command.execute();
  }
}
