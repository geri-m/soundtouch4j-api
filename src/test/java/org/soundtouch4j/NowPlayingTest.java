package org.soundtouch4j;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.testing.http.HttpTesting;
import org.junit.jupiter.api.Test;
import org.soundtouch4j.common.SourceEnum;
import org.soundtouch4j.nowplaying.ArtImageStatusEnum;
import org.soundtouch4j.nowplaying.NowPlayingResponse;
import org.soundtouch4j.nowplaying.PlayStatusEnum;
import org.soundtouch4j.nowplaying.StreamTypeEnum;

import static org.junit.jupiter.api.Assertions.*;

public class NowPlayingTest {

  @Test
  public void nowPLayingSuccessfulVersion19() {
    final String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><nowPlaying deviceID=\"C8DF84AE0B6E\" source=\"INTERNET_RADIO\"><ContentItem source=\"INTERNET_RADIO\" " +
        "location=\"18298\" sourceAccount=\"\" isPresetable=\"true\"><itemName>ORF Hitradio Ö3</itemName><containerArt>http://item.radio456.com/007452/logo/logo-18298.jpg" + "</containerArt></ContentItem><track></track><artist></artist><album></album><stationName>ORF Hitradio Ö3</stationName><art artImageStatus=\"IMAGE_PRESENT\">http" + "://item.radio456.com/007452/logo/logo-18298.jpg</art><playStatus>PLAY_STATE</playStatus><description>MP3  128 kbps  Vienna Austria,  Hitradio Ö3, das meistgehörte " + "Radio Österreichs, mit den aktuellen Charthits, Pop und Rock aus den 80er- und 90er-Jahren und der Morning Show mit der lustigsten Comedy.</description" +
        "><stationLocation>Vienna Austria</stationLocation></nowPlaying>";
    final HttpTransport transport = Const.getHttpTransportFromString(xml);

    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);

    final NowPlayingResponse response = soundTouchApi.getNowPlayingApi()
        .nowPlaying();
    assertEquals("NowPlayingResponse{source=INTERNET_RADIO, deviceID='C8DF84AE0B6E', contentItem=ContentItem{source=INTERNET_RADIO, isPresetable=true, location='18298', " +
        "sourceAccount='', itemName='ORF Hitradio Ö3', containerArt='http://item.radio456.com/007452/logo/logo-18298.jpg'}, track='null', artist='null', album='null', " +
        "genre='null', rating='null', stationName='ORF Hitradio Ö3', art=Art{value=http://item.radio456.com/007452/logo/logo-18298.jpg, artImageStatus=IMAGE_PRESENT}, time=null," +
        " skipEnabled=false, skipPreviousEnabled=false, favoriteEnabled=null, isFavorite=false, rateEnabled=null, playStatus=PLAY_STATE, stationLocation='Vienna Austria', " +
        "connectionStatusInfo=null}", response.toString());
    assertEquals("C8DF84AE0B6E", response.getDeviceID());
    assertEquals(SourceEnum.INTERNET_RADIO, response.getSource());
    assertNull(response.getShuffleSetting());
    assertNull(response.getRepeatSettings());
    assertNull(response.getStreamType());
    assertTrue(response.isPlaying());
    assertFalse(response.isInStandbyMode());
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
  public void nowPlayingSuccessfulVersion20() {
    final String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><nowPlaying " +
        "deviceID=\"C8DF84AE0B6E\" source=\"TUNEIN\" sourceAccount=\"\"><ContentItem " +
        "source=\"TUNEIN\" type=\"stationurl\" location=\"/v1/playback/station/s8007\" " +
        "sourceAccount=\"\" isPresetable=\"true\"><itemName>Hitradio " +
        "Ö3</itemName><containerArt>http://radiotime-logos.s3.amazonaws.com/s8007q" +
        ".png</containerArt></ContentItem><track>Hitradio Ö3</track><artist>Katy Perry feat. " +
        "Migos - Bon AppÉTit</artist><album></album><stationName>Hitradio Ö3</stationName><art " +
        "artImageStatus=\"IMAGE_PRESENT\">http://cdn-radiotime-logos.tunein.com/s8007q" +
        ".png</art><favoriteEnabled /><playStatus>PLAY_STATE</playStatus><streamType" +
        ">RADIO_STREAMING</streamType></nowPlaying>";
    final HttpTransport transport = Const.getHttpTransportFromString(xml);
    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(),
        transport);
    final NowPlayingResponse response = soundTouchApi.getNowPlayingApi()
        .nowPlaying();

