package org.soundtouch4j.info;

import org.soundtouch4j.SoundTouchApi;
import org.soundtouch4j.common.AbstractApi;

public class InfoApi extends AbstractApi {

  private static final String PATH_FOR_API = "info";

  public InfoApi(final SoundTouchApi soundTouchApi) {
    super(soundTouchApi);
  }


  /**
   * Method to turn on/off the Sound Touch
   *
   * @return InfoResponse Element that was received from the speaker
   */

  public InfoResponse getInfo() {
    return soundTouchApi.getSoundTouchApiClient()
        .get(PATH_FOR_API, InfoResponse.class);
  }
}

