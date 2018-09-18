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
import bassCapabilties.BaseCapabilitiesResponse;
import junit.framework.TestCase;

public class BassCapabilitiesApiTest extends TestCase {

  private static final Logger LOGGER = LoggerFactory.getLogger(BassCapabilitiesApiTest.class);

  public void test01_getBassCapabilities() {
    LOGGER.info("test01_setName started");
    final HttpTransport transport = new MockHttpTransport() {
      @Override
      public LowLevelHttpRequest buildRequest(final String method, final String url) {
        return new MockLowLevelHttpRequest() {
          @Override
          public LowLevelHttpResponse execute() {
            final MockLowLevelHttpResponse result = new MockLowLevelHttpResponse();
            result.setContentType(Xml.MEDIA_TYPE);
            result.setContent("<?xml version=\"1.0\" encoding=\"UTF-8\" ?><bassCapabilities " + "deviceID=\"C8DF84AE0B6E\"><bassAvailable>true</bassAvailable><bassMin>-9" +
                "</bassMin><bassMax>0</bassMax><bassDefault>0</bassDefault></bassCapabilities>");
            return result;
          }
        };
      }
    };

    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    try {
      final BaseCapabilitiesResponse response = soundTouchApi.getBassCapabilitiesApi()
          .getBassCapabilities();
      assertEquals(response.getDeviceID(), "C8DF84AE0B6E");
      assertEquals(response.getBassMin(), -9);
      assertEquals(response.getBassMax(), 0);
      assertEquals(response.getBassDefault(), 0);

    } catch (final SoundTouchApiException e) {
      LOGGER.error("Unable to get the basic information: {}", e.getMessage());
      Assert.fail();
    }
    LOGGER.info("test01_getBassCapabilities started");
  }
}

