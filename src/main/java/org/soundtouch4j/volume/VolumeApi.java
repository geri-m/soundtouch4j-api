package org.soundtouch4j.volume;

import org.soundtouch4j.SoundTouchApi;
import org.soundtouch4j.SoundTouchApiException;
import org.soundtouch4j.common.AbstractApi;

public class VolumeApi extends AbstractApi {

  private static final String PATH_FOR_API = "volume";
  private static final String ERROR_MSG_VOLUME_RANGE = "The Volume must be a value from 0 to 100, inclusive. '%s' is out of this range.";
  private static final int MIN_VOLUME = 0;
  private static final int MAX_VOLUME = 100;

  public VolumeApi(final SoundTouchApi soundTouchApi) {
    super(soundTouchApi);
  }

  /**
   * Method to get the Volume/Mute Setting of the Speaker
   *
   * @return VolumeGetResponse containing information on the speaker volume setting.
   */

  public VolumeGetResponse getVolume() {
    return soundTouchApi.getSoundTouchApiClient()
        .get(PATH_FOR_API, VolumeGetResponse.class);
  }

  /**
   * Method to set the Volume of a Bose Speaker to a dedicated Volume
   *
   * @param volume The Volume can be set from 0 and 100, inclusive.
   */

  public void setVolume(final int volume) {

    if ((volume < MIN_VOLUME) || (volume > MAX_VOLUME)) {
      throw new SoundTouchApiException(String.format(ERROR_MSG_VOLUME_RANGE, volume));
    }

    final VolumeSetResponse response = soundTouchApi.getSoundTouchApiClient()
        .post(PATH_FOR_API, VolumeSetRequest.ELEMENT_NAME, new VolumeSetRequest(volume), VolumeSetResponse.class);

    if (!("/" + PATH_FOR_API).equals(response.getVolume())) {
      throw new SoundTouchApiException(String.format(ERROR_MSG_FROM_SPEAKER, response.getVolume()));
    }
  }
}
