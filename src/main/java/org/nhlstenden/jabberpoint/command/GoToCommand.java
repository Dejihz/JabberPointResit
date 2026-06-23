package org.nhlstenden.jabberpoint.command;

import javax.swing.JOptionPane;
import org.nhlstenden.jabberpoint.Presentation;
import org.nhlstenden.jabberpoint.slide.SlideViewerComponent;
import org.nhlstenden.jabberpoint.utils.Constants;

/**
 * Command that navigates to a specific slide number.
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class GoToCommand extends Command {

  /** Creates a go-to command for the given presentation. */
  public GoToCommand(Presentation presentation) {
    super(presentation);
  }

  @Override
  public void execute() {
    SlideViewerComponent parent = this.presentation.getParent();
    String pageNumberStr = JOptionPane.showInputDialog("Page number?");
    int pageNumber = 0;

    try {
      pageNumber = Integer.parseInt(pageNumberStr);
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(
          parent, Constants.INT_ERR, Constants.JAB_ERR, JOptionPane.ERROR_MESSAGE);
      return;
    }

    if (pageNumber <= 0) {
      JOptionPane.showMessageDialog(
          parent, Constants.INT_ERR, Constants.JAB_ERR, JOptionPane.ERROR_MESSAGE);
      return;
    }

    this.presentation.setSlideNumber(pageNumber - 1);
  }
}
