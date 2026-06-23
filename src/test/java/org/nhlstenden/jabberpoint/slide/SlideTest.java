package org.nhlstenden.jabberpoint.slide;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.nhlstenden.jabberpoint.slide.item.TextItem;

class SlideTest {
  @Test
  void addTextItemAndGetText() {
    Slide slide = new Slide("title");
    slide.addTextItem(1, "hello");

    assertEquals(1, slide.getSizeOfSlideItems());
    assertEquals("title", slide.getTitle());

    TextItem item = (TextItem) slide.getSlideItems().elementAt(0);
    assertEquals("hello", slide.getText(item));
    assertEquals(1, slide.getLevel(item));
  }

  @Test
  void addBitmapItemAndGetText() {
    Slide slide = new Slide("title");
    slide.addBitmapItem(2, "test.png");

    assertEquals(1, slide.getSizeOfSlideItems());
    assertEquals("test.png", slide.getText(slide.getSlideItems().elementAt(0)));
  }
}
