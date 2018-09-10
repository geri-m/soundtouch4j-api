package org.soundtouch4j.key;

import java.io.IOException;
import org.soundtouch4j.AbstractApi;
import org.soundtouch4j.SoundTouchApi;
import org.soundtouch4j.SoundTouchApiException;

public class KeyApi extends AbstractApi {

  private static final String PATH_FOR_API = "key";

  public KeyApi(final SoundTouchApi soundTouchApi) {
    super(soundTouchApi);
  }


  public void pressPowerButton() throws SoundTouchApiException {
    // TODO: Define how we want to see the Business Logic
    try {
      soundTouchApi.getSoundTouchApiClient()
          .post(PATH_FOR_API, KeyRequest.ELEMENT_NAME, new KeyRequest(KeyPressAndReleaseEnum.POWER.name(), KeyStateEnum.PRESS.getValue()), KeyResponse.class);
      soundTouchApi.getSoundTouchApiClient()
          .post(PATH_FOR_API, KeyRequest.ELEMENT_NAME, new KeyRequest(KeyPressAndReleaseEnum.POWER.name(), KeyStateEnum.RELEASE.getValue()), KeyResponse.class);
    } catch (final IOException e) {
      throw new SoundTouchApiException(e);
    }
  }
}
