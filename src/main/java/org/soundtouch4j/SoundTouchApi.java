package org.soundtouch4j;

import java.net.URL;
import org.soundtouch4j.key.KeyApi;

public class SoundTouchApi {

  // Single One Instance of the API that does the call to the Box. We want to have this single object to avoid parallel calls.
  private final SoundTouchApiClient soundTouchApiClient;
  private KeyApi keyApi;

  // TODO: We require the option to choose ANDROID or J2SE
  public SoundTouchApi(final URL endpoint) {
    soundTouchApiClient = new SoundTouchApiClient(endpoint);
  }

  /**
   * Gets the KeyApi instance owned by this SoundTouchApi instance. The KeyApi is used to perform all key press/release/event related API calls.
   *
   * @return the KeyApi instance owned by this SoundTouchApi instance
   */
  public KeyApi getKeyApi() {

    if (keyApi == null) {
      synchronized (this) {
        if (keyApi == null) {
          keyApi = new KeyApi(this);
        }
      }
    }

    return (keyApi);
  }


  public SoundTouchApiClient getSoundTouchApiClient() {
    return soundTouchApiClient;
  }


}
