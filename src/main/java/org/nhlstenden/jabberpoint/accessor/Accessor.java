package org.nhlstenden.jabberpoint.accessor;

import java.io.IOException;
import org.nhlstenden.jabberpoint.Presentation;

/** Reads and writes presentation data. */
public abstract class Accessor {

  public static Accessor getDemoAccessor() {
    return new DemoPresentation();
  }

  public abstract void loadFile(Presentation p, String fn) throws IOException;

  public abstract void saveFile(Presentation p, String fn) throws IOException;
}
