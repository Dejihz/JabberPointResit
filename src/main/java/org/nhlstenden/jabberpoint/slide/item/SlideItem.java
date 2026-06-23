package org.nhlstenden.jabberpoint.slide.item;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import org.nhlstenden.jabberpoint.slide.Style;

/**
 * The abstract class for an item on a slide.
 *
 * <p>All SlideItems have drawing functionality.
 */
public abstract class SlideItem {

  private int level;

  public SlideItem(int level) {
    this.level = level;
  }

  public int getLevel() {
    return this.level;
  }

  public abstract Rectangle getBoundingBox(
      Graphics g, ImageObserver observer, float scale, Style style);

  public abstract void draw(
      int x, int y, float scale, Graphics g, Style style, ImageObserver observer);
}
