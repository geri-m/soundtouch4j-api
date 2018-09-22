package org.soundtouch4j.preset;

import java.util.Date;
import org.soundtouch4j.common.ContentItem;
import com.google.api.client.util.Key;

public class Preset {

  @Key("@id")
  private int id;

  @Key("@createdOn")
  private int createdOn;

  @Key("@updatedOn")
  private int updatedOn;

  @Key("ContentItem")
  private ContentItem contentItem;

  public Preset() {
    // Auto Init/Reflection Requires Empty Constructor
  }

  public ContentItem getContentItem() {
    return contentItem;
  }

  public int getId() {
    return id;
  }

  public Date getCreatedOn() {
    return new Date(createdOn * 1000L);
  }

  public Date getUpdatedOn() {
    return new Date(updatedOn * 1000L);
  }

  @Override
  public String toString() {
    return "Preset{" + "id=" + id + ", createdOn=" + getCreatedOn() + ", updatedOn=" + getUpdatedOn() + ", contentItem=" + contentItem + '}';
  }
}
