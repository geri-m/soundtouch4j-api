package org.soundtouch4j.nowplaying;

import java.io.IOException;
import org.soundtouch4j.AbstractApi;
import org.soundtouch4j.SoundTouchApi;
import org.soundtouch4j.SoundTouchApiException;
import org.soundtouch4j.key.KeyPressValueEnum;
import org.soundtouch4j.key.KeyRequest;
import org.soundtouch4j.key.KeyResponse;
import org.soundtouch4j.key.KeyStateEnum;

public class NowPlayingApi extends AbstractApi {

  private static final String PATH_FOR_API = "now_playing\t\n";

  public NowPlayingApi(final SoundTouchApi soundTouchApi) {
    super(soundTouchApi);
  }


  /**
   * Method to turn on/off the Sound Touch
   *
   * @throws SoundTouchApiException is thrown in case the communication to the speaker failed.
   */

  public void nowPlaying() throws SoundTouchApiException {
    try {
      soundTouchApi.getSoundTouchApiClient()
          .post(PATH_FOR_API, KeyRequest.ELEMENT_NAME, new KeyRequest(KeyPressValueEnum.POWER, KeyStateEnum.PRESS), KeyResponse.class);
      soundTouchApi.getSoundTouchApiClient()
          .post(PATH_FOR_API, KeyRequest.ELEMENT_NAME, new KeyRequest(KeyPressValueEnum.POWER, KeyStateEnum.RELEASE), KeyResponse.class);
    } catch (final IOException e) {
      throw new SoundTouchApiException(e);
    }
  }
}