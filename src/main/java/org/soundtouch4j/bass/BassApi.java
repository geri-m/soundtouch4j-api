package org.soundtouch4j.bass;

import org.soundtouch4j.SoundTouchApi;
import org.soundtouch4j.SoundTouchApiException;
import org.soundtouch4j.common.AbstractApi;

public class BassApi extends AbstractApi {

  private static final String PATH_FOR_API = "bass";

  public BassApi(final SoundTouchApi soundTouchApi) {
    super(soundTouchApi);
  }

  /**
   * Method to set the name of the Sound Touch Device
   *
   * @return InfoResponse Response from the Speaker with the full information.
   * @throws SoundTouchApiException is thrown in case the communication to the speaker failed or the Speaker did response
   */

  public BassGetResponse getBass() throws SoundTouchApiException {
    return soundTouchApi.getSoundTouchApiClient()
        .get(PATH_FOR_API, BassGetResponse.class);
  }


  public void setBass(final int base) throws SoundTouchApiException {
    soundTouchApi.getSoundTouchApiClient()
        .post(PATH_FOR_API, BassRequest.ELEMENT_NAME, new BassRequest(base), BassSetResponse.class);
  }
}