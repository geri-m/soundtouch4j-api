package org.soundtouch4j.info;

import java.io.IOException;
import org.soundtouch4j.AbstractApi;
import org.soundtouch4j.SoundTouchApi;
import org.soundtouch4j.SoundTouchApiException;

public class InfoApi extends AbstractApi {

  private static final String PATH_FOR_API = "info";

  public InfoApi(final SoundTouchApi soundTouchApi) {
    super(soundTouchApi);
  }


  /**
   * Method to turn on/off the Sound Touch
   *
   * @return InfoResponse Element that was received from the speaker
   * @throws SoundTouchApiException is thrown in case the communication to the speaker failed.
   */

  public InfoResponse getInfo() throws SoundTouchApiException {
    try {
      return soundTouchApi.getSoundTouchApiClient()
          .get(PATH_FOR_API, InfoResponse.class);
    } catch (final IOException e) {
      throw new SoundTouchApiException(e);
    }
  }
}

