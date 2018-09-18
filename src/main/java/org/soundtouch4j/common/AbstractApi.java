package org.soundtouch4j.common;

import org.soundtouch4j.SoundTouchApi;

public abstract class AbstractApi {

  protected final SoundTouchApi soundTouchApi;

  public AbstractApi(final SoundTouchApi soundTouchApi) {
    this.soundTouchApi = soundTouchApi;
  }

}
