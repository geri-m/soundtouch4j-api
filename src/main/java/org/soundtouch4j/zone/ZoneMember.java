package org.soundtouch4j.zone;

import com.google.api.client.util.Key;

public class ZoneMember {

  @Key("@ipaddress")
  private String ipaddress;

  @Key("text()")
  private String macAddress;


  public ZoneMember(final String ipAddress, final String macAddress) {
    this.ipaddress = ipAddress;
    this.macAddress = macAddress;
  }

  public ZoneMember() {

  }

  public String getIpaddress() {
    return ipaddress;
  }


  public String getMacAddress() {
    return macAddress;
  }

  @Override
  public String toString() {
    return "ZoneMember{" + "ipaddress='" + ipaddress + '\'' + ", macAddress='" + macAddress + '\'' + '}';
  }
}
