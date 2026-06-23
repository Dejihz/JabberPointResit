package org.nhlstenden.jabberpoint;

import java.io.IOException;

/** Reads and writes presentation data. */
public abstract class Accessor {
  public static final String DEMO_NAME = "Demonstration presentation";
  public static final String DEFAULT_EXTENSION = ".xml";

  public static Accessor getDemoAccessor() {
    return new DemoPresentation();
  }

  public abstract void loadFile(Presentation p, String fn) throws IOException;

  public abstract void saveFile(Presentation p, String fn) throws IOException;
}
