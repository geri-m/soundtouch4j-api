package org.soundtouch4j;

public abstract class AbstractApi {

  protected final SoundTouchApi soundTouchApi;

  public AbstractApi(final SoundTouchApi soundTouchApi) {
    this.soundTouchApi = soundTouchApi;
  }

}
