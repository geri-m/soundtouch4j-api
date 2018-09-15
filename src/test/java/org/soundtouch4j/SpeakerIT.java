package org.soundtouch4j;

import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.soundtouch4j.common.ContentItem;
import org.soundtouch4j.info.InfoResponse;
import org.soundtouch4j.nowplaying.NowPlayingResponse;
import org.soundtouch4j.select.SelectResponse;
import com.google.api.client.http.HttpStatusCodes;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.xml.XmlObjectParser;

public class SpeakerIT {

  private static final Logger LOGGER = LoggerFactory.getLogger(SpeakerIT.class);
  private static final int TIME_TO_SCAN_FOR_DEVICE_IN_MS = 3000;

  @Test
  @Ignore
  public void test00_runThruTest() {
    LOGGER.info("test00_runThruTest started");

    final URL boseEndpoint;
    try {
      boseEndpoint = new URL(Const.URL);
    } catch (final MalformedURLException e) {
      Assert.fail();
      return;
    }

    final SoundTouch soundTouchApi = new SoundTouchApi(boseEndpoint, new NetHttpTransport());
    try {

      // If the speaker is not own, turn on
      if (soundTouchApi.getNowPlayingApi()
          .nowPlaying()
          .isInStandbyMode()) {
        soundTouchApi.getKeyApi()
            .power();
      }

      // Wait till playing
      while (!soundTouchApi.getNowPlayingApi()
          .nowPlaying()
          .isPlaying()) {
        Thread.sleep(100);
      }

      Assert.assertEquals(soundTouchApi.getInfoApi()
          .getInfo()
          .getNetworkInfo()
          .size(), 2);
      Assert.assertEquals(soundTouchApi.getSourceApi()
          .getSources()
          .getSourceItems()
          .size(), 11);

      // Turn off again.
      if (!soundTouchApi.getNowPlayingApi()
          .nowPlaying()
          .isInStandbyMode()) {
        soundTouchApi.getKeyApi()
            .power();
      }

    } catch (final SoundTouchApiException e) {
      LOGGER.error("Unable to press the Power Button: {}", e.getMessage());
      Assert.fail();
      return;
    } catch (InterruptedException e) {
      e.printStackTrace();
    }


    LOGGER.info("test00_runThruTest started");
  }

  @Test
  @Ignore
  public void test01_basicTestWithBox() {
    LOGGER.info("test01_basicTestWithBox passed");

    /*
    Not doing an Active Search for the Beginning

    final List<SsdpService> services = SsdpScanner.synchronousBlockingDeviceScanner(TIME_TO_SCAN_FOR_DEVICE_IN_MS);
    for (final SsdpService service : services) {
      LOGGER.info("Service '{}' with IP '{}' found", service.getSerialNumber(), service.getRemoteIp());
    }
    */


    final URL boseEndpoint;
    try {
      // boseEndpoint = new URL("http://"+ services.get(0).getRemoteIp().getHostAddress() + ":8090");
      boseEndpoint = new URL(Const.URL);
      LOGGER.info("Using urL: {}", boseEndpoint.toString());
    } catch (final MalformedURLException e) {
      // LOGGER.error("Failed to Create URL from IP: {}. Msg: {}", services.get(0).getRemoteIp(), e.getMessage());
      Assert.fail();
      return;
    }

    final SoundTouch soundTouchApi = new SoundTouchApi(boseEndpoint, new NetHttpTransport());
    try {
      soundTouchApi.getKeyApi()
          .power();
    } catch (final SoundTouchApiException e) {
      LOGGER.error("Unable to press the Power Button: {}", e.getMessage());
      Assert.fail();
      return;
    }

    LOGGER.info("test01_basicTestWithBox passed");
  }

  @Test
  @Ignore
  public void test02_fetchInfoFromSpeaker() {
    LOGGER.info("test02_fetchInfoFromSpeaker started");

    final URL boseEndpoint;
    try {
      boseEndpoint = new URL(Const.URL);
    } catch (final MalformedURLException e) {
      // LOGGER.error("Failed to Create URL from IP: {}. Msg: {}", services.get(0).getRemoteIp(), e.getMessage());
      Assert.fail();
      return;
    }

    final SoundTouch soundTouchApi = new SoundTouchApi(boseEndpoint, new NetHttpTransport());
    try {
      final InfoResponse response = soundTouchApi.getInfoApi()
          .getInfo();
    } catch (final SoundTouchApiException e) {
      LOGGER.error("Unable to get the basic information: {}", e.getMessage());
      Assert.fail();
      return;
    }

    LOGGER.info("test02_fetchInfoFromSpeaker passed");
  }

