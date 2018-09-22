package org.soundtouch4j.select;

import org.soundtouch4j.common.Response;
import com.google.api.client.util.Key;

public class SelectResponse implements Response {

  @Key("text()")
  private String status;

  public SelectResponse() {
    // Auto Init/Reflection Requires Empty Constructor
  }

  public String getStatus() {
    return status;
  }
}
