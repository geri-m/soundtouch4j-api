package org.soundtouch4j.source;

import java.io.IOException;
import org.soundtouch4j.AbstractApi;
import org.soundtouch4j.SoundTouchApi;
import org.soundtouch4j.SoundTouchApiException;

public class SourceApi extends AbstractApi {

  private static final String PATH_FOR_API = "source";

  public SourceApi(final SoundTouchApi soundTouchApi) {
    super(soundTouchApi);
  }


  /**
   * Method to turn on/off the Sound Touch
   *
   * @throws SoundTouchApiException is thrown in case the communication to the speaker failed.
   */

  public SourceResponse getSources() throws SoundTouchApiException {
    try {
      return soundTouchApi.getSoundTouchApiClient()
          .get(PATH_FOR_API, SourceResponse.class);
    } catch (final IOException e) {
      throw new SoundTouchApiException(e);
    }
  }
}
