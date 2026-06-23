package org.nhlstenden.jabberpoint.command;

import org.nhlstenden.jabberpoint.Presentation;

/**
 * Command that navigates to the previous slide.
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class PreviousSlideCommand extends Command {

  /** Creates a previous-slide command for the given presentation. */
  public PreviousSlideCommand(Presentation presentation) {
    super(presentation);
  }

  @Override
  public void execute() {
    this.presentation.prevSlide();
  }
}
