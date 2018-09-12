package org.soundtouch4j.nowplaying;

import java.net.URL;
import com.google.api.client.util.Key;

public class Art {

  @Key("@text()")
  private URL value;

  @Key("@artImageStatus")
  private ArtImageStatusEnum artImageStatus;

  public Art() {
  }

  public URL getValue() {
    return value;
  }

  public ArtImageStatusEnum getArtImageStatus() {
    return artImageStatus;
  }

  @Override
  public String toString() {
    return "Art{" + "value=" + value + ", artImageStatus=" + artImageStatus + '}';
  }
}
