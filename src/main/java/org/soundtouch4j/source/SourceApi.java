package org.soundtouch4j.source;

import org.soundtouch4j.SoundTouchApi;
import org.soundtouch4j.SoundTouchApiException;
import org.soundtouch4j.common.AbstractApi;

public class SourceApi extends AbstractApi {

  private static final String PATH_FOR_API = "source";

  public SourceApi(final SoundTouchApi soundTouchApi) {
    super(soundTouchApi);
  }


  /**
   * Method to get the Sources of the  the Sound Touch
   *
   * @throws SoundTouchApiException is thrown in case the communication to the speaker failed or the Speaker did response
   */

  public SourceResponse getSources() throws SoundTouchApiException {
      return soundTouchApi.getSoundTouchApiClient()
          .get(PATH_FOR_API, SourceResponse.class);

  }
}
