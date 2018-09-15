package org.soundtouch4j;

import org.soundtouch4j.info.InfoApi;
import org.soundtouch4j.key.KeyApi;
import org.soundtouch4j.nowplaying.NowPlayingApi;
import org.soundtouch4j.source.SourceApi;

public interface SoundTouch {

  KeyApi getKeyApi();

  InfoApi getInfoApi();

  NowPlayingApi getNowPlayingApi();

  SourceApi getSourceApi();

}
