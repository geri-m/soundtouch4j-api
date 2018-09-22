package org.soundtouch4j;


import java.io.IOException;
import java.io.StringReader;
import org.soundtouch4j.common.ErrorResponse;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.xml.XmlObjectParser;

public class SoundTouchApiException extends Exception {

  private static final long serialVersionUID = 1L;
  private ErrorResponse error;
  private int httpStatus;

  /**
   * Create a SoundTouchCommunicationException instance from the {@link HttpResponseException}
   *
   * @param exception r the exception
   */
  public SoundTouchApiException(final HttpResponseException exception) {
    super(exception);
    httpStatus = exception.getStatusCode();

    // Parse the XML from the Error
    final XmlObjectParser parser = new XmlObjectParser(SoundTouchApiClient.DICTIONARY);
    try {
      error = parser.parseAndClose(new StringReader(exception.getContent()), ErrorResponse.class);
    } catch (final IOException ignored) {
      // If we can't parse the error, we don't block the exception process and ignore it for now.
      error = null;
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
   * @param exception the Exception to wrap
   */
  public SoundTouchApiException(final Exception exception) {
    super(exception);
  }


  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public ErrorResponse getError() {
    return error;
  }


  public int getHttpStatus() {
    return httpStatus;
  }

}
