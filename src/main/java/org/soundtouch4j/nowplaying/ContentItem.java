package org.soundtouch4j.nowplaying;

import com.google.api.client.util.Key;

/**
 * Content Item of the NowPlaying Response.
 */

public class ContentItem {

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

  public ContentItem() {

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

  @Override
  public String toString() {
    return "ContentItem{" + "source=" + source + ", isPresetable=" + isPresetable + ", location='" + location + '\'' + ", sourceAccount='" + sourceAccount + '\'' + ", itemName" + "='" + itemName + '\'' + ", containerArt='" + containerArt + '\'' + '}';
  }
}
