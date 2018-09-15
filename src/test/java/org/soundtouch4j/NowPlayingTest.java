package org.soundtouch4j;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.soundtouch4j.common.SourceEnum;
import org.soundtouch4j.nowplaying.ArtImageStatusEnum;
import org.soundtouch4j.nowplaying.NowPlayingResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.testing.http.HttpTesting;
import com.google.api.client.testing.http.MockHttpTransport;
import com.google.api.client.testing.http.MockLowLevelHttpRequest;
import com.google.api.client.testing.http.MockLowLevelHttpResponse;
import com.google.api.client.xml.Xml;
import junit.framework.TestCase;

public class NowPlayingTest extends TestCase {

  private static final Logger LOGGER = LoggerFactory.getLogger(NowPlayingTest.class);

  public void test01_nowPLayingOe3() {
    LOGGER.info("test01_nowPLayingOe3 started");
    final HttpTransport transport = new MockHttpTransport() {
      @Override
      public LowLevelHttpRequest buildRequest(final String method, final String url) {
        return new MockLowLevelHttpRequest() {
          @Override
          public LowLevelHttpResponse execute() {
            final MockLowLevelHttpResponse result = new MockLowLevelHttpResponse();
            result.setContentType(Xml.MEDIA_TYPE);
            result.setContent("<?xml version=\"1.0\" encoding=\"UTF-8\" ?><nowPlaying deviceID=\"C8DF84AE0B6E\" source=\"INTERNET_RADIO\"><ContentItem source=\"INTERNET_RADIO\" " + "location=\"18298\" sourceAccount=\"\" isPresetable=\"true\"><itemName>ORF Hitradio Ö3</itemName><containerArt>http://item.radio456.com/007452/logo/logo-18298" + ".jpg</containerArt></ContentItem><track></track><artist></artist><album></album><stationName>ORF Hitradio Ö3</stationName><art " + "artImageStatus=\"IMAGE_PRESENT\">http://item.radio456.com/007452/logo/logo-18298.jpg</art><playStatus>PLAY_STATE</playStatus><description>MP3  128 kbps  Vienna Austria,  Hitradio Ö3, das meistgehörte Radio Österreichs, mit den aktuellen Charthits, Pop und Rock aus den 80er- und 90er-Jahren und der Morning Show mit der lustigsten Comedy.</description><stationLocation>Vienna Austria</stationLocation></nowPlaying>");
            return result;
          }
        };
      }
    };

    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    try {
      final NowPlayingResponse response = soundTouchApi.getNowPlayingApi()
          .nowPlaying();
      assertEquals(response.getDeviceID(), "C8DF84AE0B6E");
      assertEquals(response.getSource(), SourceEnum.INTERNET_RADIO);
      assertNull(response.getAlbum());
      assertNull(response.getTrack());
      assertEquals(response.getStationName(), "ORF Hitradio Ö3");
      assertEquals(response.getArt()
          .getArtImageStatus(), ArtImageStatusEnum.IMAGE_PRESENT);
      assertEquals(response.getArt()
          .getValue(), "http://item.radio456.com/007452/logo/logo-18298.jpg");
      assertEquals(response.getPlayStatus(), "PLAY_STATE");
      assertEquals(response.getDescription(), "MP3  128 kbps  Vienna Austria,  Hitradio Ö3, das meistgehörte Radio Österreichs, mit den aktuellen Charthits, Pop und Rock aus den" +
          " 80er- und 90er-Jahren und der Morning Show mit der lustigsten Comedy.");
      assertEquals(response.getStationLocation(), "Vienna Austria");
    } catch (final SoundTouchApiException e) {
      LOGGER.error("Unable to get the basic information: {}", e.getMessage());
      Assert.fail();
    }
    LOGGER.info("test01_nowPLayingOe3 done");
  }


