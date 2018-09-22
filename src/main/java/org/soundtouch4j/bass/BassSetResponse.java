package org.soundtouch4j.bass;

import org.soundtouch4j.common.Response;
import com.google.api.client.util.Key;

public class BassSetResponse implements Response {


  @Key("text()")
  private String value;

  public BassSetResponse() {
    // Auto Init/Reflection Requires Empty Constructor
  }

  public String getValue() {
    return value;
  }

}
