package org.soundtouch4j.bass;

import org.soundtouch4j.common.Response;
import com.google.api.client.util.Key;

public class BassSetResponse implements Response {


  @Key("text()")
  private String value;

  public BassSetResponse() {

  }

  public String getValue() {
    return value;
  }

}
