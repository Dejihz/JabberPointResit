package org.nhlstenden.jabberpoint;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import org.nhlstenden.jabberpoint.command.QuitCommand;

/** Key listener for presentation navigation. */
public class KeyController extends KeyAdapter {

  private final Presentation presentation;

  public KeyController(Presentation p) {
    this.presentation = p;
  }

  public void keyPressed(KeyEvent keyEvent) {
    int keyCode = keyEvent.getKeyCode();
    QuitCommand quitCommand = new QuitCommand(this.presentation);

    if (this.isNextSlideKey(keyCode)) {
      this.presentation.nextSlide();
      return;
    }

    if (this.isPrevSlideKey(keyCode)) {
      this.presentation.prevSlide();
      return;
    }

    if (this.isQuitKey(keyCode)) {
      quitCommand.execute();
    }
  }

  private boolean isNextSlideKey(int keyCode) {
    return keyCode == KeyEvent.VK_PAGE_DOWN
        || keyCode == KeyEvent.VK_DOWN
        || keyCode == KeyEvent.VK_ENTER
        || keyCode == '+';
  }

  private boolean isPrevSlideKey(int keyCode) {
    return keyCode == KeyEvent.VK_PAGE_UP || keyCode == KeyEvent.VK_UP || keyCode == '-';
  }

  private boolean isQuitKey(int keyCode) {
    return keyCode == 'q' || keyCode == 'Q';
  }
}
