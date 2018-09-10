package org.soundtouch4j.key;

import com.google.api.client.util.Key;

/**
 * Response from the '/key' POST Call
 *
 * So far, the response is always
 * * <pre>
 * {@code
 * <?xml version="1.0" encoding="UTF-8" ?>
 * <status>/key</status>
 * }
 * </pre>
 */


public class KeyResponse {


  @Key("text()")
  private String value;

  public KeyResponse() {

  }

  public String getValue() {
    return value;
  }
}
