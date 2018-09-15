package org.soundtouch4j;


import java.io.IOException;
import org.soundtouch4j.common.Error;
import com.google.api.client.http.HttpResponse;

public class SoundTouchApiException extends Exception {

  private static final long serialVersionUID = 1L;
  private Error error;
  private int httpStatus;

  public SoundTouchApiException(final HttpResponse response) {
    httpStatus = response.getStatusCode();
    try {
      error = response.parseAs(Error.class);
    } catch (final IOException ignore) {

    }
  }

  /**
   * Create a SoundTouchCommunicationException instance with the specified message.
   *
   * @param message the message for the exception
   */

  public SoundTouchApiException(final String message) {
    super(message);
  }

  /**
   * Create a SoundTouchCommunicationException instance based on the exception.
   *
   * @param e the Exception to wrap
   */

  public SoundTouchApiException(final Exception e) {
    super(e);
  }
}
