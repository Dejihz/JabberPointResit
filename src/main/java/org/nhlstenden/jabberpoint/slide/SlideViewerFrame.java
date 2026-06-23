package org.nhlstenden.jabberpoint.slide;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import org.nhlstenden.jabberpoint.KeyController;
import org.nhlstenden.jabberpoint.MenuController;
import org.nhlstenden.jabberpoint.Presentation;

/** Application window for a slide viewer component. */
public class SlideViewerFrame extends JFrame {
  private static final long serialVersionUID = 3227L;

  private static final String JABTITLE = "Jabberpoint 1.6 - OU";
  public static final int WIDTH = 1200;
  public static final int HEIGHT = 800;

  public SlideViewerFrame(String title, Presentation presentation) {
    super(title);
    SlideViewerComponent slideViewerComponent = new SlideViewerComponent(presentation, this);
    presentation.setShowView(slideViewerComponent);
    this.setupWindow(slideViewerComponent, presentation);
  }

  public void setupWindow(SlideViewerComponent slideViewerComponent, Presentation presentation) {
    this.setTitle(JABTITLE);
    this.addWindowListener(
        new WindowAdapter() {
          public void windowClosing(WindowEvent e) {
            System.exit(0);
          }
        });
    this.getContentPane().add(slideViewerComponent);
    this.addKeyListener(new KeyController(presentation));
    this.setMenuBar(new MenuController(this, presentation));
    this.setSize(new Dimension(WIDTH, HEIGHT));
    this.setVisible(true);
  }
}
