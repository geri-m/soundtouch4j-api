package org.soundtouch4j.bass;

import org.soundtouch4j.common.Request;
import com.google.api.client.util.Key;

public class BassRequest implements Request {

  public static final String ELEMENT_NAME = "bass";

  @Key("text()")
  private int bass;

  public BassRequest(final int bass) {
    this.bass = bass;
  }

}
