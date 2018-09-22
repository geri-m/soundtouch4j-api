package org.soundtouch4j;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.soundtouch4j.bass.BassGetResponse;
import org.soundtouch4j.zone.Zone;
import org.soundtouch4j.zone.ZoneMember;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.testing.http.HttpTesting;
import com.google.api.client.testing.http.MockHttpTransport;
import com.google.api.client.testing.http.MockLowLevelHttpRequest;
import com.google.api.client.testing.http.MockLowLevelHttpResponse;
import com.google.api.client.xml.Xml;
import junit.framework.TestCase;

public class BassApiTest extends TestCase {

  private static final Logger LOGGER = LoggerFactory.getLogger(BassApiTest.class);

  public void test01_getBass() {
    LOGGER.info("test01_getVolume started");
    final HttpTransport transport = new MockHttpTransport() {
      @Override
      public LowLevelHttpRequest buildRequest(final String method, final String url) {
        return new MockLowLevelHttpRequest() {
          @Override
          public LowLevelHttpResponse execute() {
            final MockLowLevelHttpResponse result = new MockLowLevelHttpResponse();
            result.setContentType(Xml.MEDIA_TYPE);
            result.setContent("<?xml version=\"1.0\" encoding=\"UTF-8\" ?><bass deviceID=\"C8DF84AE0B6E\"><targetbass>1</targetbass><actualbass>2</actualbass></bass>");
            return result;
          }
        };
      }
    };

    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    try {
      final BassGetResponse response = soundTouchApi.getBassApi()
          .getBass();

      assertEquals("C8DF84AE0B6E", response.getDeviceID());
      assertEquals(2, response.getActualBass());
      assertEquals(1, response.getTargetBass());


    } catch (final SoundTouchApiException e) {
      LOGGER.error("Unable to get the basic information: {}", e.getMessage());
      Assert.fail();
    }
    LOGGER.info("test01_getVolume started");
  }


  public void test02_setBass() {
    LOGGER.info("test02_setVolume started");
    final HttpTransport transport = new MockHttpTransport() {
      @Override
      public LowLevelHttpRequest buildRequest(final String method, final String url) {
        return new MockLowLevelHttpRequest() {
          @Override
          public LowLevelHttpResponse execute() {
            final MockLowLevelHttpResponse result = new MockLowLevelHttpResponse();
            result.setContentType(Xml.MEDIA_TYPE);
            result.setContent("<?xml version=\"1.0\" encoding=\"UTF-8\" ?><status>/bass</status>");
            return result;
          }
        };
      }
    };

    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    try {
      soundTouchApi.getBassApi()
          .setBass(10);
    } catch (final SoundTouchApiException e) {
      LOGGER.error("Unable to get the basic information: {}", e.getMessage());
      Assert.fail();
    }
    LOGGER.info("test02_setVolume started");
  }


  public void test03_setBassBrokenResponse() {
    LOGGER.info("test03_setBassBrokenResponse started");
    final HttpTransport transport = new MockHttpTransport() {
      @Override
      public LowLevelHttpRequest buildRequest(final String method, final String url) {
        return new MockLowLevelHttpRequest() {
          @Override
          public LowLevelHttpResponse execute() {
            final MockLowLevelHttpResponse result = new MockLowLevelHttpResponse();
            result.setContentType(Xml.MEDIA_TYPE);
            result.setContent("BROKEN RESPONSE SET BASS");
            return result;
          }
        };
      }
    };

    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    try {
      final Zone zone = new Zone("C8DF84AE0B6E");
      zone.addMember(new ZoneMember("192.168.178.61", "C8DF84AE0B6E"));
      soundTouchApi.getBassApi()
          .setBass(10);
      Assert.fail();
    } catch (final SoundTouchApiException e) {
      LOGGER.error("Unable to get the basic information: {}", e.getMessage());

    }
    LOGGER.info("test03_setBassBrokenResponse completed");
  }

  public void test04_setBassIncorrectResponse() {
    LOGGER.info("test04_setBassIncorrectResponse started");
    final HttpTransport transport = new MockHttpTransport() {
      @Override
      public LowLevelHttpRequest buildRequest(final String method, final String url) {
        return new MockLowLevelHttpRequest() {
          @Override
          public LowLevelHttpResponse execute() {
            final MockLowLevelHttpResponse result = new MockLowLevelHttpResponse();
            result.setContentType(Xml.MEDIA_TYPE);
            result.setContent("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n<status>/NOTAsetBase</status>");
            return result;
          }
        };
      }
    };

    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    try {
      final Zone zone = new Zone("C8DF84AE0B6E");
      zone.addMember(new ZoneMember("192.168.178.61", "C8DF84AE0B6E"));
      soundTouchApi.getBassApi()
          .setBass(10);
      Assert.fail();
    } catch (final SoundTouchApiException e) {
      LOGGER.error("Unable to get the basic information: {}", e.getMessage());
      assertEquals("Invalid Response from Speaker. Response was '/NOTAsetBase'", e.getMessage());
    }
    LOGGER.info("test04_setBassIncorrectResponse completed");
  }

}
