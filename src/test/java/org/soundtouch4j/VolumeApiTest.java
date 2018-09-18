package org.soundtouch4j;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.soundtouch4j.volume.VolumeGetResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.testing.http.HttpTesting;
import com.google.api.client.testing.http.MockHttpTransport;
import com.google.api.client.testing.http.MockLowLevelHttpRequest;
import com.google.api.client.testing.http.MockLowLevelHttpResponse;
import com.google.api.client.xml.Xml;
import junit.framework.TestCase;

public class VolumeApiTest extends TestCase {

  private static final Logger LOGGER = LoggerFactory.getLogger(VolumeApiTest.class);

  public void test01_getVolume() {
    LOGGER.info("test01_getVolume started");
    final HttpTransport transport = new MockHttpTransport() {
      @Override
      public LowLevelHttpRequest buildRequest(final String method, final String url) {
        return new MockLowLevelHttpRequest() {
          @Override
          public LowLevelHttpResponse execute() {
            final MockLowLevelHttpResponse result = new MockLowLevelHttpResponse();
            result.setContentType(Xml.MEDIA_TYPE);
            result.setContent("<?xml version=\"1.0\" encoding=\"UTF-8\" ?><volume deviceID=\"C8DF84AE0B6E\"><targetvolume>18</targetvolume><actualvolume>28</actualvolume" +
                "><muteenabled>false</muteenabled></volume>");
            return result;
          }
        };
      }
    };

    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    try {
      final VolumeGetResponse response = soundTouchApi.getVolumeApi()
          .getVolume();
      assertEquals(response.getDeviceID(), "C8DF84AE0B6E");
      assertEquals(response.getActualVolume(), 28);
      assertEquals(response.getTargetVolume(), 18);
      assertFalse(response.isMuteEnabled());

    } catch (final SoundTouchApiException e) {
      LOGGER.error("Unable to get the basic information: {}", e.getMessage());
      Assert.fail();
    }
    LOGGER.info("test01_getVolume started");
  }


  public void test02_setVolume() {
    LOGGER.info("test02_setVolume started");
    final HttpTransport transport = new MockHttpTransport() {
      @Override
      public LowLevelHttpRequest buildRequest(final String method, final String url) {
        return new MockLowLevelHttpRequest() {
          @Override
          public LowLevelHttpResponse execute() {
            final MockLowLevelHttpResponse result = new MockLowLevelHttpResponse();
            result.setContentType(Xml.MEDIA_TYPE);
            result.setContent("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" + "<status>/volume</status>");
            return result;
          }
        };
      }
    };

    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    try {
      soundTouchApi.getVolumeApi()
          .setVolume(100);


      soundTouchApi.getVolumeApi()
          .setVolume(0);

    } catch (final SoundTouchApiException e) {
      LOGGER.error("Unable to get the basic information: {}", e.getMessage());
      Assert.fail();
    }
    LOGGER.info("test02_setVolume started");
  }


  public void test03_setVolumeLessThanZero() {
    LOGGER.info("test03_setVolumeLessThanZero started");
    final HttpTransport transport = new MockHttpTransport() {
      @Override
      public LowLevelHttpRequest buildRequest(final String method, final String url) {
        return new MockLowLevelHttpRequest() {
          @Override
          public LowLevelHttpResponse execute() {
            return new MockLowLevelHttpResponse();
          }
        };
      }
    };

    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    try {
      soundTouchApi.getVolumeApi()
          .setVolume(-1);
      Assert.fail();
    } catch (final SoundTouchApiException e) {
      LOGGER.error("Unable to get the basic information: {}", e.getMessage());
      assertEquals("The Volume must be a value from 0 to 100, inclusive. '-1' is out of this range.", e.getMessage());
    }
    LOGGER.info("test03_setVolumeLessThanZero started");
  }

  public void test05_setVolumeMoreThan100() {
    LOGGER.info("test05_setVolumeMoreThan100 started");
    final HttpTransport transport = new MockHttpTransport() {
      @Override
      public LowLevelHttpRequest buildRequest(final String method, final String url) {
        return new MockLowLevelHttpRequest() {
          @Override
          public LowLevelHttpResponse execute() {
            return new MockLowLevelHttpResponse();
          }
        };
      }
    };

    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    try {
      soundTouchApi.getVolumeApi()
          .setVolume(101);
      Assert.fail();
    } catch (final SoundTouchApiException e) {
      LOGGER.error("Unable to get the basic information: {}", e.getMessage());
      assertEquals("The Volume must be a value from 0 to 100, inclusive. '101' is out of this range.", e.getMessage());
    }
    LOGGER.info("test05_setVolumeMoreThan100 started");
  }

}

