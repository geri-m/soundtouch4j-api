package org.soundtouch4j;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.soundtouch4j.common.SourceEnum;
import org.soundtouch4j.nowplaying.ArtImageStatusEnum;
import org.soundtouch4j.nowplaying.NowPlayingResponse;
import org.soundtouch4j.nowplaying.PlayStatusEnum;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.testing.http.HttpTesting;

public class NowPlayingTest {

  @Test
  public void nowPLayingSuccessful() throws SoundTouchApiException {
    final String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><nowPlaying deviceID=\"C8DF84AE0B6E\" source=\"INTERNET_RADIO\"><ContentItem source=\"INTERNET_RADIO\" " +
        "location=\"18298\" sourceAccount=\"\" isPresetable=\"true\"><itemName>ORF Hitradio Ö3</itemName><containerArt>http://item.radio456.com/007452/logo/logo-18298" + ".jpg" +
        "</containerArt></ContentItem><track></track><artist></artist><album></album><stationName>ORF Hitradio Ö3</stationName><art " + "artImageStatus=\"IMAGE_PRESENT\">http" +
        "://item.radio456.com/007452/logo/logo-18298.jpg</art><playStatus>PLAY_STATE</playStatus><description>MP3  128 kbps  Vienna Austria," + "  Hitradio Ö3, das meistgehörte " +
        "Radio Österreichs, mit den aktuellen Charthits, Pop und Rock aus den 80er- und 90er-Jahren und der Morning Show mit der lustigsten " + "Comedy.</description" +
        "><stationLocation>Vienna Austria</stationLocation></nowPlaying>";
    final HttpTransport transport = Const.getHttpTransportFromString(xml);

    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);

    final NowPlayingResponse response = soundTouchApi.getNowPlayingApi()
        .nowPlaying();
    assertEquals("C8DF84AE0B6E", response.getDeviceID());
    assertEquals(SourceEnum.INTERNET_RADIO, response.getSource());
    assertNull(response.getAlbum());
    assertNull(response.getTrack());
    assertEquals("ORF Hitradio Ö3", response.getStationName());
    assertEquals(ArtImageStatusEnum.IMAGE_PRESENT, response.getArt()
        .getArtImageStatus());
    assertEquals("http://item.radio456.com/007452/logo/logo-18298.jpg", response.getArt()
        .getValue());
    assertEquals(PlayStatusEnum.PLAY_STATE, response.getPlayStatus());
    assertEquals("Vienna Austria", response.getStationLocation());

  }

  @Test
  public void nowPLayingBluetooth() throws SoundTouchApiException {
    final String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><nowPlaying deviceID=\"C8DF84AE0B6E\" source=\"BLUETOOTH\" sourceAccount=\"\"><ContentItem source" +
        "=\"BLUETOOTH\" location=\"\" sourceAccount=\"\" isPresetable=\"false\"><itemName>Geralds MacBook Pro</itemName></ContentItem><track>Unknown</track><artist></artist" +
        "><album></album><stationName>Geralds MacBook Pro</stationName><art artImageStatus=\"SHOW_DEFAULT_IMAGE\" /><skipEnabled/><playStatus>STOP_STATE</playStatus" +
        "><skipPreviousEnabled/><genre>genre</genre><connectionStatusInfo status=\"CONNECTED\" deviceName=\"Geralds MacBook Pro\" /></nowPlaying>";
    final HttpTransport transport = Const.getHttpTransportFromString(xml);

    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);

    final NowPlayingResponse response = soundTouchApi.getNowPlayingApi()
        .nowPlaying();
    assertEquals("C8DF84AE0B6E", response.getDeviceID());
    assertEquals(SourceEnum.BLUETOOTH, response.getSource());
    assertNull(response.getAlbum());
    assertEquals("Unknown", response.getTrack());
    assertEquals("Geralds MacBook Pro", response.getContentItem()
        .getItemName());
    assertEquals(ArtImageStatusEnum.SHOW_DEFAULT_IMAGE, response.getArt()
        .getArtImageStatus());
    assertEquals("Geralds MacBook Pro", response.getStationName());
    assertNull(response.getArt()
        .getValue());
    assertEquals(PlayStatusEnum.STOP_STATE, response.getPlayStatus());
    assertEquals("CONNECTED", response.getConnectionStatusInfo()
        .getStatus());
    assertEquals("Geralds MacBook Pro", response.getConnectionStatusInfo()
        .getDeviceName());
    assertTrue(!response.isPlaying());
    assertFalse(response.isSkipPreviousEnabled());
    assertFalse(response.isSkipEnabled());
    assertEquals("genre", response.getGenre());

  }

  @Test
  public void nowStandby() throws SoundTouchApiException {
    final String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><nowPlaying deviceID=\"C8DF84AE0B6E\" source=\"STANDBY\"><ContentItem source=\"STANDBY\" isPresetable" +
        "=\"true\" /></nowPlaying>";
    final HttpTransport transport = Const.getHttpTransportFromString(xml);

    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);

    final NowPlayingResponse response = soundTouchApi.getNowPlayingApi()
        .nowPlaying();
    assertEquals("C8DF84AE0B6E", response.getDeviceID());
    assertEquals(SourceEnum.STANDBY, response.getSource());
    assertNull(response.getAlbum());
    assertNull(response.getTrack());
    assertEquals(SourceEnum.STANDBY, response.getContentItem()
        .getSource());
    assertTrue(response.getContentItem()
        .isPresetable());
    assertNull(response.getArt());
    assertNull(response.getStationName());
    assertNull(response.getConnectionStatusInfo());
    assertTrue(response.isInStandbyMode());
    ;
  }

  @Test
  public void nowPlayingSampleDataInAllElements() throws SoundTouchApiException {
    final String xml =
        "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><nowPlaying deviceID=\"deviceID\" source=\"STANDBY\"><ContentItem source=\"UPNP\" location" + "=\"location\" " +
            "sourceAccount=\"sourceAccount\" isPresetable=\"true\"><itemName>itemName</itemName></ContentItem><track>track</track><artist>artist" + "</artist><album>album</album"
            + "><stationName>stationName</stationName><art artImageStatus=\"DOWNLOADING\">url</art><playStatus>PAUSE_STATE</playStatus><description>description</description" +
            "><stationLocation>stationLocation</stationLocation></nowPlaying>";
    final HttpTransport transport = Const.getHttpTransportFromString(xml);
    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);

    final NowPlayingResponse response = soundTouchApi.getNowPlayingApi()
        .nowPlaying();
    assertEquals("deviceID", response.getDeviceID());
    assertEquals(SourceEnum.STANDBY, response.getSource());
    assertEquals("album", response.getAlbum());
    assertEquals("track", response.getTrack());
    assertEquals(SourceEnum.UPNP, response.getContentItem()
        .getSource());
    assertEquals("itemName", response.getContentItem()
        .getItemName());
    assertEquals("sourceAccount", response.getContentItem()
        .getSourceAccount());
    assertEquals("location", response.getContentItem()
        .getLocation());
    assertEquals("artist", response.getArtist());
    assertEquals("stationLocation", response.getStationLocation());
    assertEquals("stationName", response.getStationName());
    assertEquals(ArtImageStatusEnum.DOWNLOADING, response.getArt()
        .getArtImageStatus());
    assertEquals("url", response.getArt()
        .getValue());

    assertEquals(PlayStatusEnum.PAUSE_STATE, response.getPlayStatus());

  }
}
