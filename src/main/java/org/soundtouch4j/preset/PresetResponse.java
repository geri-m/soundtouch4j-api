package org.soundtouch4j.preset;

import java.util.List;
import org.soundtouch4j.common.Response;
import com.google.api.client.util.Key;

public class PresetResponse implements Response {

  @Key("preset")
  private List<Preset> presetList;

  public PresetResponse() {
    // Auto Init/Reflection Requires Empty Constructor
  }

  public List<Preset> getPresetList() {
    return presetList;
  }

  @Override
  public String toString() {
    return "PresetResponse{presetList=" + presetList + '}';
  }
}
