package org.soundtouch4j.nowplaying;

import java.io.IOException;
import org.soundtouch4j.AbstractApi;
import org.soundtouch4j.SoundTouchApi;
import org.soundtouch4j.SoundTouchApiException;

public class NowPlayingApi extends AbstractApi {

  private static final String PATH_FOR_API = "now_playing";

  public NowPlayingApi(final SoundTouchApi soundTouchApi) {
    super(soundTouchApi);
  }

  /**
   * Method to turn on/off the Sound Touch
   *
   * @throws SoundTouchApiException is thrown in case the communication to the speaker failed.
   */

  public NowPlayingResponse nowPlaying() throws SoundTouchApiException {
    try {
      return soundTouchApi.getSoundTouchApiClient()
          .get(PATH_FOR_API, NowPlayingResponse.class);
    } catch (final IOException e) {
      throw new SoundTouchApiException(e);
    }
  }
}