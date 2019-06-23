package org.soundtouch4j;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.testing.http.HttpTesting;
import com.google.api.client.testing.http.MockHttpTransport;
import com.google.api.client.testing.http.MockLowLevelHttpRequest;
import com.google.api.client.testing.http.MockLowLevelHttpResponse;
import org.junit.jupiter.api.Test;
import org.soundtouch4j.volume.VolumeGetResponse;

import static org.junit.jupiter.api.Assertions.*;

public class VolumeApiTest {

  @Test
  public void getVolume() {
    final String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><volume deviceID=\"C8DF84AE0B6E\"><targetvolume>18</targetvolume><actualvolume>28</actualvolume" +
        "><muteenabled>false</muteenabled></volume>";
    final HttpTransport transport = Const.getHttpTransportFromString(xml);

    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    final VolumeGetResponse response = soundTouchApi.getVolumeApi()
        .getVolume();
    assertEquals("C8DF84AE0B6E", response.getDeviceID());
    assertEquals(28, response.getActualVolume());
    assertEquals(18, response.getTargetVolume());
    assertEquals("VolumeGetResponse{deviceID='C8DF84AE0B6E', targetVolume=18, actualVolume=28, muteEnabled=false}", response.toString());
    assertFalse(response.isMuteEnabled());

  }

  @Test
  public void setVolume() {
    final String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n<status>/volume</status>";
    final HttpTransport transport = Const.getHttpTransportFromString(xml);

    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    soundTouchApi.getVolumeApi()
        .setVolume(100);
    soundTouchApi.getVolumeApi()
        .setVolume(0);
  }

  @Test
  public void setVolumeLessThanZero() {
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
      assertEquals("The Volume must be a value from 0 to 100, inclusive. '-1' is out of this range.", e.getMessage());
    }
  }

  @Test
  public void setVolumeMoreThan100() {
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
      assertEquals("The Volume must be a value from 0 to 100, inclusive. '101' is out of this range.", e.getMessage());
    }
  }

  @Test
  public void setVolumeBrokenResponse() {
    final HttpTransport transport = Const.getBrokenResponse();

    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    try {
      soundTouchApi.getVolumeApi()
          .setVolume(100);
      fail();
    } catch (final SoundTouchApiException e) {
      assertEquals("java.io.IOException", e.getMessage());

    }
  }

  @Test
  public void setVolumeIncorrectResponse() {
    final HttpTransport transport = Const.getIncorrectStatusResponse("NOTsetVolume");

    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    try {
      soundTouchApi.getVolumeApi()
          .setVolume(100);
      fail();
    } catch (final SoundTouchApiException e) {
      assertEquals("Invalid Response from Speaker. Response was '/NOTsetVolume'", e.getMessage());
    }
  }
}

