package org.soundtouch4j;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import java.net.MalformedURLException;
import java.net.URL;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.soundtouch4j.bass.BassGetResponse;
import org.soundtouch4j.basscapabilities.BaseCapabilitiesResponse;
import org.soundtouch4j.common.ContentItem;
import org.soundtouch4j.common.SourceEnum;
import org.soundtouch4j.info.InfoResponse;
import org.soundtouch4j.nowplaying.NowPlayingResponse;
import org.soundtouch4j.preset.PresetResponse;
import org.soundtouch4j.volume.VolumeGetResponse;
import org.soundtouch4j.zone.Zone;
import com.google.api.client.http.HttpStatusCodes;
import com.google.api.client.http.javanet.NetHttpTransport;


// Ignore this Calls during automated builds, as this requires a physical speaker
@Disabled
public class SpeakerIT {

  private static final Logger LOGGER = LoggerFactory.getLogger(SpeakerIT.class);
  private static final int TIME_TO_SCAN_FOR_DEVICE_IN_MS = 3000;

  @Test
  @Disabled
  public void test00_runThruTest() {
    LOGGER.info("test00_runThruTest started");

    final SoundTouch soundTouchApi = new SoundTouchApi(Const.getUrl(), new NetHttpTransport());
    try {

      // If the speaker is not on, turn on
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
      }

      assertEquals(2, soundTouchApi.getInfoApi()
          .getInfo()
          .getNetworkInfo()
          .size());
      assertEquals(11, soundTouchApi.getSourceApi()
          .getSources()
          .getSourceItems()
          .size());


      LOGGER.info("Un-Mute, if speaker is muted");
      VolumeGetResponse getResp = soundTouchApi.getVolumeApi()
          .getVolume();

      while (getResp.isMuteEnabled()) {
        LOGGER.info("Checking Mute (to disable)");
        getResp = soundTouchApi.getVolumeApi()
            .getVolume();
      }

      final int initalValue = getResp.getActualVolume();

      LOGGER.info("Status: {}", getResp.toString());

      // Start setting the volume to a low level.
      soundTouchApi.getVolumeApi()
          .setVolume(5);
      getResp = soundTouchApi.getVolumeApi()
          .getVolume();

      while (getResp.getActualVolume() != getResp.getTargetVolume()) {
        LOGGER.info("Checking Volume");
        getResp = soundTouchApi.getVolumeApi()
            .getVolume();
      }

      soundTouchApi.getVolumeApi()
          .setVolume(25);
      getResp = soundTouchApi.getVolumeApi()
          .getVolume();

      while (getResp.getActualVolume() != getResp.getTargetVolume()) {
        LOGGER.info("Checking Volume");
        getResp = soundTouchApi.getVolumeApi()
            .getVolume();
      }

      soundTouchApi.getVolumeApi()
          .setVolume(initalValue);
      getResp = soundTouchApi.getVolumeApi()
          .getVolume();

      while (getResp.getActualVolume() != getResp.getTargetVolume()) {
        LOGGER.info("Checking Volume");
        getResp = soundTouchApi.getVolumeApi()
            .getVolume();
      }

      soundTouchApi.getKeyApi()
          .mute();
      getResp = soundTouchApi.getVolumeApi()
          .getVolume();

      while (!getResp.isMuteEnabled()) {
        LOGGER.info("Checking Mute (to disable)");
        getResp = soundTouchApi.getVolumeApi()
            .getVolume();
      }

      soundTouchApi.getKeyApi()
          .mute();
      getResp = soundTouchApi.getVolumeApi()
          .getVolume();

      while (getResp.isMuteEnabled()) {
        LOGGER.info("Checking Mute (to enable again)");
        getResp = soundTouchApi.getVolumeApi()
            .getVolume();
      }

      // get the Presets
      final PresetResponse reps = soundTouchApi.getPresetApi()
          .getPresets();

      final BaseCapabilitiesResponse bassCapabilities = soundTouchApi.getBassCapabilitiesApi()
          .getBassCapabilities();


      // Turn off again.
      if (!soundTouchApi.getNowPlayingApi()
          .nowPlaying()
          .isInStandbyMode()) {
        soundTouchApi.getKeyApi()
            .power();
      }

    } catch (final SoundTouchApiException e) {
      LOGGER.error("Unable to press the Power Button: {}", e.getMessage());
      fail();
      return;
    }

