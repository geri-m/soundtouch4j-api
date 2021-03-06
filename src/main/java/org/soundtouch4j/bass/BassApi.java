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
   * Method to get the bass of the Sound Touch Device
   *
   * @return BassGetResponse Response from the Speaker with the bass information.
   */

  public BassGetResponse getBass() {
    return soundTouchApi.getSoundTouchApiClient()
        .get(PATH_FOR_API, BassGetResponse.class);
  }

  /**
   * Method to set the bass of the Sound Touch Device
   *
   * @param bass Bass Setting for the Speaker
   */

  public void setBass(final int bass) {
    final BassSetResponse response = soundTouchApi.getSoundTouchApiClient()
        .post(PATH_FOR_API, BassRequest.ELEMENT_NAME, new BassRequest(bass), BassSetResponse.class);

    if (!("/" + PATH_FOR_API).equals(response.getValue())) {
      throw new SoundTouchApiException(String.format(ERROR_MSG_FROM_SPEAKER, response.getValue()));
    }
  }
}