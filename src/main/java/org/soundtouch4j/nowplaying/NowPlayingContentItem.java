package org.soundtouch4j.nowplaying;

import com.google.api.client.util.Key;

/**
 * Content Item of the NowPlaying Response.
 */

public class NowPlayingContentItem {

  @Key("@source")
  private SourceEnum source;

  @Key("@isPresetable")
  private boolean isPresetable;

  @Key("@location")
  private String location;

  @Key("@sourceAccount")
  private String sourceAccount;

  @Key
  private String itemName;

  @Key
  private String containerArt;

  public NowPlayingContentItem() {

  }

  public SourceEnum getSource() {
    return source;
  }

  public boolean isPresetable() {
    return isPresetable;
  }

  public String getLocation() {
    return location;
  }

  public String getSourceAccount() {
    return sourceAccount;
  }

  public String getItemName() {
    return itemName;
  }

  public String getContainerArt() {
    return containerArt;
  }
}
