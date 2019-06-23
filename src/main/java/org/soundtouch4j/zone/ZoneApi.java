package org.soundtouch4j.zone;

import org.soundtouch4j.SoundTouchApi;
import org.soundtouch4j.SoundTouchApiException;
import org.soundtouch4j.common.AbstractApi;

public class ZoneApi extends AbstractApi {

  private static final String PATH_FOR_API_GET = "getZone";
  private static final String PATH_FOR_API_SET = "setZone";
  private static final String PATH_FOR_API_ADD = "addZoneSlave";
  private static final String PATH_FOR_API_RM = "removeZoneSlave";


  public ZoneApi(final SoundTouchApi soundTouchApi) {
    super(soundTouchApi);
  }

  /**
   * Method to get the Zone Settings of the Speaker
   *
   * @return Zone containing information the Zone Information
   */

  public Zone getZone() {
    return soundTouchApi.getSoundTouchApiClient()
        .get(PATH_FOR_API_GET, Zone.class);

  }

  /**
   * Method to set the Zone Information of a speaker
   *
   * @param zone Zone definition for the speaker
   */

  public void setZone(final Zone zone) {

    final ZoneSetResponse response = soundTouchApi.getSoundTouchApiClient()
        .post(PATH_FOR_API_SET, Zone.ELEMENT_NAME, zone, ZoneSetResponse.class);

    if (!("/" + PATH_FOR_API_SET).equals(response.getResponse())) {
      throw new SoundTouchApiException(String.format(ERROR_MSG_FROM_SPEAKER, response.getResponse()));
    }
  }

  /**
   * This command must be sent to the master product of an existing zone. Create zones using the {@link #setZone}
   *
   * @param zone To add a product to a zone, you must build a zone object.
   */
  public void addZoneSlave(final Zone zone) {
    final ZoneSetResponse response = soundTouchApi.getSoundTouchApiClient()
        .post(PATH_FOR_API_ADD, Zone.ELEMENT_NAME, zone, ZoneSetResponse.class);

    if (!("/" + PATH_FOR_API_ADD).equals(response.getResponse())) {
      throw new SoundTouchApiException(String.format(ERROR_MSG_FROM_SPEAKER, response.getResponse()));
    }
  }

  /**
   * This command must be sent to the master product of an existing zone. Create zones using the {@link #setZone}
   *
   * @param zone To add a product to a zone, you must build a zone object.
   */

  public void removeZoneSlave(final Zone zone) {
    final ZoneSetResponse response = soundTouchApi.getSoundTouchApiClient()
        .post(PATH_FOR_API_RM, Zone.ELEMENT_NAME, zone, ZoneSetResponse.class);

    if (!("/" + PATH_FOR_API_RM).equals(response.getResponse())) {
      throw new SoundTouchApiException(String.format(ERROR_MSG_FROM_SPEAKER, response.getResponse()));
    }
  }


}
