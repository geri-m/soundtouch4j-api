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
  private int targetvolume;

  @Key("actualvolume")
  private int actualvolume;

  @Key("muteenabled")
  private boolean muteenabled;

  public VolumeGetResponse() {
  }

  @Override
  public String toString() {
    return "VolumeGetResponse{" + "deviceID='" + deviceID + '\'' + ", targetvolume=" + targetvolume + ", actualvolume=" + actualvolume + ", muteenabled=" + muteenabled + '}';
  }
}
