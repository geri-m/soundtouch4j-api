package org.soundtouch4j.nowplaying;

import org.soundtouch4j.SoundTouchApi;
import org.soundtouch4j.common.AbstractApi;

public class NowPlayingApi extends AbstractApi {

  private static final String PATH_FOR_API = "now_playing";

  public NowPlayingApi(final SoundTouchApi soundTouchApi) {
    super(soundTouchApi);
  }

  /**
   * Method to turn on/off the Sound Touch
   *
   * @return NowPlayingResponse Element that was received from the speaker
   */

  public NowPlayingResponse nowPlaying() {
    return soundTouchApi.getSoundTouchApiClient()
        .get(PATH_FOR_API, NowPlayingResponse.class);
  }
}