package org.soundtouch4j.bass;

import org.soundtouch4j.common.Response;
import com.google.api.client.util.Key;

public class BassGetResponse implements Response {

  public static final String ELEMENT_NAME = "bass";

  @Key("targetbass")
  private int targetBass;


  @Key("actualbass")
  private int actualBass;

  public BassGetResponse() {

  }

  public int getTargetBass() {
    return targetBass;
  }

  public int getActualBass() {
    return actualBass;
  }

  @Override
  public String toString() {
    return "BassGetResponse{" + "targetBass=" + targetBass + ", actualBass=" + actualBass + '}';
  }
}
