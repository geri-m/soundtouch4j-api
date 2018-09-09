package org.soundtouch4j;

import java.io.IOException;
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

  private final String basePath;

  public SoundTouchApiClient(final String basePath) {
    // TODO: Make Sure we have "/" in the end of the URL.
    this.basePath = basePath;
  }


  public <T> T post(final String path, final String xmlElementName, final AbstractRequest request, final Class<T> dataclass) throws IOException {
    final GenericUrl url = new GenericUrl(basePath + path);
    final XmlHttpContent xmlContentForPostCall = new XmlHttpContent(DICTIONARY, xmlElementName, request);
    return factory.buildPostRequest(url, xmlContentForPostCall)
        .setParser(new XmlObjectParser(DICTIONARY))
        .execute()
        .parseAs(dataclass);
  }


}
