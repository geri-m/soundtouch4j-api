package org.soundtouch4j.info;

import com.google.api.client.util.Key;

/**
 * <pre>
 * {@code
 * <networkInfo type="SCM">
 * <macAddress>C8DF84AE0B6E</macAddress>
 * <ipAddress>192.168.178.61</ipAddress>
 * </networkInfo>
 * }
 * </pre>
 */


public class NetworkInfo {

  @Key("@type")
  private NetworkInfoTypeEnum type;

  @Key
  private String macAddress;

  @Key
  private String ipAddress;

  public NetworkInfo() {
    // Auto Init/Reflection Requires Empty Constructor
  }

  public String getMacAddress() {
    return macAddress;
  }

  public String getIpAddress() {
    return ipAddress;
  }

  public NetworkInfoTypeEnum getType() {
    return type;
  }

  @Override
  public String toString() {
    return "NetworkInfo{" + "type=" + type + ", macAddress='" + macAddress + '\'' + ", ipAddress='" + ipAddress + '\'' + '}';
  }
}
