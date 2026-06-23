package org.nhlstenden.jabberpoint.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

/**
 * Utility for reading resources from the classpath.
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class ResourceAccessor {
  public static ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

  /** Returns an input stream for the given classpath resource. */
  public static InputStream getResource(String resource) {
    if (resource == null) {
      System.out.println("Resource file name is null.");
      System.out.println(Constants.RESOURCE_LOADING_ERR);
      System.exit(0);
    }

    InputStream stream = classLoader.getResourceAsStream(resource);

    if (stream == null) {
      System.out.println("Resource file not found: " + resource);
      System.out.println(Constants.RESOURCE_LOADING_ERR);
      System.exit(0);
    }

    return stream;
  }

  /** Returns the contents of the given classpath resource as a string. */
  public static String getResourceAsString(String resource) {
    InputStream resourceStream = getResource(resource);
    return new BufferedReader(new InputStreamReader(resourceStream, StandardCharsets.UTF_8))
        .lines()
        .collect(Collectors.joining("\n"));
  }
}
