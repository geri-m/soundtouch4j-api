package org.soundtouch4j.nowplaying;

import com.google.api.client.util.Key;

public class ConnectionStatusInfo {

  @Key("@status")
  private String status;

  @Key("@deviceName")
  private String deviceName;

  public ConnectionStatusInfo() {

  }

  public String getStatus() {
    return status;
  }

  public String getDeviceName() {
    return deviceName;
  }

  @Override
  public String toString() {
    return "ConnectionStatusInfo{" + "status='" + status + '\'' + ", deviceName=" + deviceName + '}';
  }
}
