package org.nhlstenden.jabberpoint.command;

import org.nhlstenden.jabberpoint.Presentation;

/**
 * Command that clears the presentation and starts a new empty one.
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class NewFileCommand extends Command {

  /** Creates a new-file command for the given presentation. */
  public NewFileCommand(Presentation presentation) {
    super(presentation);
  }

  @Override
  public void execute() {
    this.presentation.clear();
    this.presentation.setSlideNumber(0);
    this.presentation.getParent().repaint();
  }
}
