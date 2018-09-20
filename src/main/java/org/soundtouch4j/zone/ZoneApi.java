package org.soundtouch4j.zone;

import org.soundtouch4j.SoundTouchApi;
import org.soundtouch4j.SoundTouchApiException;
import org.soundtouch4j.common.AbstractApi;
import org.soundtouch4j.volume.VolumeSetRequest;

public class ZoneApi extends AbstractApi {

  private static final String PATH_FOR_API = "zone";

  public ZoneApi(final SoundTouchApi soundTouchApi) {
    super(soundTouchApi);
  }

  /**
   * Method to get the Volume/Mute Setting of the Speaker
   *
   * @return VolumeGetResponse containing information on the speaker volume setting.
   * @throws SoundTouchApiException is thrown in case the communication to the speaker failed or the Speaker did response
   */

  public Zone getZone() throws SoundTouchApiException {
    return soundTouchApi.getSoundTouchApiClient()
        .get(PATH_FOR_API, Zone.class);
  }

  /**
   * Method to set the Volume of a Bose Speaker to a dedicated Volume
   *
   * @param zone The Volume can be set from 0 and 100, inclusive.
   * @throws SoundTouchApiException is thrown in case the communication to the speaker failed or the Speaker did response
   */

  public void setZone(final Zone zone) throws SoundTouchApiException {

    soundTouchApi.getSoundTouchApiClient()
        .post(PATH_FOR_API, VolumeSetRequest.ELEMENT_NAME, zone, ZoneSetResponse.class);
  }
}
