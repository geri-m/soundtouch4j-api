package org.soundtouch4j.nowplaying;

import com.google.api.client.util.Key;

public class ConnectionStatusInfo {

  @Key("@status")
  private String status;

  @Key("@deviceName")
  private boolean deviceName;

  public ConnectionStatusInfo() {

  }

  public String getStatus() {
    return status;
  }

  public boolean isDeviceName() {
    return deviceName;
  }

  @Override
  public String toString() {
    return "ConnectionStatusInfo{" + "status='" + status + '\'' + ", deviceName=" + deviceName + '}';
  }
}
