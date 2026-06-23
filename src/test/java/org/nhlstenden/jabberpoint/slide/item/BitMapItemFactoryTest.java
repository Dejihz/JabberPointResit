package org.nhlstenden.jabberpoint.slide.item;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BitMapItemFactoryTest {
  @Test
  void testCreateSlideItem() {
    BitMapItemFactory factory = new BitMapItemFactory();
    SlideItem item = factory.createSlideItem(1, "test.png");

    assertInstanceOf(BitmapItem.class, item);

    BitmapItem bitmapItem = (BitmapItem) item;

    assertEquals(1, bitmapItem.getLevel());
    assertEquals("test.png", bitmapItem.getImageName());
  }
}
