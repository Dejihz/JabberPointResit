package org.nhlstenden.jabberpoint.slide.item;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SlideItemTest {
  @Test
  void getLevel() {
    SlideItem item = new TextItem(2, "test");

    assertEquals(2, item.getLevel());
  }
}