    LOGGER.info("test00_runThruTest started");
  }


  @Test

  public void test01_basicTestWithBox() {
    LOGGER.info("test01_basicTestWithBox passed");

    final SoundTouch soundTouchApi = new SoundTouchApi(Const.getUrl(), new NetHttpTransport());

    try {
      soundTouchApi.getKeyApi()
          .power();
    } catch (final SoundTouchApiException e) {
      LOGGER.error("Unable to press the Power Button: {}", e.getMessage());
      fail();
      return;
    }

    LOGGER.info("test01_basicTestWithBox passed");
  }

  @Test

  public void test02_fetchInfoFromSpeaker() {
    LOGGER.info("test02_fetchInfoFromSpeaker started");

    final URL boseEndpoint;
    try {
      boseEndpoint = new URL(Const.URL);
    } catch (final MalformedURLException e) {
      // LOGGER.error("Failed to Create URL from IP: {}. Msg: {}", services.get(0).getRemoteIp(), e.getMessage());
      fail();
      return;
    }

    final SoundTouch soundTouchApi = new SoundTouchApi(boseEndpoint, new NetHttpTransport());
    try {
      soundTouchApi.getInfoApi()
          .getInfo();
    } catch (final SoundTouchApiException e) {
      LOGGER.error("Unable to get the basic information: {}", e.getMessage());
      fail();
      return;
    }

    LOGGER.info("test02_fetchInfoFromSpeaker passed");
  }

  @Test

  public void test03_nowPlaying() {
    LOGGER.info("test03_nowPlaying started");
    final SoundTouch soundTouchApi = new SoundTouchApi(Const.getUrl(), new NetHttpTransport());
    try {
      final NowPlayingResponse response = soundTouchApi.getNowPlayingApi()
          .nowPlaying();
      LOGGER.info("Now Playing: '{}'", response);
    } catch (final SoundTouchApiException e) {
      LOGGER.error("Unable to get information on 'now Playing: {}", e.getMessage());
      fail();
      return;
    }

    LOGGER.info("test03_nowPlaying passed");
  }

  @Test

  public void test04_selectAux() {
    LOGGER.info("test03_nowPlaying started");
    final SoundTouch soundTouchApi = new SoundTouchApi(Const.getUrl(), new NetHttpTransport());
    try {
      soundTouchApi.getSelectApi()
          .select(new ContentItem(SourceEnum.AUX, "AUX"));
    } catch (final SoundTouchApiException e) {
      LOGGER.error("Unable to get information on 'now Playing: {}", e.getMessage());
      fail();
    }

    LOGGER.info("test03_nowPlaying passed");
  }


  @Test

  public void test04_selectIncorrectSource() {
    LOGGER.info("test04_selectIncorrectSource started");
    final SoundTouch soundTouchApi = new SoundTouchApi(Const.getUrl(), new NetHttpTransport());

    try {
      soundTouchApi.getSelectApi()
          .select(new ContentItem(SourceEnum.INTERNET_RADIO, ""));
      fail();
    } catch (final SoundTouchApiException e) {
      LOGGER.info("Select Failed: {}", e.getMessage());
      assertEquals(HttpStatusCodes.STATUS_CODE_SERVER_ERROR, e.getHttpStatus());
    }

    LOGGER.info("test04_selectIncorrectSource passed");
  }

  @Test

  public void test05_getAndChangeVolume() {
    LOGGER.info("test05_getAndChangeVolume started");
    final SoundTouch soundTouchApi = new SoundTouchApi(Const.getUrl(), new NetHttpTransport());
    try {
      VolumeGetResponse getResp = soundTouchApi.getVolumeApi()
          .getVolume();

      LOGGER.info("Un-Mute, if speaker is muted");

      while (getResp.isMuteEnabled()) {
        LOGGER.info("Checking Mute (to disable)");
        getResp = soundTouchApi.getVolumeApi()
            .getVolume();
      }

      final int initalValue = getResp.getActualVolume();

      LOGGER.info("Status: {}", getResp.toString());

      // Start setting the volume to a low level.
      soundTouchApi.getVolumeApi()
          .setVolume(5);
      getResp = soundTouchApi.getVolumeApi()
          .getVolume();

      while (getResp.getActualVolume() != getResp.getTargetVolume()) {
        LOGGER.info("Checking Volume");
        getResp = soundTouchApi.getVolumeApi()
            .getVolume();
      }

      soundTouchApi.getVolumeApi()
          .setVolume(25);
      getResp = soundTouchApi.getVolumeApi()
          .getVolume();

      while (getResp.getActualVolume() != getResp.getTargetVolume()) {
        LOGGER.info("Checking Volume");
        getResp = soundTouchApi.getVolumeApi()
            .getVolume();
      }

      soundTouchApi.getVolumeApi()
          .setVolume(initalValue);
      getResp = soundTouchApi.getVolumeApi()
          .getVolume();

      while (getResp.getActualVolume() != getResp.getTargetVolume()) {
        LOGGER.info("Checking Volume");
        getResp = soundTouchApi.getVolumeApi()
            .getVolume();
      }

      soundTouchApi.getKeyApi()
          .mute();
      getResp = soundTouchApi.getVolumeApi()
          .getVolume();

      while (!getResp.isMuteEnabled()) {
        LOGGER.info("Checking Mute (to disable)");
        getResp = soundTouchApi.getVolumeApi()
            .getVolume();
      }

      soundTouchApi.getKeyApi()
          .mute();
      getResp = soundTouchApi.getVolumeApi()
          .getVolume();

      while (getResp.isMuteEnabled()) {
        LOGGER.info("Checking Mute (to enable again)");
        getResp = soundTouchApi.getVolumeApi()
            .getVolume();
      }


    } catch (final SoundTouchApiException e) {
      LOGGER.info("Select Failed: {}", e.getMessage());
      assertEquals(HttpStatusCodes.STATUS_CODE_SERVER_ERROR, e.getHttpStatus());
    }

    LOGGER.info("test05_getAndChangeVolume passed");
  }

  @Test

  public void test06_getPresets() {
    LOGGER.info("test06_getPresets started");
    final SoundTouch soundTouchApi = new SoundTouchApi(Const.getUrl(), new NetHttpTransport());

    try {
      final PresetResponse reps = soundTouchApi.getPresetApi()
          .getPresets();
      LOGGER.info("Result: {}", reps);
    } catch (final SoundTouchApiException e) {
      LOGGER.info("Select Failed: {}", e.getMessage());
      fail();
    }

    LOGGER.info("test06_getPresets passed");
  }

  @Test

  public void test07_setName() {
    LOGGER.info("test07_setName started");
    final SoundTouch soundTouchApi = new SoundTouchApi(Const.getUrl(), new NetHttpTransport());

    try {
      InfoResponse reps = soundTouchApi.getNameApi()
          .setName("test");
      assertEquals("test", reps.getName());


      reps = soundTouchApi.getNameApi()
          .setName("SoundTouch 20");
      assertEquals("SoundTouch 20", reps.getName());

    } catch (final SoundTouchApiException e) {
      LOGGER.info("Select Failed: {}", e.getMessage());
      fail();
    }

    LOGGER.info("test07_setName passed");
  }

  @Test

  public void test08_getBassCapabilities() {
    LOGGER.info("test08_getBassCapabilities started");
    final SoundTouch soundTouchApi = new SoundTouchApi(Const.getUrl(), new NetHttpTransport());

    try {
      final BaseCapabilitiesResponse reps = soundTouchApi.getBassCapabilitiesApi()
          .getBassCapabilities();

      LOGGER.info("BaseCapabilitiesResponse: {}", reps);

      BassGetResponse responseBass = soundTouchApi.getBassApi()
          .getBass();
      final int actualBass = responseBass.getActualBass();

      soundTouchApi.getBassApi()
          .setBass(-5);
      while (soundTouchApi.getBassApi()
          .getBass()
          .getActualBass() != -5) {

      }
      assertEquals(-5, responseBass.getActualBass());

      soundTouchApi.getBassApi()
          .setBass(actualBass);
      responseBass = soundTouchApi.getBassApi()
          .getBass();

      while (soundTouchApi.getBassApi()
          .getBass()
          .getActualBass() != responseBass.getActualBass()) {

      }

      assertEquals(actualBass, responseBass.getActualBass());


    } catch (final SoundTouchApiException e) {
      LOGGER.info("Bass Get Failed: {}", e.getMessage());
      fail();
    }

    LOGGER.info("test08_getBassCapabilities passed");
  }


  @Test

  public void test09_setAndGetZone() {
    LOGGER.info("test09_setAndGetZone started");
    final SoundTouch soundTouchApi = new SoundTouchApi(Const.getUrl(), new NetHttpTransport());

    try {
      final Zone zone = soundTouchApi.getZoneApi()
          .getZone();

      soundTouchApi.getZoneApi()
          .setZone(zone);

    } catch (final SoundTouchApiException e) {
      LOGGER.info("setAndGetZone: {}", e.getMessage());
      fail();
    }

    LOGGER.info("test09_setAndGetZone passed");

  }

  @Test

  public void test10_addAndRemoveSlave() {
    LOGGER.info("test10_addAndRemoveSlave started");
    final SoundTouch soundTouchApi = new SoundTouchApi(Const.getUrl(), new NetHttpTransport());

    try {
      final Zone zone = soundTouchApi.getZoneApi()
          .getZone();

      soundTouchApi.getZoneApi()
          .addZoneSlave(zone);

      soundTouchApi.getZoneApi()
          .removeZoneSlave(zone);

    } catch (final SoundTouchApiException e) {
      LOGGER.info("addAndRemoveSlave: {}", e.getMessage());
      fail();
    }

    LOGGER.info("test10_addAndRemoveSlave passed");

  }

}
