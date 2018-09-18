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
    soundTouchApi.getSoundTouchApiClient()
        .post(PATH_FOR_API, KeyRequest.ELEMENT_NAME, new KeyRequest(KeyPressValueEnum.POWER, KeyStateEnum.PRESS), KeyResponse.class);
    soundTouchApi.getSoundTouchApiClient()
        .post(PATH_FOR_API, KeyRequest.ELEMENT_NAME, new KeyRequest(KeyPressValueEnum.POWER, KeyStateEnum.RELEASE), KeyResponse.class);
  }

  /**
   * Method to mute or un-mute the speaker. Use the {@link org.soundtouch4j.volume.VolumeApi} to get the current state.
   *
   * @throws SoundTouchApiException is thrown in case the communication to the speaker failed.
   */

  public void mute() throws SoundTouchApiException {
    soundTouchApi.getSoundTouchApiClient()
        .post(PATH_FOR_API, KeyRequest.ELEMENT_NAME, new KeyRequest(KeyPressValueEnum.MUTE, KeyStateEnum.PRESS), KeyResponse.class);
  }


}
