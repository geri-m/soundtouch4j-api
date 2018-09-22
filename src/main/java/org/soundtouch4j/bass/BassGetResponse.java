package org.soundtouch4j.bass;

import org.soundtouch4j.common.Response;
import com.google.api.client.util.Key;

public class BassGetResponse implements Response {

  public static final String ELEMENT_NAME = "bass";

  @Key("@deviceID")
  private String deviceID;

  @Key("targetbass")
  private int targetBass;


  @Key("actualbass")
  private int actualBass;

  public BassGetResponse() {
    // Auto Init/Reflection Requires Empty Constructor
  }

  public int getTargetBass() {
    return targetBass;
  }

  public int getActualBass() {
    return actualBass;
  }

  public String getDeviceID() {
    return deviceID;
  }

  @Override
  public String toString() {
    return "BassGetResponse{" + "deviceID='" + deviceID + '\'' + ", targetBass=" + targetBass + ", actualBass=" + actualBass + '}';
  }
}
