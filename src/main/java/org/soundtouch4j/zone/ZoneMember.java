package org.soundtouch4j.zone;

import com.google.api.client.util.Key;

public class ZoneMember {

  @Key("@ipaddress")
  private String ipaddress;

  @Key("text()")
  private String value;


  public ZoneMember() {

  }

  public String getIpaddress() {
    return ipaddress;
  }


  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return "ZoneMember{" + "ipaddress='" + ipaddress + '\'' + ", value='" + value + '\'' + '}';
  }
}
