package org.soundtouch4j;

import com.google.api.client.http.HttpStatusCodes;
import com.google.api.client.http.javanet.NetHttpTransport;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.soundtouch4j.bass.BassGetResponse;
import org.soundtouch4j.basscapabilities.BaseCapabilitiesResponse;
import org.soundtouch4j.common.ContentItem;
import org.soundtouch4j.common.SourceEnum;
import org.soundtouch4j.info.InfoResponse;
import org.soundtouch4j.volume.VolumeGetResponse;
import org.soundtouch4j.zone.Zone;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


// Ignore this Calls during automated builds, as this requires a physical speaker
@Disabled
public class SpeakerIT {


  @Test
  public void runThruTest() {

    final SoundTouch soundTouchApi = new SoundTouchApi(Const.getUrl(), new NetHttpTransport());

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

    VolumeGetResponse getResp = soundTouchApi.getVolumeApi()
        .getVolume();

    while (getResp.isMuteEnabled()) {
      getResp = soundTouchApi.getVolumeApi()
          .getVolume();
    }

    final int initialValue = getResp.getActualVolume();

    // Start setting the volume to a low level.
    soundTouchApi.getVolumeApi()
        .setVolume(5);
    getResp = soundTouchApi.getVolumeApi()
        .getVolume();

    while (getResp.getActualVolume() != getResp.getTargetVolume()) {
      getResp = soundTouchApi.getVolumeApi()
          .getVolume();
    }

    soundTouchApi.getVolumeApi()
        .setVolume(25);
    getResp = soundTouchApi.getVolumeApi()
        .getVolume();

    while (getResp.getActualVolume() != getResp.getTargetVolume()) {
      getResp = soundTouchApi.getVolumeApi()
          .getVolume();
    }

    soundTouchApi.getVolumeApi()
        .setVolume(initialValue);
    getResp = soundTouchApi.getVolumeApi()
        .getVolume();

    while (getResp.getActualVolume() != getResp.getTargetVolume()) {
      getResp = soundTouchApi.getVolumeApi()
          .getVolume();
    }

    soundTouchApi.getKeyApi()
        .mute();
    getResp = soundTouchApi.getVolumeApi()
        .getVolume();

    while (!getResp.isMuteEnabled()) {
      getResp = soundTouchApi.getVolumeApi()
          .getVolume();
    }

    soundTouchApi.getKeyApi()
        .mute();
    getResp = soundTouchApi.getVolumeApi()
        .getVolume();

    while (getResp.isMuteEnabled()) {
      getResp = soundTouchApi.getVolumeApi()
          .getVolume();
    }

    // get the Presets
    soundTouchApi.getPresetApi()
        .getPresets();

    soundTouchApi.getBassCapabilitiesApi()
        .getBassCapabilities();


    // Turn off again.
    if (!soundTouchApi.getNowPlayingApi()
        .nowPlaying()
        .isInStandbyMode()) {
      soundTouchApi.getKeyApi()
          .power();
    }

  }


  @Test
  public void basicTestWithBox() {
    final SoundTouch soundTouchApi = new SoundTouchApi(Const.getUrl(), new NetHttpTransport());
    soundTouchApi.getKeyApi()
        .power();
  }

  @Test

  public void fetchInfoFromSpeaker() throws MalformedURLException {
    final URL boseEndpoint = new URL(Const.URL);
    final SoundTouch soundTouchApi = new SoundTouchApi(boseEndpoint, new NetHttpTransport());
    soundTouchApi.getInfoApi()
        .getInfo();
  }

  @Test
  public void nowPlaying() {
    final SoundTouch soundTouchApi = new SoundTouchApi(Const.getUrl(), new NetHttpTransport());
    soundTouchApi.getNowPlayingApi()
        .nowPlaying();

  }

  @Test
  public void selectAux() {
    final SoundTouch soundTouchApi = new SoundTouchApi(Const.getUrl(), new NetHttpTransport());
    soundTouchApi.getSelectApi()
        .select(new ContentItem(SourceEnum.AUX, "AUX"));
  }


