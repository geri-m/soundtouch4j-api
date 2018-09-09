package org.soundtouch4j.key;

import java.io.IOException;
import org.soundtouch4j.AbstractApi;
import org.soundtouch4j.SoundTouchApi;

public class KeyApi extends AbstractApi {

  private static final String PATH_FOR_API = "key";

  public KeyApi(final SoundTouchApi soundTouchApi) {
    super(soundTouchApi);
  }


  public void pressPowerButton() throws IOException {
    // TODO: Define how we want to see the Business Logic
    soundTouchApi.getSoundTouchApiClient()
        .post(PATH_FOR_API, KeyRequest.ELEMENT_NAME, new KeyRequest(KeyPressAndReleaseEnum.POWER.name(), KeyStateEnum.PRESS.getValue()), KeyResponse.class);
    soundTouchApi.getSoundTouchApiClient()
        .post(PATH_FOR_API, KeyRequest.ELEMENT_NAME, new KeyRequest(KeyPressAndReleaseEnum.POWER.name(), KeyStateEnum.RELEASE.getValue()), KeyResponse.class);
  }


}
