package org.nhlstenden.jabberpoint.slide;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.Vector;
import org.nhlstenden.jabberpoint.slide.item.BitMapItemFactory;
import org.nhlstenden.jabberpoint.slide.item.BitmapItem;
import org.nhlstenden.jabberpoint.slide.item.SlideItem;
import org.nhlstenden.jabberpoint.slide.item.SlideItemFactory;
import org.nhlstenden.jabberpoint.slide.item.TextItem;
import org.nhlstenden.jabberpoint.slide.item.TextItemFactory;

/**
 * A slide. This class has a drawing functionality.
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class Slide {
  public static final int WIDTH = 1200;
  public static final int HEIGHT = 800;

  protected String title;
  protected Vector<SlideItem> slideItems;


  public Slide(String title) {
    this.title = title;
    this.slideItems = new Vector<>();
  }

  public void addSlideItem(SlideItem slideItem) {
    this.slideItems.addElement(slideItem);
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void addTextItem(int level, String message) {
    SlideItemFactory textItemFactory = new TextItemFactory();
    this.addSlideItem(textItemFactory.createSlideItem(level, message));
  }

  public void addBitmapItem(int level, String imageName) {
    SlideItemFactory bitmapItemFactory = new BitMapItemFactory();
    this.addSlideItem(bitmapItemFactory.createSlideItem(level, imageName));
  }

  public SlideItem getSlideItem(int position) {
    return this.slideItems.elementAt(position);
  }

  public Vector<SlideItem> getSlideItems() {
    return this.slideItems;
  }

  public int getSizeOfSlideItems() {
    return this.slideItems.size();
  }

  public void draw(Graphics graphics, Rectangle area, ImageObserver view) {
    float scale = this.getScale(area);
    int xCoordinate = area.x;
    int yCoordinate = area.y;

    yCoordinate += this.drawTitle(graphics, xCoordinate, yCoordinate, view, scale);

    for (int i = 0; i < this.getSizeOfSlideItems(); i++) {
      SlideItem slideItem = this.getSlideItems().elementAt(i);
      Style style = Style.getStyle(slideItem.getLevel());
      slideItem.draw(xCoordinate, yCoordinate, scale, graphics, style, view);
      yCoordinate += slideItem.getBoundingBox(graphics, view, scale, style).height;
    }
  }

  public int drawTitle(Graphics g, int x, int y, ImageObserver view, float scale) {
    int defaultLevel = 0;
    SlideItemFactory textItemFactory = new TextItemFactory();
    Style style = Style.getStyle(defaultLevel);
    SlideItem slideItem = textItemFactory.createSlideItem(defaultLevel, this.getTitle());

    slideItem.draw(x, y, scale, g, style, view);

    return slideItem.getBoundingBox(g, view, scale, style).height;
  }

  public String getText(SlideItem slideItem) {
    String text = null;
    if (slideItem instanceof TextItem) {
      text = ((TextItem) slideItem).getText();
    } else if (slideItem instanceof BitmapItem) {
      text = ((BitmapItem) slideItem).getImageName();
    }
    return text;
  }

  public int getLevel(SlideItem slideItem) {

    return slideItem.getLevel();
  }

  private float getScale(Rectangle area) {
    return Math.min(
        ((float) area.width) / ((float) WIDTH), ((float) area.height) / ((float) HEIGHT));
  }
}
