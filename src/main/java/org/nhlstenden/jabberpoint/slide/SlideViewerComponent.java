package org.nhlstenden.jabberpoint.slide;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JComponent;
import javax.swing.JFrame;
import org.nhlstenden.jabberpoint.Presentation;

/** Graphical component that can show slides. */
public class SlideViewerComponent extends JComponent {

  private Slide slide;
  private final Font labelFont;
  private Presentation presentation;
  private final JFrame frame;

  private static final long serialVersionUID = 227L;

  private static final Color BGCOLOR = Color.white;
  private static final Color COLOR = Color.black;
  private static final String FONTNAME = "Dialog";
  private static final int FONTSTYLE = Font.BOLD;
  private static final int FONTHEIGHT = 10;
  private static final int XPOS = 1100;
  private static final int YPOS = 20;

  public SlideViewerComponent(Presentation pres, JFrame frame) {
    this.setBackground(BGCOLOR);
    this.presentation = pres;
    this.labelFont = new Font(FONTNAME, FONTSTYLE, FONTHEIGHT);
    this.frame = frame;
  }

  public Dimension getPreferredSize() {
    return new Dimension(Slide.WIDTH, Slide.HEIGHT);
  }

  public void update(Presentation presentation, Slide data) {
    if (data == null) {
      this.repaint();
      return;
    }
    this.presentation = presentation;
    this.slide = data;
    this.repaint();
    this.frame.setTitle(presentation.getTitle());
  }

  public void paintComponent(Graphics g) {
    g.setColor(BGCOLOR);
    g.fillRect(0, 0, this.getSize().width, this.getSize().height);
    if (this.presentation.getSlideNumber() < 0 || this.slide == null) {
      return;
    }
    g.setFont(this.labelFont);
    g.setColor(COLOR);
    g.drawString(
        "Slide "
            + (1 + this.presentation.getSlideNumber())
            + " of "
            + this.presentation.getSize(),
        XPOS,
        YPOS);
    Rectangle area = new Rectangle(0, YPOS, this.getWidth(), (this.getHeight() - YPOS));
    this.slide.draw(g, area, this);
  }
}
