package org.soundtouch4j;

import org.soundtouch4j.bass.BassApi;
import org.soundtouch4j.basscapabilities.BassCapabilitiesApi;
import org.soundtouch4j.group.GroupApi;
import org.soundtouch4j.info.InfoApi;
import org.soundtouch4j.key.KeyApi;
import org.soundtouch4j.name.NameApi;
import org.soundtouch4j.nowplaying.NowPlayingApi;
import org.soundtouch4j.preset.PresetApi;
import org.soundtouch4j.select.SelectApi;
import org.soundtouch4j.source.SourceApi;
import org.soundtouch4j.volume.VolumeApi;
import org.soundtouch4j.zone.ZoneApi;

public interface SoundTouch {

  KeyApi getKeyApi();

  InfoApi getInfoApi();

  NowPlayingApi getNowPlayingApi();

  SourceApi getSourceApi();

  SelectApi getSelectApi();

  VolumeApi getVolumeApi();

  PresetApi getPresetApi();

  NameApi getNameApi();

  BassCapabilitiesApi getBassCapabilitiesApi();

  BassApi getBassApi();

  ZoneApi getZoneApi();

  GroupApi getGroupApi();

}
