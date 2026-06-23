package org.nhlstenden.jabberpoint.slide.item;

/**
 * Abstract factory for creating slide items.
 *
 * <p>Concrete factories create either text or bitmap items.
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public abstract class SlideItemFactory {

  /** Creates a slide item at the given level with the given value. */
  public abstract SlideItem createSlideItem(int level, String value);
}
