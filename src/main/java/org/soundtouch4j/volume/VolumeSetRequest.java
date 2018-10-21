package org.soundtouch4j.volume;

import org.soundtouch4j.common.Request;
import com.google.api.client.util.Key;


/**
 * Response from the '/volume' GET Call
 *
 * So far, the response is always
 * * <pre>
 * {@code
 * <?xml version="1.0" encoding="UTF-8" ?>
 * <volume>20</volume>
 * }
 * </pre>
 */


public class VolumeSetRequest implements Request {

  public static final String ELEMENT_NAME = "volume";

  @Key("text()")
  private int volume;

  public VolumeSetRequest(final int volume) {
    this.volume = volume;
  }

  @Override
  public String toString() {
    return "VolumeSetRequest{volume=" + volume + '}';
  }
}
