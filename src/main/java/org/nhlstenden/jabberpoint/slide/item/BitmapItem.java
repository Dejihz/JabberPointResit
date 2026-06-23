package org.nhlstenden.jabberpoint.slide.item;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.nhlstenden.jabberpoint.slide.Style;

/** Bitmap item that draws itself. */
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
