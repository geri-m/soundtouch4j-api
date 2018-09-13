package org.soundtouch4j.key;

import java.io.IOException;
import org.soundtouch4j.AbstractApi;
import org.soundtouch4j.SoundTouchApi;
import org.soundtouch4j.SoundTouchApiException;

public class KeyApi extends AbstractApi {

  private static final String PATH_FOR_API = "key";

  public KeyApi(final SoundTouchApi soundTouchApi) {
    super(soundTouchApi);
  }


  /**
   * Method to turn on/off the Sound Touch
   *
   * @throws SoundTouchApiException is thrown in case the communication to the speaker failed.
   */

  public KeyResponse power() throws SoundTouchApiException {
    try {
      soundTouchApi.getSoundTouchApiClient()
          .post(PATH_FOR_API, KeyRequest.ELEMENT_NAME, new KeyRequest(KeyPressValueEnum.POWER, KeyStateEnum.PRESS), KeyResponse.class);
      return soundTouchApi.getSoundTouchApiClient()
          .post(PATH_FOR_API, KeyRequest.ELEMENT_NAME, new KeyRequest(KeyPressValueEnum.POWER, KeyStateEnum.RELEASE), KeyResponse.class);
    } catch (final IOException e) {
      throw new SoundTouchApiException(e);
    }
  }
}
