package org.soundtouch4j;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.http.xml.XmlHttpContent;
import com.google.api.client.xml.XmlNamespaceDictionary;
import com.google.api.client.xml.XmlObjectParser;

public class SoundTouchApiClient {

  // We don't have a namespace for our Application and/or the objects. Soundtouch doesn't like it.
  private static final XmlNamespaceDictionary DICTIONARY = new XmlNamespaceDictionary().set("", "");

  private final HttpRequestFactory factory = new NetHttpTransport().createRequestFactory();

  private final URL basePath;

  public SoundTouchApiClient(final URL basePath) {
    // TODO: Make Sure we have "/" in the end of the URL.
    this.basePath = basePath;
  }

  public <T> T post(final String path, final String xmlElementName, final Request request, final Class<T> dataclass) throws IOException {
    final GenericUrl url = new GenericUrl(basePath.toString() + "/" + path);
    final XmlHttpContent xmlContentForPostCall = new XmlHttpContent(DICTIONARY, xmlElementName, request);

    // This a hack, as XPP on J2SE create a xmlns="" in the Root Element, which it does not on Android. So there must be some underlying issue that is different.
    // see also https://stackoverflow.com/questions/52264672/different-serializing-of-xml-on-android-and-j2se-using-google-http-lib-and-xpp
    final OutputStream os = new ByteArrayOutputStream();
    xmlContentForPostCall.writeTo(os);
    final ByteArrayContent content = ByteArrayContent.fromString("text/xml", os.toString()
        .replace("xmlns=\"\"", ""));

    return factory.buildPostRequest(url, content)
        .setParser(new XmlObjectParser(DICTIONARY))
        .execute()
        .parseAs(dataclass);
  }

  public <T> T get(final String path, final Class<T> dataclass) throws IOException {
    final GenericUrl url = new GenericUrl(basePath.toString() + "/" + path);
    return factory.buildGetRequest(url)
        .setParser(new XmlObjectParser(DICTIONARY))
        .execute()
        .parseAs(dataclass);
  }
}