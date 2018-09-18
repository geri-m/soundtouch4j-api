package org.soundtouch4j;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.testing.http.HttpTesting;
import com.google.api.client.testing.http.MockHttpTransport;
import com.google.api.client.testing.http.MockLowLevelHttpRequest;
import com.google.api.client.testing.http.MockLowLevelHttpResponse;
import com.google.api.client.xml.Xml;
import junit.framework.TestCase;


public class KeyApiTest extends TestCase {

  private static final Logger LOGGER = LoggerFactory.getLogger(KeyApiTest.class);


  private final HttpTransport transport = new MockHttpTransport() {
    @Override
    public LowLevelHttpRequest buildRequest(final String method, final String url) {
      return new MockLowLevelHttpRequest() {
        @Override
        public LowLevelHttpResponse execute() {
          final MockLowLevelHttpResponse result = new MockLowLevelHttpResponse();
          result.setContentType(Xml.MEDIA_TYPE);
          result.setContent("<?xml version=\"1.0\" encoding=\"UTF-8\" ?><status>/key</status>");
          return result;
        }
      };
    }
  };

  public void test01_power() {
    LOGGER.info("test01_power started");
    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    try {
      soundTouchApi.getKeyApi()
          .power();
    } catch (final SoundTouchApiException e) {
      LOGGER.error("Unable to get the basic information: {}", e.getMessage());
      Assert.fail();
    }
    LOGGER.info("test01_power done");
  }

  public void test02_mute() {
    LOGGER.info("test02_mute started");
    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    try {
      soundTouchApi.getKeyApi()
          .mute();
    } catch (final SoundTouchApiException e) {
      LOGGER.error("Unable to get the basic information: {}", e.getMessage());
      Assert.fail();
    }
    LOGGER.info("test02_mute done");
  }

}
