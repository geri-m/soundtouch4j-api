package org.soundtouch4j;


import java.io.IOException;
import java.io.StringReader;
import org.soundtouch4j.common.Error;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.xml.XmlNamespaceDictionary;
import com.google.api.client.xml.XmlObjectParser;

public class SoundTouchApiException extends Exception {

  public static final XmlNamespaceDictionary XML_NAMESPACE_DICTIONARY = new XmlNamespaceDictionary().set("", "");
  private static final long serialVersionUID = 1L;
  private Error error;
  private int httpStatus;

  public SoundTouchApiException(final HttpResponseException response) {
    super(response.getStatusMessage());
    httpStatus = response.getStatusCode();

    final XmlObjectParser parser = new XmlObjectParser(XML_NAMESPACE_DICTIONARY);
    try {
      error = parser.parseAndClose(new StringReader(response.getContent()), Error.class);
    } catch (IOException ignored) {

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


  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public Error getError() {
    return error;
  }


  public int getHttpStatus() {
    return httpStatus;
  }

}
