package org.soundtouch4j.group;

import com.google.api.client.util.Key;

public class GroupRole {

  @Key("deviceId")
  private String deviceId;

  @Key("role")
  private String role;

  @Key("ipAddress")
  private String ipAddress;

  public GroupRole() {

  }

  public String getDeviceId() {
    return deviceId;
  }

  public String getRole() {
    return role;
  }

  public String getIpAddress() {
    return ipAddress;
  }

  @Override
  public String toString() {
    return "GroupRole{" + "deviceId='" + deviceId + '\'' + ", role=" + role + ", ipAddress='" + ipAddress + '\'' + '}';
  }
}
