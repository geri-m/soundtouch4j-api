package org.soundtouch4j.source;

import java.util.ArrayList;
import java.util.List;
import org.soundtouch4j.common.Response;
import com.google.api.client.util.Key;

public class SourceResponse implements Response {

  private static final String SOURCE_ITEM = "sourceItem";

  @Key("@deviceID")
  private String deviceID;

  @Key("sourceItem")
  private List<SourceItem> sourceItem;

  public SourceResponse() {
  }

  public List<SourceItem> getSourceItems() {
    return new ArrayList<>(sourceItem);
  }

  public String getDeviceID() {
    return deviceID;
  }

  @Override
  public String toString() {
    return "SourceResponse{" + "deviceID='" + deviceID + '\'' + ", sourceItems=" + sourceItem + '}';
  }
}
