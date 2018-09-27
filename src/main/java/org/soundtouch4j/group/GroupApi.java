package org.soundtouch4j.group;

import org.soundtouch4j.SoundTouchApi;
import org.soundtouch4j.SoundTouchApiException;
import org.soundtouch4j.common.AbstractApi;
import org.soundtouch4j.info.InfoResponse;

public class GroupApi extends AbstractApi {

  private static final String PATH_FOR_API = "getGroup";
  private static final String PATH_FOR_INFO = "info";
  private static final String ST10 = "SoundTouch 10";
  private static final int MIN_VERSION_FOR_GROUP = 14;

  public GroupApi(final SoundTouchApi soundTouchApi) {
    super(soundTouchApi);
  }

  /**
   * This method returns an object containing the Stereo Pair Group configuration of the current product and the other member of the group. Currently, only the SoundTouch 10
   * supports stereo pair groups.
   *
   * @return Group Response from the Speaker with the Group information.
   * @throws SoundTouchApiException is thrown in case the communication to the speaker failed or the Speaker did response
   */

  public Group getGroup() throws SoundTouchApiException {

    final InfoResponse infoResponse = soundTouchApi.getSoundTouchApiClient()
        .get(PATH_FOR_INFO, InfoResponse.class);

    if (infoResponse.getComponents()
        .isEmpty()) {
      throw new SoundTouchApiException("Unable to fetch Version of SoundTouch");
    }

    if (infoResponse.getComponents()
        .get(0)
        .getVersion() <= MIN_VERSION_FOR_GROUP) {
      throw new SoundTouchApiException(String.format("Major Version of Firmware is '%s', but needs to be > 14", infoResponse.getComponents()
          .get(0)
          .getVersion()));
    }

    if (!infoResponse.getType()
        .equals(ST10)) {
      throw new SoundTouchApiException(String.format("/group Call Requires SoundTouch 10, but this is '%s'", infoResponse.getType()));
    }

    return soundTouchApi.getSoundTouchApiClient()
        .get(PATH_FOR_API, Group.class);
  }
}
