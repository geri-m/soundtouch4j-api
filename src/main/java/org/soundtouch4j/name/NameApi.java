package org.soundtouch4j.name;

import org.soundtouch4j.SoundTouchApi;
import org.soundtouch4j.common.AbstractApi;
import org.soundtouch4j.info.InfoResponse;

public class NameApi extends AbstractApi {

  private static final String PATH_FOR_API = "name";

  public NameApi(final SoundTouchApi soundTouchApi) {
    super(soundTouchApi);
  }

  /**
   * Method to set the name of the Sound Touch Device
   *
   * @param name Name of the Speaker that should be set
   * @return InfoResponse Response from the Speaker with the full information.
   */

  public InfoResponse setName(final String name) {
    return soundTouchApi.getSoundTouchApiClient()
        .post(PATH_FOR_API, Name.ELEMENT_NAME, new Name(name), InfoResponse.class);
  }
}