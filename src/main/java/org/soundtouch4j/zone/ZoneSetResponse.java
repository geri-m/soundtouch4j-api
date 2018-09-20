package org.soundtouch4j.zone;

import org.soundtouch4j.common.Response;
import com.google.api.client.util.Key;

public class ZoneSetResponse implements Response {

  @Key("text()")
  private String response;

  public ZoneSetResponse() {
  }

  public String getResponse() {
    return response;
  }

  @Override
  public String toString() {
    return "ZoneSetResponse{" + "response='" + response + '\'' + '}';
  }
}
