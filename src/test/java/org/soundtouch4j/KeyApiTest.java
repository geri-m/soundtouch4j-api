package org.soundtouch4j;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.testing.http.HttpTesting;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class KeyApiTest {

  private final HttpTransport transport = Const.getHttpTransportFromString("<?xml version=\"1.0\" encoding=\"UTF-8\" ?><status>/key</status>");

  @Test
  public void powerOnSuccessful() {
    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    soundTouchApi.getKeyApi()
        .power();
  }

  @Test
  public void powerBrokenResponse() {
    final HttpTransport transport = Const.getBrokenResponse();
    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    try {
      soundTouchApi.getKeyApi()
          .power();
      fail();
    } catch (final SoundTouchApiException e) {
      assertEquals("java.io.IOException", e.getLocalizedMessage());
    }
  }

  @Test
  public void powerIncorrectResponse() {
    final HttpTransport transport = Const.getIncorrectStatusResponse("NOTPower");
    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    try {
      soundTouchApi.getKeyApi()
          .power();
      fail();
    } catch (final SoundTouchApiException e) {
      assertEquals("Invalid Response from Speaker. Response was '/NOTPower'", e.getMessage());
    }
  }


  @Test
  public void muteSucessfully() {
    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    soundTouchApi.getKeyApi()
        .mute();
  }

  @Test
  public void muteBrokenResponse() {
    final HttpTransport transport = Const.getBrokenResponse();
    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    try {
      soundTouchApi.getKeyApi()
          .mute();
      fail();
    } catch (final SoundTouchApiException e) {
      assertEquals("java.io.IOException", e.getLocalizedMessage());
    }
  }

  @Test
  public void muteIncorrectResponse() {
    final HttpTransport transport = Const.getIncorrectStatusResponse("NOTMute");
    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    try {
      soundTouchApi.getKeyApi()
          .mute();
      fail();
    } catch (final SoundTouchApiException e) {
      assertEquals("Invalid Response from Speaker. Response was '/NOTMute'", e.getMessage());
    }
  }
}