  @Test
  @Ignore
  public void test03_nowPlaying() {
    LOGGER.info("test03_nowPlaying started");


    final URL boseEndpoint;
    try {
      boseEndpoint = new URL(Const.URL);
    } catch (final MalformedURLException e) {
      // LOGGER.error("Failed to Create URL from IP: {}. Msg: {}", services.get(0).getRemoteIp(), e.getMessage());
      Assert.fail();
      return;
    }

    final SoundTouch soundTouchApi = new SoundTouchApi(boseEndpoint, new NetHttpTransport());
    try {
      final NowPlayingResponse response = soundTouchApi.getNowPlayingApi()
          .nowPlaying();
      LOGGER.info("Now Playing: '{}'", response);
    } catch (final SoundTouchApiException e) {
      LOGGER.error("Unable to get information on 'now Playing: {}", e.getMessage());
      Assert.fail();
      return;
    }

    LOGGER.info("test03_nowPlaying passed");
  }

  @Test
  @Ignore
  public void test04_selectAux() {

    LOGGER.info("test03_nowPlaying started");
    final XmlObjectParser parser = new XmlObjectParser(SoundTouchApiClient.DICTIONARY);


    final URL boseEndpoint;
    try {
      boseEndpoint = new URL(Const.URL);
    } catch (final MalformedURLException e) {
      // LOGGER.error("Failed to Create URL from IP: {}. Msg: {}", services.get(0).getRemoteIp(), e.getMessage());
      Assert.fail();
      return;
    }

    final String input = "<ContentItem source=\"AUX\" sourceAccount=\"AUX\"></ContentItem>";
    final ContentItem content;
    try {
      content = parser.parseAndClose(new StringReader(input), ContentItem.class);
      final SoundTouch soundTouchApi = new SoundTouchApi(boseEndpoint, new NetHttpTransport());
      final SelectResponse response = soundTouchApi.getSelectApi()
          .select(content);
      LOGGER.info("Select: '{}'", response);
    } catch (final IOException e) {
      LOGGER.error("Error Parsing XML: {}", e.getMessage());
      Assert.fail();
    } catch (final SoundTouchApiException e) {
      LOGGER.error("Unable to get information on 'now Playing: {}", e.getMessage());
      Assert.fail();
    }

    LOGGER.info("test03_nowPlaying passed");
  }


  @Test
  public void test04_selectIncorrectSource() {

    LOGGER.info("test04_selectIncorrectSource started");
    final XmlObjectParser parser = new XmlObjectParser(SoundTouchApiClient.DICTIONARY);


    final URL boseEndpoint;
    try {
      boseEndpoint = new URL(Const.URL);
    } catch (final MalformedURLException e) {
      // LOGGER.error("Failed to Create URL from IP: {}. Msg: {}", services.get(0).getRemoteIp(), e.getMessage());
      Assert.fail();
      return;
    }

    final String input = "<ContentItem source=\"INTERNET_RADIO\" sourceAccount=\"\"></ContentItem>";
    final ContentItem content;
    try {
      content = parser.parseAndClose(new StringReader(input), ContentItem.class);
      final SoundTouch soundTouchApi = new SoundTouchApi(boseEndpoint, new NetHttpTransport());
      soundTouchApi.getSelectApi()
          .select(content);
      Assert.fail();
    } catch (final IOException e) {
      LOGGER.error("Error Parsing XML: {}", e.getMessage());
      Assert.fail();
    } catch (final SoundTouchApiException e) {
      LOGGER.info("Select Failed: {}", e.getMessage());
      Assert.assertEquals(e.getHttpStatus(), HttpStatusCodes.STATUS_CODE_SERVER_ERROR);
    }

    LOGGER.info("test04_selectIncorrectSource passed");
  }

}