    assertEquals("C8DF84AE0B6E", response.getDeviceID());
    assertEquals(SourceEnum.TUNEIN, response.getSource());
    assertEquals("", response.getSourceAccount());
    assertEquals(SourceEnum.TUNEIN, response.getContentItem()
        .getSource());
    assertEquals("stationurl", response.getContentItem()
        .getType());
    assertEquals("/v1/playback/station/s8007", response.getContentItem()
        .getLocation());
    assertEquals("", response.getContentItem()
        .getSourceAccount());
    assertTrue(response.getContentItem()
        .isPresetable());
    assertEquals("Hitradio Ö3", response.getContentItem()
        .getItemName());
    assertEquals("http://radiotime-logos.s3.amazonaws.com/s8007q.png", response.getContentItem()
        .getContainerArt());
    assertEquals("Hitradio Ö3", response.getTrack());

    assertEquals("Katy Perry feat. Migos - Bon AppÉTit", response.getArtist());

    assertEquals(StreamTypeEnum.RADIO_STREAMING, response.getStreamType());
    assertTrue(response.isPlaying());
    assertFalse(response.isInStandbyMode());
    assertNull(response.getAlbum());
    assertEquals("Hitradio Ö3", response.getStationName());
    assertEquals(ArtImageStatusEnum.IMAGE_PRESENT, response.getArt()
        .getArtImageStatus());
    assertEquals("http://cdn-radiotime-logos.tunein.com/s8007q.png", response.getArt()
        .getValue());
    assertEquals(PlayStatusEnum.PLAY_STATE, response.getPlayStatus());
  }


  @Test
  public void nowPLayingBluetooth() {
    final String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><nowPlaying deviceID=\"C8DF84AE0B6E\" source=\"BLUETOOTH\" sourceAccount=\"\"><ContentItem source" +
        "=\"BLUETOOTH\" location=\"\" sourceAccount=\"\" isPresetable=\"false\"><itemName>Geralds MacBook Pro</itemName></ContentItem><track>Unknown</track><artist></artist" +
        "><album></album><stationName>Geralds MacBook Pro</stationName><art artImageStatus=\"SHOW_DEFAULT_IMAGE\" /><skipEnabled/><playStatus>STOP_STATE</playStatus" +
        "><skipPreviousEnabled/><genre>genre</genre><connectionStatusInfo status=\"CONNECTED\" deviceName=\"Geralds MacBook Pro\" /></nowPlaying>";
    final HttpTransport transport = Const.getHttpTransportFromString(xml);

    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);

    final NowPlayingResponse response = soundTouchApi.getNowPlayingApi()
        .nowPlaying();
    assertEquals("NowPlayingResponse{source=BLUETOOTH, deviceID='C8DF84AE0B6E', contentItem=ContentItem{source=BLUETOOTH, isPresetable=false, location='', sourceAccount='', " +
        "itemName='Geralds MacBook Pro', containerArt='null'}, track='Unknown', artist='null', album='null', genre='genre', rating='null', stationName='Geralds MacBook Pro', " +
        "art=Art{value=null, artImageStatus=SHOW_DEFAULT_IMAGE}, time=null, skipEnabled=false, skipPreviousEnabled=false, favoriteEnabled=null, isFavorite=false, " +
        "rateEnabled=null, playStatus=STOP_STATE, stationLocation='null', connectionStatusInfo=ConnectionStatusInfo{status='CONNECTED', deviceName=Geralds MacBook Pro}}", response.toString());
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
  public void nowStandby() {
    final String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><nowPlaying deviceID=\"C8DF84AE0B6E\" source=\"STANDBY\"><ContentItem source=\"STANDBY\" isPresetable" +
        "=\"true\" /></nowPlaying>";
    final HttpTransport transport = Const.getHttpTransportFromString(xml);

    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);

    final NowPlayingResponse response = soundTouchApi.getNowPlayingApi()
        .nowPlaying();
    assertEquals("NowPlayingResponse{source=STANDBY, deviceID='C8DF84AE0B6E', contentItem=ContentItem{source=STANDBY, isPresetable=true, location='null', sourceAccount='null', " +
        "itemName='null', containerArt='null'}, track='null', artist='null', album='null', genre='null', rating='null', stationName='null', art=null, time=null, " +
        "skipEnabled=false, skipPreviousEnabled=false, favoriteEnabled=null, isFavorite=false, rateEnabled=null, playStatus=null, stationLocation='null', connectionStatusInfo=null}", response.toString());
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
    assertFalse(response.isPlaying());
  }

  @Test
  public void nowPlayingSampleDataInAllElements() {
    final String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><nowPlaying deviceID=\"deviceID\" source=\"STANDBY\"><ContentItem source=\"UPNP\" location=\"location\" " +
        "sourceAccount=\"sourceAccount\" isPresetable=\"true\"><itemName>itemName</itemName></ContentItem><track>track</track><artist>artist</artist><album>album</album" +
        "><stationName>stationName</stationName><art artImageStatus=\"DOWNLOADING\">url</art><playStatus>PAUSE_STATE</playStatus><description>description</description" +
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
