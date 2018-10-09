package org.soundtouch4j;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
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


public class VolumeApiTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(VolumeApiTest.class);

  @Test
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
      assertEquals("C8DF84AE0B6E", response.getDeviceID());
      assertEquals(28, response.getActualVolume());
      assertEquals(18, response.getTargetVolume());
      assertEquals("VolumeGetResponse{deviceID='C8DF84AE0B6E', targetVolume=18, actualVolume=28, muteEnabled=false}", response.toString());
      assertFalse(response.isMuteEnabled());
    } catch (final SoundTouchApiException e) {
      LOGGER.error("Unable to get the basic information: {}", e.getMessage());
      fail();
    }
    LOGGER.info("test01_getVolume started");
  }

  @Test
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
      fail();
    }
    LOGGER.info("test02_setVolume started");
  }

  @Test
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
      fail();
    } catch (final SoundTouchApiException e) {
      LOGGER.error("Unable to get the basic information: {}", e.getMessage());
      assertEquals("The Volume must be a value from 0 to 100, inclusive. '-1' is out of this range.", e.getMessage());
    }
    LOGGER.info("test03_setVolumeLessThanZero started");
  }

  @Test
  public void test04_setVolumeMoreThan100() {
    LOGGER.info("test04_setVolumeMoreThan100 started");
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
      fail();
    } catch (final SoundTouchApiException e) {
      LOGGER.error("Unable to get the basic information: {}", e.getMessage());
      assertEquals("The Volume must be a value from 0 to 100, inclusive. '101' is out of this range.", e.getMessage());
    }
    LOGGER.info("test04_setVolumeMoreThan100 started");
  }

  @Test
  public void test05_setVolumeBrokenResponse() {
    LOGGER.info("test05_setVolumeBrokenResponse started");
    final HttpTransport transport = Const.getBrokenResponse();

    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    try {
      soundTouchApi.getVolumeApi()
          .setVolume(100);
      fail();
    } catch (final SoundTouchApiException e) {
      LOGGER.error("Unable to get the basic information: {}", e.getMessage());

    }
    LOGGER.info("test05_setVolumeBrokenResponse completed");
  }

  @Test
  public void test06_setVolumeIncorrectResponse() {
    LOGGER.info("test06_setVolumeIncorrectResponse started");
    final HttpTransport transport = Const.getIncorrectStatusResponse("NOTsetVolume");

    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    try {
      soundTouchApi.getVolumeApi()
          .setVolume(100);
      fail();
    } catch (final SoundTouchApiException e) {
      LOGGER.error("Unable to get the basic information: {}", e.getMessage());
      assertEquals("Invalid Response from Speaker. Response was '/NOTsetVolume'", e.getMessage());
    }
    LOGGER.info("test06_setVolumeIncorrectResponse completed");
  }

}

