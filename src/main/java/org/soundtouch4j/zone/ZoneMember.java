package org.soundtouch4j.zone;

import com.google.api.client.util.Key;

public class ZoneMember {

  @Key("@ipaddress")
  private String ipAddress;

  @Key("text()")
  private String macAddress;


  public ZoneMember(final String ipAddress, final String macAddress) {
    this.ipAddress = ipAddress;
    this.macAddress = macAddress;
  }

  public ZoneMember() {
    // Auto Init/Reflection Requires Empty Constructor
  }

  public String getIpAddress() {
    return ipAddress;
  }


  public String getMacAddress() {
    return macAddress;
  }

  @Override
  public String toString() {
    return "ZoneMember{" + "ipaddress='" + ipAddress + '\'' + ", macAddress='" + macAddress + '\'' + '}';
  }
}
