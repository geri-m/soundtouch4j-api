package org.soundtouch4j.preset;

import org.soundtouch4j.SoundTouchApi;
import org.soundtouch4j.SoundTouchApiException;
import org.soundtouch4j.common.AbstractApi;

public class PresetApi extends AbstractApi {

  private static final String PATH_FOR_API = "presets";

  public PresetApi(final SoundTouchApi soundTouchApi) {
    super(soundTouchApi);
  }


  /**
   * Method to get the Presets of the  the Sound Touch
   *
   * @return {@link PresetResponse} from the Speaker when posting a Select Command
   * @throws SoundTouchApiException is thrown in case the communication to the speaker failed or the Speaker did response
   */

  public PresetResponse getPresets() throws SoundTouchApiException {
    return soundTouchApi.getSoundTouchApiClient()
        .get(PATH_FOR_API, PresetResponse.class);
  }
}
