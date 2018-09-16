package org.soundtouch4j;

import org.soundtouch4j.info.InfoApi;
import org.soundtouch4j.key.KeyApi;
import org.soundtouch4j.nowplaying.NowPlayingApi;
import org.soundtouch4j.select.SelectApi;
import org.soundtouch4j.source.SourceApi;
import org.soundtouch4j.volume.VolumeApi;

public interface SoundTouch {

  KeyApi getKeyApi();

  InfoApi getInfoApi();

  NowPlayingApi getNowPlayingApi();

  SourceApi getSourceApi();

  SelectApi getSelectApi();

  VolumeApi getVolumeApi();

}
