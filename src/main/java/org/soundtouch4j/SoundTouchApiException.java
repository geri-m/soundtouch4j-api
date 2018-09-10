package org.soundtouch4j;

public class SoundTouchApiException extends Exception {

  /**
   * Create a SoundTouchApiException instance with the specified message.
   *
   * @param message the message for the exception
   */

  public SoundTouchApiException(final String message) {
    super(message);
  }

  /**
   * Create a SoundTouchApiException instance based on the exception.
   *
   * @param e the Exception to wrap
   */

  public SoundTouchApiException(final Exception e) {
    super(e);
  }


}
