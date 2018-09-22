package org.soundtouch4j.volume;

import org.soundtouch4j.common.Response;
import com.google.api.client.util.Key;


/**
 * Response from the '/volume' GET Call
 *
 * So far, the response is always
 * * <pre>
 * {@code
 * <?xml version="1.0" encoding="UTF-8" ?>
 * <volume deviceID="$MACADDR">
 * <targetvolume>$INT</targetvolume>
 * <actualvolume>$INT</actualvolume>
 * <muteenabled>$BOOL</muteenabled>
 * </volume>
 * }
 * </pre>
 */


public class VolumeGetResponse implements Response {

  @Key("@deviceID")
  private String deviceID;

  @Key("targetvolume")
  private int targetVolume;

  @Key("actualvolume")
  private int actualVolume;

  @Key("muteenabled")
  private boolean muteEnabled;

  public VolumeGetResponse() {
    // Auto Init/Reflection Requires Empty Constructor
  }

  @Override
  public String toString() {
    return "VolumeGetResponse{" + "deviceID='" + deviceID + '\'' + ", targetVolume=" + targetVolume + ", actualVolume=" + actualVolume + ", muteEnabled=" + muteEnabled + '}';
  }

  public String getDeviceID() {
    return deviceID;
  }

  public int getTargetVolume() {
    return targetVolume;
  }

  public int getActualVolume() {
    return actualVolume;
  }

  public boolean isMuteEnabled() {
    return muteEnabled;
  }
}
