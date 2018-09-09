package org.soundtouch4j.key;

import org.soundtouch4j.AbstractRequest;
import com.google.api.client.util.Key;

/**
 * Request Object for the '/key' HTTP-POST Request. Requires the Key as well as the Key State
 */

public class KeyRequest extends AbstractRequest {

  public static final String ELEMENT_NAME = "key";

  // This value is always "Gabbo" - see documentation.
  @Key("@sender")
  private final String sender = "Gabbo";
  @Key("text()")
  private final String key;
  @Key("@state")
  private String keyState;


  public KeyRequest(final String key, final String keyState) {
    super();
    this.keyState = keyState;
    this.key = key;
  }

}
