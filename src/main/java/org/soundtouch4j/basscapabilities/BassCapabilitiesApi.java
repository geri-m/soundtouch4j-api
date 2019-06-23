package org.soundtouch4j.basscapabilities;

import org.soundtouch4j.SoundTouchApi;
import org.soundtouch4j.common.AbstractApi;


public class BassCapabilitiesApi extends AbstractApi {

  private static final String PATH_FOR_API = "bassCapabilities";

  public BassCapabilitiesApi(final SoundTouchApi soundTouchApi) {
    super(soundTouchApi);
  }

  /**
   * Method to set the name of the Sound Touch Device
   *
   * @return BaseCapabilitiesResponse Response from the Speaker with the org.soundtouch4j.bass information.
   */

  public BaseCapabilitiesResponse getBassCapabilities() {
    return soundTouchApi.getSoundTouchApiClient()
        .get(PATH_FOR_API, BaseCapabilitiesResponse.class);

  }
}