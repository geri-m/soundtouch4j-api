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
   */

  public void power() throws SoundTouchApiException {
    KeyResponse response = soundTouchApi.getSoundTouchApiClient()
        .post(PATH_FOR_API, KeyRequest.ELEMENT_NAME, new KeyRequest(KeyPressValueEnum.POWER, KeyStateEnum.PRESS), KeyResponse.class);

    if (!("/" + PATH_FOR_API).equals(response.getValue())) {
      throw new SoundTouchApiException(String.format(ERROR_MSG_FROM_SPEAKER, response.getValue()));
    }

    response = soundTouchApi.getSoundTouchApiClient()
        .post(PATH_FOR_API, KeyRequest.ELEMENT_NAME, new KeyRequest(KeyPressValueEnum.POWER, KeyStateEnum.RELEASE), KeyResponse.class);

    if (!("/" + PATH_FOR_API).equals(response.getValue())) {
      throw new SoundTouchApiException(String.format(ERROR_MSG_FROM_SPEAKER, response.getValue()));
    }
  }

  /**
   * Method to mute or un-mute the speaker. Use the {@link org.soundtouch4j.volume.VolumeApi} to get the current state.
   *
   * @throws SoundTouchApiException is thrown in case the communication to the speaker failed.
   */

  public void mute() throws SoundTouchApiException {
    final KeyResponse response = soundTouchApi.getSoundTouchApiClient()
        .post(PATH_FOR_API, KeyRequest.ELEMENT_NAME, new KeyRequest(KeyPressValueEnum.MUTE, KeyStateEnum.PRESS), KeyResponse.class);

    if (!("/" + PATH_FOR_API).equals(response.getValue())) {
      throw new SoundTouchApiException(String.format(ERROR_MSG_FROM_SPEAKER, response.getValue()));
    }
  }


}
