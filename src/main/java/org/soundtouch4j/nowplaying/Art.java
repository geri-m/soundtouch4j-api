package org.soundtouch4j.nowplaying;

import com.google.api.client.util.Key;

public class Art {

  @Key("text()")
  private String value;

  @Key("@artImageStatus")
  private ArtImageStatusEnum artImageStatus;

  public Art() {
    // Auto Init/Reflection Requires Empty Constructor
  }

  public String getValue() {
    return value;
  }

  public ArtImageStatusEnum getArtImageStatus() {
    return artImageStatus;
  }

  @Override
  public String toString() {
    return "Art{value=" + value + ", artImageStatus=" + artImageStatus + '}';
  }
}
