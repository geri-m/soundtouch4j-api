package org.soundtouch4j;

import java.net.MalformedURLException;
import java.net.URL;
import org.junit.Assert;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.testing.http.MockHttpTransport;
import com.google.api.client.testing.http.MockLowLevelHttpRequest;
import com.google.api.client.testing.http.MockLowLevelHttpResponse;
import com.google.api.client.xml.Xml;

public class Const {

  public static final String URL = "http://soundtouch-20.fritz.box:8090";

  public static URL getUrl() {
    try {
      return new URL(Const.URL);
    } catch (final MalformedURLException e) {
      Assert.fail();
      return null;
    }
  }

  public static HttpTransport getBrokenResponse() {
    return new MockHttpTransport() {
      @Override
      public LowLevelHttpRequest buildRequest(final String method, final String url) {
        return new MockLowLevelHttpRequest() {
          @Override
          public LowLevelHttpResponse execute() {
            final MockLowLevelHttpResponse result = new MockLowLevelHttpResponse();
            result.setContentType(Xml.MEDIA_TYPE);
            result.setContent("BROKEN RESPONSE");
            return result;
          }
        };
      }
    };
  }


  public static HttpTransport getIncorrectStatusResponse(final String parameter) {
    return new MockHttpTransport() {
      @Override
      public LowLevelHttpRequest buildRequest(final String method, final String url) {
        return new MockLowLevelHttpRequest() {
          @Override
          public LowLevelHttpResponse execute() {
            final MockLowLevelHttpResponse result = new MockLowLevelHttpResponse();
            result.setContentType(Xml.MEDIA_TYPE);
            result.setContent(String.format("<?xml version=\"1.0\" encoding=\"UTF-8\" ?><status>/%s</status>", parameter));
            return result;
          }
        };
      }
    };
  }

}
