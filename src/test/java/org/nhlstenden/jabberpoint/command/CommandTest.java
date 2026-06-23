package org.nhlstenden.jabberpoint.command;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.nhlstenden.jabberpoint.Presentation;
import org.nhlstenden.jabberpoint.slide.Slide;

class CommandTest {
  @Test
  void executeViaPreviousSlideCommand() {
    Presentation presentation = new Presentation();
    presentation.append(new Slide("slide 1"));
    presentation.append(new Slide("slide 2"));
    presentation.setSlideNumber(1);

    Command command = new PreviousSlideCommand(presentation);
    command.execute();

    assertEquals(0, presentation.getSlideNumber());
  }
}
