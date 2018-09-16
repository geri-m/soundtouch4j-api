package org.soundtouch4j;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.Charset;
import org.soundtouch4j.common.Request;
import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.xml.XmlHttpContent;
import com.google.api.client.xml.XmlNamespaceDictionary;
import com.google.api.client.xml.XmlObjectParser;

public class SoundTouchApiClient {

  private static final String CHAR_SET_UTF8 = "UTF-8";
  private static final String TEXT_XML_CONTENT_TYPE = "text/xml";
  private static final String EMPTY_STRING = "";
  // We don't have a namespace for our Application and/or the objects. Bose's SoundTouch doesn't like it.
  static final XmlNamespaceDictionary DICTIONARY = new XmlNamespaceDictionary().set(EMPTY_STRING, EMPTY_STRING);
  private static final String XMLNS_STRING_TO_REPLACE = "xmlns=\"\"";
  private static final String URL_PATH_SEPARATOR = "/";
  private final HttpRequestFactory factory;

  private final URL basePath;

  /**
   * The SoundTouchApiClient does the actually HTTP Calls and the XML parsing of the responses.
   *
   * @param basePath  The IP or Hostname of your Bose SoundTouch Speaker
   * @param transport The {@link HttpTransport} implementation of the platform used to do the HTTP Calls
   */

  public SoundTouchApiClient(final URL basePath, final HttpTransport transport) {
    this.basePath = basePath;
    this.factory = transport.createRequestFactory();
  }

  /**
   * This method does the HTTP POST calls to the SoundTouch
   *
   * @param path           the Path of the URL which the call is using.
   * @param xmlElementName name of the root Element of the {@link Request} you are posting.
   * @param request        The Implementation of the Request Object that you want to Post
   * @param dataclass      The Object that the XML parser should map the XML to
   * @param <T>            The Class type of the Object for the Template. Must be of type {@link Request}
   * @return Returns an implementation of the Template if the Call was successful
   * @throws SoundTouchApiException An error is thrown if the calls was not successful (either communication not possible or 4xx, 5xx status Codes)
   */

  public <T> T post(final String path, final String xmlElementName, final Request request, final Class<T> dataclass) throws SoundTouchApiException {
    final GenericUrl url = new GenericUrl(basePath.toString() + URL_PATH_SEPARATOR + path);
    final XmlHttpContent xmlContentForPostCall = new XmlHttpContent(DICTIONARY, xmlElementName, request);

    // This a hack, as XPP on J2SE create a xmlns="" in the Root Element, which it does not on Android. So there must be some underlying issue that is different.
    // see also https://stackoverflow.com/questions/52264672/different-serializing-of-xml-on-android-and-j2se-using-google-http-lib-and-xpp
    final OutputStream os = new ByteArrayOutputStream();
    try {
      xmlContentForPostCall.writeTo(os);
    } catch (final IOException e) {
      throw new SoundTouchApiException(e);
    }

    // Parsing the XML to the Content for the Request
    final ByteArrayContent content = ByteArrayContent.fromString(TEXT_XML_CONTENT_TYPE, os.toString()
        .replace(XMLNS_STRING_TO_REPLACE, EMPTY_STRING));

    try {
      // Do the Actual Request
      // if there is an exception, the HTTP Client is going to create a {@link HttpResponseException}
      final HttpResponse response = factory.buildPostRequest(url, content)
          .setParser(new XmlObjectParser(DICTIONARY))
          .execute();

      response.getMediaType()
          .setCharsetParameter(Charset.forName(CHAR_SET_UTF8));


      // Parse the XML and return the PoJo
      return response.parseAs(dataclass);

    } catch (final HttpResponseException e) {
      throw new SoundTouchApiException(e);
    } catch (final IOException e) {
      throw new SoundTouchApiException(e);
    }
  }

  /**
   * This method does the HTTP GET calls to the SoundTouch
   *
   * @param path      the Path of the URL which the call is using
   * @param dataclass The Object that the XML parser should map the XML to
   * @param <T>       The Class type of the Object for the Template. Must be of type {@link Request}
   * @return Returns an implementation of the Template if the Call was successful
   * @throws SoundTouchApiException An error is thrown if the calls was not successful (either communication not possible or 4xx, 5xx status Codes)
   */


  public <T> T get(final String path, final Class<T> dataclass) throws SoundTouchApiException {
    final GenericUrl url = new GenericUrl(basePath.toString() + URL_PATH_SEPARATOR + path);
    try {
      // the {@link HttpResponseException} is thrown is response.isSuccessStatusCode() is false.
      final HttpResponse response = factory.buildGetRequest(url)
          .setParser(new XmlObjectParser(DICTIONARY))
          .execute();

      response.getMediaType()
          .setCharsetParameter(Charset.forName(CHAR_SET_UTF8));

      // Parse the XML and return the PoJo
      return response.parseAs(dataclass);

    } catch (final HttpResponseException e) {
      throw new SoundTouchApiException(e);
    } catch (final IOException e) {
      throw new SoundTouchApiException(e);
    }
  }
}