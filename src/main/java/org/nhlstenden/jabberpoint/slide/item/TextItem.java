package org.nhlstenden.jabberpoint.slide.item;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.List;
import org.nhlstenden.jabberpoint.slide.Slide;
import org.nhlstenden.jabberpoint.slide.Style;

/**
 * A text item.
 *
 * <p>A SlideItem.TextItem has drawingfunctionality.
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class TextItem extends SlideItem {

  private final String text;

  public TextItem(int level, String text) {
    super(level);
    this.text = text;
  }

  public String getText() {
    return this.text == null ? "" : this.text;
  }

  /** Returns the attributed string for this text item. */
  public AttributedString getAttributedString(Style style, float scale) {
    AttributedString attributedString = new AttributedString(this.getText());
    attributedString.addAttribute(
        TextAttribute.FONT, style.getFont(scale), 0, this.getText().length());
    return attributedString;
  }

  /** Give the bounding box of the item. */
  public Rectangle getBoundingBox(
      Graphics graphics, ImageObserver observer, float scale, Style style) {
    double boundingWidth = 0;
    double boundingHeight = style.getLeading() * scale;

    for (TextLayout layout : this.getLayouts(graphics, style, scale)) {
      Rectangle2D bounds = layout.getBounds();
      if (bounds.getWidth() > boundingWidth) {
        boundingWidth = bounds.getWidth();
      }
      if (bounds.getHeight() > 0) {
        boundingHeight += bounds.getHeight();
      }
      boundingHeight += layout.getLeading() + layout.getDescent();
    }

    return new Rectangle(
        (int) (style.getIndent() * scale), 0, (int) boundingWidth, (int) boundingHeight);
  }

  /** Draw the item. */
  public void draw(
      int xCoordinate,
      int yCoordinate,
      float scale,
      Graphics graphics,
      Style style,
      ImageObserver imageObserver) {
    if (this.textIsEmpty()) {
      return;
    }

    List<TextLayout> layouts = this.getLayouts(graphics, style, scale);
    Graphics2D graphics2D = (Graphics2D) graphics;
    graphics2D.setColor(style.getColor());

    Point pen =
        new Point(
            xCoordinate + (int) (style.getIndent() * scale),
            yCoordinate + (int) (style.getLeading() * scale));

    for (TextLayout layout : layouts) {
      pen.y += (int) layout.getAscent();
      layout.draw(graphics2D, pen.x, pen.y);
      pen.y += (int) layout.getDescent();
    }
  }

  public boolean textIsEmpty() {
    return this.getText().isEmpty();
  }

  private List<TextLayout> getLayouts(Graphics graphics, Style style, float scale) {
    List<TextLayout> textLayouts = new ArrayList<>();

    AttributedString attributedString = this.getAttributedString(style, scale);
    Graphics2D graphics2D = (Graphics2D) graphics;
    FontRenderContext fontRenderContext = graphics2D.getFontRenderContext();

    float wrappingWidth = (Slide.WIDTH - style.getIndent()) * scale;
    LineBreakMeasurer measurer =
        new LineBreakMeasurer(attributedString.getIterator(), fontRenderContext);

    while (measurer.getPosition() < this.getText().length()) {
      textLayouts.add(measurer.nextLayout(wrappingWidth));
    }
    return textLayouts;
  }

  public String toString() {
    return "TextItem[" + this.getLevel() + "," + this.getText() + "]";
  }
}
