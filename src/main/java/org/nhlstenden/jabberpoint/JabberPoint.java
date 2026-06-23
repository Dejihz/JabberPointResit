package org.nhlstenden.jabberpoint;

import java.io.IOException;
import javax.swing.JOptionPane;
import org.nhlstenden.jabberpoint.slide.SlideViewerFrame;
import org.nhlstenden.jabberpoint.slide.Style;
import org.nhlstenden.jabberpoint.utils.Constants;

/** JabberPoint main program. */
public class JabberPoint {

  public static void main(String[] argv) {
    Style.createStyles();
    Constants.loadConstants();
    Presentation presentation = new Presentation();
    new SlideViewerFrame(Constants.TITLE_FRAME, presentation);
    try {
      if (argv.length == 0) {
        Accessor.getDemoAccessor().loadFile(presentation, "");
      } else {
        new XMLAccessor().loadFile(presentation, argv[0]);
      }
      presentation.setSlideNumber(0);
    } catch (IOException ex) {
      JOptionPane.showMessageDialog(
          null, Constants.IO_ERR + ex, Constants.JAB_ERR, JOptionPane.ERROR_MESSAGE);
    }
  }
}
