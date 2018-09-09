package org.soundtouch4j;

import org.soundtouch4j.key.KeyApi;

public class SoundTouchApi {


  private final SoundTouchApiClient soundTouchApiClient;
  private KeyApi keyApi;

  // TODO: We require the option to choose ANDROID or J2SE
  public SoundTouchApi(final String endpoint) {
    soundTouchApiClient = new SoundTouchApiClient(endpoint);
  }

  /**
   * Gets the KeyApi instance owned by this SoundTouchApi instance. The KeyApi is used to perform all key press/release/event related API calls.
   *
   * @return the KeyApi instance owned by this SoundTouchApi instance
   */
  public KeyApi getUserApi() {

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
