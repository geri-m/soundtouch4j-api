package org.soundtouch4j;

import java.net.MalformedURLException;
import java.net.URL;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.soundtouch4j.info.InfoResponse;

public class NowPlayingTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(NowPlayingTest.class);


  @Test
  public void test01_nowPlaying() {
    LOGGER.info("test01_nowPlaying started");


    final URL boseEndpoint;
    try {
      boseEndpoint = new URL(Const.URL);
    } catch (final MalformedURLException e) {
      // LOGGER.error("Failed to Create URL from IP: {}. Msg: {}", services.get(0).getRemoteIp(), e.getMessage());
      Assert.fail();
      return;
    }

    final SoundTouchApi soundTouchApi = new SoundTouchApi(boseEndpoint);
    try {
      final InfoResponse response = soundTouchApi.getInfoApi()
          .getInfo();
      LOGGER.info("Info: '{}'", response);
    } catch (final SoundTouchApiException e) {
      LOGGER.error("Unable to press the Power Button: {}", e.getMessage());
      Assert.fail();
      return;
    }

    LOGGER.info("test01_nowPlaying passed");
  }

}
