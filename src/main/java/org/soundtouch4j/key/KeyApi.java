package org.soundtouch4j.key;

import org.soundtouch4j.SoundTouchApi;
import org.soundtouch4j.SoundTouchApiException;
import org.soundtouch4j.common.AbstractApi;

public class KeyApi extends AbstractApi {

  private static final String PATH_FOR_API = "key";

  public KeyApi(final SoundTouchApi soundTouchApi) {
    super(soundTouchApi);
  }


  /**
   * Method to turn on/off the Sound Touch
   *
   * @throws SoundTouchApiException is thrown in case the communication to the speaker failed.
   * @return KeyResponse Response from the Speaker for Posting a Key Request
   */

  public KeyResponse power() throws SoundTouchApiException {
      soundTouchApi.getSoundTouchApiClient()
          .post(PATH_FOR_API, KeyRequest.ELEMENT_NAME, new KeyRequest(KeyPressValueEnum.POWER, KeyStateEnum.PRESS), KeyResponse.class);
      return soundTouchApi.getSoundTouchApiClient()
          .post(PATH_FOR_API, KeyRequest.ELEMENT_NAME, new KeyRequest(KeyPressValueEnum.POWER, KeyStateEnum.RELEASE), KeyResponse.class);
  }
}
