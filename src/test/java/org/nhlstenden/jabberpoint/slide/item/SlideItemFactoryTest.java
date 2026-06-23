package org.nhlstenden.jabberpoint.slide.item;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SlideItemFactoryTest {
  @Test
  void createSlideItemViaTextItemFactory() {
    SlideItemFactory factory = new TextItemFactory();
    SlideItem item = factory.createSlideItem(0, "test");

    assertInstanceOf(TextItem.class, item);
    assertEquals("test", ((TextItem) item).getText());
  }
}
