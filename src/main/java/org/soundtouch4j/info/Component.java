package org.soundtouch4j.info;


import com.google.api.client.util.Key;

/**
 * <component>
 * <componentCategory>SCM</componentCategory>
 * <softwareVersion>19.0.5.42017.2794643 epdbuild.trunk.cepeswbld02.2018-04-25T18:23:30</softwareVersion>
 * <serialNumber>F8124895404720048620440</serialNumber>
 * </component>
 * <component>
 * <componentCategory>PackagedProduct</componentCategory>
 * <serialNumber>069428P81639976AE</serialNumber>
 * </component>
 */

public class Component {

  @Key
  private String softwareVersion;

  @Key
  private String serialNumber;

  public Component() {

  }

  public String getSoftwareVersion() {
    return softwareVersion;
  }

  public String getSerialNumber() {
    return serialNumber;
  }

  @Override
  public String toString() {
    return "Component{" + "softwareVersion='" + softwareVersion + '\'' + ", serialNumber='" + serialNumber + '\'' + '}';
  }
}
