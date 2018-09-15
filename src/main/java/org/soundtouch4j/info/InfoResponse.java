package org.soundtouch4j.info;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.soundtouch4j.common.Response;
import com.google.api.client.util.Key;

/**
 * Response from the '/info' GET Call
 *
 * So far, the response is
 * <pre>
 * {@code
 * <?xml version="1.0" encoding="UTF-8" ?>
 * <info deviceID="C8DF84AE0B6E">
 * <name>SoundTouch 20</name>
 * <type>SoundTouch 20</type>
 * <margeAccountUUID>6990307</margeAccountUUID>
 * <components>
 * <component>
 * <componentCategory>SCM</componentCategory>
 * <softwareVersion>19.0.5.42017.2794643 epdbuild.trunk.cepeswbld02.2018-04-25T18:23:30</softwareVersion>
 * <serialNumber>F8124895404720048620440</serialNumber>
 * </component>
 * <component>
 * <componentCategory>PackagedProduct</componentCategory>
 * <serialNumber>069428P81639976AE</serialNumber>
 * </component>
 * </components>
 * <margeURL>https://streaming.bose.com</margeURL>
 * <networkInfo type="SCM">
 * <macAddress>C8DF84AE0B6E</macAddress>
 * <ipAddress>192.168.178.61</ipAddress>
 * </networkInfo>
 * <networkInfo type="SMSC">
 * <macAddress>C8DF84615084</macAddress>
 * <ipAddress>192.168.178.61</ipAddress>
 * </networkInfo>
 * <moduleType>sm2</moduleType>
 * <variant>spotty</variant>
 * <variantMode>normal</variantMode>
 * <countryCode>GB</countryCode>
 * <regionCode>GB</regionCode>
 * </info>
 * }
 * </pre>
 */


public class InfoResponse implements Response {

  private static final String COMPONENT = "component";

  @Key("@deviceID")
  private String deviceID;

  @Key
  private String name;

  @Key
  private String type;

  @Key
  private Map<String, List<Component>> components;

  @Key
  private List<NetworkInfo> networkInfo;

  public InfoResponse() {

  }

  public String getDeviceID() {
    return deviceID;
  }

  public String getName() {
    return name;
  }

  public String getType() {
    return type;
  }


  public List<Component> getComponents() {
    return new ArrayList<>(components.get(COMPONENT));
  }


  public List<NetworkInfo> getNetworkInfo() {
    return new ArrayList<>(networkInfo);
  }

  @Override
  public String toString() {
    return "InfoResponse{" + "deviceID='" + deviceID + '\'' + ", name='" + name + '\'' + ", type='" + type + '\'' + ", components=" + components + ", networkInfo=" + networkInfo + '}';
  }
}
