package org.nhlstenden.jabberpoint.slide.item;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.nhlstenden.jabberpoint.slide.Style;

/**
 * De klasse voor een Bitmap item
 *
 * <p>Bitmap items have the responsibility to draw themselves.
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class BitmapItem extends SlideItem {

  private final String imageName;
  private BufferedImage bufferedImage;

  public BitmapItem(int level, String imageName) {
    super(level);
    this.imageName = imageName;
    this.bufferedImage = this.loadImage(imageName);
  }

  private BufferedImage loadImage(String name) {
    try {
      BufferedImage image = ImageIO.read(new File(name));
      if (image != null) {
        return image;
      }

      var stream = BitmapItem.class.getClassLoader().getResourceAsStream(name);
      if (stream != null) {
        try (var input = stream) {
          return ImageIO.read(input);
        }
      }
    } catch (IOException e) {
      System.err.println("File " + name + " not found");
    }

    System.err.println("File " + name + " not found");
    return null;
  }

  public String getImageName() {
    return this.imageName;
  }

  public BufferedImage getBufferedImage() {
    return this.bufferedImage;
  }

  /** Give the bounding box of the image. */
  public Rectangle getBoundingBox(
      Graphics graphics, ImageObserver observer, float scale, Style style) {
    if (this.bufferedImage == null) {
      return new Rectangle((int) (style.getIndent() * scale), 0, 0, 0);
    }

    int xCoordinate = (int) (style.getIndent() * scale);
    int yCoordinate = 0;
    int width = (int) (this.getBufferedImage().getWidth(observer) * scale);
    int height =
        ((int) (style.getLeading() * scale))
            + (int) (this.getBufferedImage().getHeight(observer) * scale);

    return new Rectangle(xCoordinate, yCoordinate, width, height);
  }

  /** Draw the image. */
  public void draw(
      int xCoordinate,
      int yCoordinate,
      float scale,
      Graphics graphics,
      Style style,
      ImageObserver observer) {
    if (this.bufferedImage == null) {
      return;
    }

    int x = xCoordinate + (int) (style.getIndent() * scale);
    int y = yCoordinate + (int) (style.getLeading() * scale);
    int width = (int) (this.getBufferedImage().getWidth(observer) * scale);
    int height = (int) (this.getBufferedImage().getHeight(observer) * scale);

    graphics.drawImage(this.getBufferedImage(), x, y, width, height, observer);
  }

  public String toString() {
    return "BitmapItem[" + this.getLevel() + "," + this.getImageName() + "]";
  }
}
