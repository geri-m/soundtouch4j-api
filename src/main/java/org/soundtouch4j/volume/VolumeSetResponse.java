package org.soundtouch4j.volume;

import org.soundtouch4j.common.Response;
import com.google.api.client.util.Key;

public class VolumeSetResponse implements Response {

  @Key("text()")
  private String volume;

  public VolumeSetResponse() {
  }

  public String getVolume() {
    return volume;
  }

  @Override
  public String toString() {
    return "VolumeSetResponse{" + "volume=" + volume + '}';
  }
}
