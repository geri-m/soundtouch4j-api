package org.soundtouch4j;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.Charset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.xml.XmlHttpContent;
import com.google.api.client.xml.XmlNamespaceDictionary;
import com.google.api.client.xml.XmlObjectParser;

public class SoundTouchApiClient {


  private static final String CHAR_SET_UTF8 = "UTF-8";
  private static final String TEXT_XML_CONTENT_TYPE = "text/xml";
  private static final String EMPTY_STRING = "";
  private static final String XMSNS_STRING_TO_REPLACE = "xmlns=\"\"";
  // We don't have a namespace for our Application and/or the objects. Soundtouch doesn't like it.
  private static final XmlNamespaceDictionary DICTIONARY = new XmlNamespaceDictionary().set(EMPTY_STRING, EMPTY_STRING);
  private static final Logger LOGGER = LoggerFactory.getLogger(SoundTouchApiClient.class);

  private final HttpRequestFactory factory;

  private final URL basePath;

  public SoundTouchApiClient(final URL basePath, final HttpTransport transport) {
    // TODO: Make Sure we have "/" in the end of the URL.
    this.basePath = basePath;
    this.factory = transport.createRequestFactory();
  }

  public <T> T post(final String path, final String xmlElementName, final Request request, final Class<T> dataclass) throws IOException {
    final GenericUrl url = new GenericUrl(basePath.toString() + "/" + path);
    final XmlHttpContent xmlContentForPostCall = new XmlHttpContent(DICTIONARY, xmlElementName, request);

    // This a hack, as XPP on J2SE create a xmlns="" in the Root Element, which it does not on Android. So there must be some underlying issue that is different.
    // see also https://stackoverflow.com/questions/52264672/different-serializing-of-xml-on-android-and-j2se-using-google-http-lib-and-xpp
    final OutputStream os = new ByteArrayOutputStream();
    xmlContentForPostCall.writeTo(os);
    final ByteArrayContent content = ByteArrayContent.fromString(TEXT_XML_CONTENT_TYPE, os.toString()
        .replace(XMSNS_STRING_TO_REPLACE, EMPTY_STRING));

    final HttpResponse response = factory.buildPostRequest(url, content)
        .setParser(new XmlObjectParser(DICTIONARY))
        .execute();
    response.getMediaType()
        .setCharsetParameter(Charset.forName(CHAR_SET_UTF8));
    return response.parseAs(dataclass);

  }

  public <T> T get(final String path, final Class<T> dataclass) throws IOException {
    final GenericUrl url = new GenericUrl(basePath.toString() + "/" + path);
    final HttpResponse response = factory.buildGetRequest(url)
        .setParser(new XmlObjectParser(DICTIONARY))
        .execute();
    response.getMediaType()
        .setCharsetParameter(Charset.forName(CHAR_SET_UTF8));
    return response.parseAs(dataclass);
  }
}