package org.soundtouch4j;

import java.net.MalformedURLException;
import java.net.URL;
import org.junit.Assert;

public class Const {

  public static final String URL = "http://soundtouch-20.fritz.box:8090";

  public static URL getUrl() {
    try {
      return new URL(Const.URL);
    } catch (final MalformedURLException e) {
      Assert.fail();
      return null;
    }
  }
}
