package org.nhlstenden.jabberpoint.command;

import java.io.IOException;
import javax.swing.JOptionPane;
import org.nhlstenden.jabberpoint.Presentation;
import org.nhlstenden.jabberpoint.accessor.Accessor;
import org.nhlstenden.jabberpoint.accessor.XMLAccessor;
import org.nhlstenden.jabberpoint.slide.SlideViewerComponent;
import org.nhlstenden.jabberpoint.utils.Constants;

/**
 * Command that loads a presentation from an XML file.
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class OpenFileCommand extends Command {

  /** Creates an open-file command for the given presentation. */
  public OpenFileCommand(Presentation presentation) {
    super(presentation);
  }

  @Override
  public void execute() {
    SlideViewerComponent parent = this.presentation.getParent();

    this.presentation.clear();
    Accessor xmlAccessor = new XMLAccessor();

    try {
      xmlAccessor.loadFile(this.presentation, Constants.TEST_FILE);
      this.presentation.setSlideNumber(0);
    } catch (IOException exc) {
      JOptionPane.showMessageDialog(
          parent, Constants.IO_ERR + exc, Constants.LOAD_ERR, JOptionPane.ERROR_MESSAGE);
    }

    parent.repaint();
  }
}
