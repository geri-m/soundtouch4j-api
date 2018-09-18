package bass;

import org.soundtouch4j.SoundTouchApi;
import org.soundtouch4j.SoundTouchApiException;
import org.soundtouch4j.common.AbstractApi;
import org.soundtouch4j.info.InfoResponse;
import name.Name;

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

  public InfoResponse setName(final String name) throws SoundTouchApiException {
    return soundTouchApi.getSoundTouchApiClient()
        .post(PATH_FOR_API, Name.ELEMENT_NAME, new Name(name), InfoResponse.class);
  }
}