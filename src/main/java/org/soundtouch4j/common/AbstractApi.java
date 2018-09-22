package org.soundtouch4j.common;

import org.soundtouch4j.SoundTouchApi;

public abstract class AbstractApi {

  protected static final String ERROR_MSG_FROM_SPEAKER = "Invalid Response from Speaker. Response was '%s'";

  protected final SoundTouchApi soundTouchApi;

  public AbstractApi(final SoundTouchApi soundTouchApi) {
    this.soundTouchApi = soundTouchApi;
  }

}