  @Test
  public void selectIncorrectSource() {
    final SoundTouch soundTouchApi = new SoundTouchApi(Const.getUrl(), new NetHttpTransport());
    try {
      soundTouchApi.getSelectApi()
          .select(new ContentItem(SourceEnum.INTERNET_RADIO, ""));
      fail();
    } catch (final SoundTouchApiException e) {
      assertEquals(HttpStatusCodes.STATUS_CODE_SERVER_ERROR, e.getHttpStatus());
    }
  }

  @Test
  public void getAndChangeVolume() {
    final SoundTouch soundTouchApi = new SoundTouchApi(Const.getUrl(), new NetHttpTransport());
    try {
      VolumeGetResponse getResp = soundTouchApi.getVolumeApi()
          .getVolume();

      while (getResp.isMuteEnabled()) {
        getResp = soundTouchApi.getVolumeApi()
            .getVolume();
      }

      final int initalValue = getResp.getActualVolume();
      // Start setting the volume to a low level.
      soundTouchApi.getVolumeApi()
          .setVolume(5);
      getResp = soundTouchApi.getVolumeApi()
          .getVolume();

      while (getResp.getActualVolume() != getResp.getTargetVolume()) {
        getResp = soundTouchApi.getVolumeApi()
            .getVolume();
      }

      soundTouchApi.getVolumeApi()
          .setVolume(25);
      getResp = soundTouchApi.getVolumeApi()
          .getVolume();

      while (getResp.getActualVolume() != getResp.getTargetVolume()) {
        getResp = soundTouchApi.getVolumeApi()
            .getVolume();
      }

      soundTouchApi.getVolumeApi()
          .setVolume(initalValue);
      getResp = soundTouchApi.getVolumeApi()
          .getVolume();

      while (getResp.getActualVolume() != getResp.getTargetVolume()) {
        getResp = soundTouchApi.getVolumeApi()
            .getVolume();
      }

      soundTouchApi.getKeyApi()
          .mute();
      getResp = soundTouchApi.getVolumeApi()
          .getVolume();

      while (!getResp.isMuteEnabled()) {
        getResp = soundTouchApi.getVolumeApi()
            .getVolume();
      }

      soundTouchApi.getKeyApi()
          .mute();
      getResp = soundTouchApi.getVolumeApi()
          .getVolume();

      while (getResp.isMuteEnabled()) {
        getResp = soundTouchApi.getVolumeApi()
            .getVolume();
      }
    } catch (final SoundTouchApiException e) {
      assertEquals(HttpStatusCodes.STATUS_CODE_SERVER_ERROR, e.getHttpStatus());
    }
  }

  @Test
  public void getPresets() {
    final SoundTouch soundTouchApi = new SoundTouchApi(Const.getUrl(), new NetHttpTransport());
    soundTouchApi.getPresetApi()
        .getPresets();
  }

  @Test
  public void setName() {
    final SoundTouch soundTouchApi = new SoundTouchApi(Const.getUrl(), new NetHttpTransport());
    InfoResponse reps = soundTouchApi.getNameApi()
        .setName("test");
    assertEquals("test", reps.getName());


    reps = soundTouchApi.getNameApi()
        .setName("SoundTouch 20");
    assertEquals("SoundTouch 20", reps.getName());
  }

  @Test
  public void getBassCapabilities() {
    final SoundTouch soundTouchApi = new SoundTouchApi(Const.getUrl(), new NetHttpTransport());

    final BaseCapabilitiesResponse reps = soundTouchApi.getBassCapabilitiesApi()
        .getBassCapabilities();

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
  }

  @Test
  public void setAndGetZone() {
    final SoundTouch soundTouchApi = new SoundTouchApi(Const.getUrl(), new NetHttpTransport());
    final Zone zone = soundTouchApi.getZoneApi()
        .getZone();
    soundTouchApi.getZoneApi()
        .setZone(zone);
  }

  @Test
  public void addAndRemoveSlave() {
    final SoundTouch soundTouchApi = new SoundTouchApi(Const.getUrl(), new NetHttpTransport());
    final Zone zone = soundTouchApi.getZoneApi()
        .getZone();
    soundTouchApi.getZoneApi()
        .addZoneSlave(zone);
    soundTouchApi.getZoneApi()
        .removeZoneSlave(zone);
  }

}
