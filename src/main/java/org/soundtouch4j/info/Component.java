package org.soundtouch4j.info;


import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.soundtouch4j.SoundTouchApiException;
import com.google.api.client.util.Key;

/**
 * <pre>
 * {@code
 * <component>
 * <componentCategory>SCM</componentCategory>
 * <softwareVersion>19.0.5.42017.2794643 epdbuild.trunk.cepeswbld02.2018-04-25T18:23:30</softwareVersion>
 * <serialNumber>F8124895404720048620440</serialNumber>
 * </component>
 * <component>
 * <componentCategory>PackagedProduct</componentCategory>
 * <serialNumber>069428P81639976AE</serialNumber>
 * </component>
 * }
 * </pre>
 */

public class Component {

  private static final String VERSION_REGEX = "(\\d{2})\\.\\d+\\.\\d+\\.\\d+\\.\\d+\\s[a-z]+.[a-z]+.[a-z]+\\d+\\.\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}";
  private static final Pattern VERSION_PATTERN = Pattern.compile(VERSION_REGEX);

  @Key
  private String softwareVersion;

  @Key
  private String serialNumber;

  public Component() {
    // Auto Init/Reflection Requires Empty Constructor
  }

  public String getSoftwareVersion() {
    return softwareVersion;
  }

  public String getSerialNumber() {
    return serialNumber;
  }

  public int getVersion() throws SoundTouchApiException {
    final Matcher matcher = VERSION_PATTERN.matcher(softwareVersion);
    if (matcher.matches()) {
      return Integer.parseInt(matcher.group(1));
    }
    throw new SoundTouchApiException(String.format("Unable to parse Version String '%s'", softwareVersion));
  }

  @Override
  public String toString() {
    return "Component{" + "softwareVersion='" + softwareVersion + '\'' + ", serialNumber='" + serialNumber + '\'' + '}';
  }
}