  public void test02_nowPLayingBluetooth() {
    LOGGER.info("test02_nowPLayingBluetooth started");
    final HttpTransport transport = new MockHttpTransport() {
      @Override
      public LowLevelHttpRequest buildRequest(final String method, final String url) {
        return new MockLowLevelHttpRequest() {
          @Override
          public LowLevelHttpResponse execute() {
            final MockLowLevelHttpResponse result = new MockLowLevelHttpResponse();
            result.setContentType(Xml.MEDIA_TYPE);
            result.setContent("<?xml version=\"1.0\" encoding=\"UTF-8\" ?><nowPlaying deviceID=\"C8DF84AE0B6E\" source=\"BLUETOOTH\" sourceAccount=\"\"><ContentItem " + "source" +
                "=\"BLUETOOTH\" location=\"\" sourceAccount=\"\" isPresetable=\"false\"><itemName>Geralds MacBook " + "Pro</itemName></ContentItem><track>Unknown</track><artist" +
                "></artist><album></album><stationName>Geralds MacBook Pro</stationName><art " + "artImageStatus=\"SHOW_DEFAULT_IMAGE\" /><skipEnabled " +
                "/><playStatus>STOP_STATE</playStatus><skipPreviousEnabled /><genre></genre><connectionStatusInfo " + "status=\"CONNECTED\" deviceName=\"Geralds MacBook Pro\" " +
                "/></nowPlaying>");
            return result;
          }
        };
      }
    };

    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    try {
      final NowPlayingResponse response = soundTouchApi.getNowPlayingApi()
          .nowPlaying();
      assertEquals(response.getDeviceID(), "C8DF84AE0B6E");
      assertEquals(response.getSource(), SourceEnum.BLUETOOTH);
      assertNull(response.getAlbum());
      assertEquals(response.getTrack(), "Unknown");
      assertEquals(response.getContentItem()
          .getItemName(), "Geralds MacBook Pro");
      assertEquals(response.getArt()
          .getArtImageStatus(), ArtImageStatusEnum.SHOW_DEFAULT_IMAGE);
      assertEquals(response.getStationName(), "Geralds MacBook Pro");
      assertNull(response.getArt()
          .getValue());
      assertEquals(response.getPlayStatus(), "STOP_STATE");
      assertEquals(response.getConnectionStatusInfo()
          .getStatus(), "CONNECTED");
      assertEquals(response.getConnectionStatusInfo()
          .getDeviceName(), "Geralds MacBook Pro");
    } catch (final SoundTouchApiException e) {
      LOGGER.error("Unable to get the basic information: {}", e.getMessage());
      Assert.fail();
    }
    LOGGER.info("test02_nowPLayingBluetooth done");
  }


  public void test03_nowStandby() {
    LOGGER.info("test03_nowStandby started");
    final HttpTransport transport = new MockHttpTransport() {
      @Override
      public LowLevelHttpRequest buildRequest(final String method, final String url) {
        return new MockLowLevelHttpRequest() {
          @Override
          public LowLevelHttpResponse execute() {
            final MockLowLevelHttpResponse result = new MockLowLevelHttpResponse();
            result.setContentType(Xml.MEDIA_TYPE);
            result.setContent("<?xml version=\"1.0\" encoding=\"UTF-8\" ?><nowPlaying deviceID=\"C8DF84AE0B6E\" source=\"STANDBY\"><ContentItem source=\"STANDBY\" " +
                "isPresetable=\"true\" /></nowPlaying>");
            return result;
          }
        };
      }
    };

    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    try {
      final NowPlayingResponse response = soundTouchApi.getNowPlayingApi()
          .nowPlaying();
      assertEquals(response.getDeviceID(), "C8DF84AE0B6E");
      assertEquals(response.getSource(), SourceEnum.STANDBY);
      assertNull(response.getAlbum());
      assertNull(response.getTrack());
      assertEquals(response.getContentItem()
          .getSource(), SourceEnum.STANDBY);
      assertTrue(response.getContentItem()
          .isPresetable());
      assertNull(response.getArt());
      assertNull(response.getStationName());
      assertNull(response.getPlayStatus());
      assertNull(response.getConnectionStatusInfo());
    } catch (final SoundTouchApiException e) {
      LOGGER.error("Unable to get the basic information: {}", e.getMessage());
      Assert.fail();
    }
    LOGGER.info("test03_nowStandby done");
  }

}
