package org.soundtouch4j.group;

import org.soundtouch4j.SoundTouchApi;
import org.soundtouch4j.SoundTouchApiException;
import org.soundtouch4j.common.AbstractApi;

public class GroupApi extends AbstractApi {

  private static final String PATH_FOR_API = "getGroup";

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
    return soundTouchApi.getSoundTouchApiClient()
        .get(PATH_FOR_API, Group.class);
  }
}
