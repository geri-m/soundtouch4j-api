package org.soundtouch4j;

import java.net.URL;
import org.soundtouch4j.bass.BassApi;
import org.soundtouch4j.bassCapabilties.BassCapabilitiesApi;
import org.soundtouch4j.group.GroupApi;
import org.soundtouch4j.info.InfoApi;
import org.soundtouch4j.key.KeyApi;
import org.soundtouch4j.name.NameApi;
import org.soundtouch4j.nowplaying.NowPlayingApi;
import org.soundtouch4j.preset.PresetApi;
import org.soundtouch4j.select.SelectApi;
import org.soundtouch4j.source.SourceApi;
import org.soundtouch4j.volume.VolumeApi;
import org.soundtouch4j.zone.ZoneApi;
import com.google.api.client.http.HttpTransport;

public class SoundTouchApi implements SoundTouch {

  // Single One Instance of the API that does the call to the Box. We want to have this single object to avoid parallel calls.
  private final SoundTouchApiClient soundTouchApiClient;
  private KeyApi keyApi;
  private InfoApi infoApi;
  private NowPlayingApi nowPlayingApi;
  private SourceApi sourceApi;
  private SelectApi selectApi;
  private VolumeApi volumeApi;
  private PresetApi presetApi;
  private NameApi nameApi;
  private BassCapabilitiesApi bassCapabilitiesApi;
  private BassApi bassApi;
  private ZoneApi zoneApi;
  private GroupApi groupApi;

  /**
   * This is the Entrypoint of the API. You have to create an entry point with the URL and the HTTP Transport implementation.
   *
   * @param endpoint  This is a {@link URL} of the endpoint you want to connect to. You can use the {@code SsdpScanner} to Scan for your Speaker and retrieve the IP-Address of it.
   * @param transport Here you have to specify the implementation of Transport. Having this parameter gives us platform independence and better testability
   *                  <ul>
   *                  <li>J2SE {@link com.google.api.client.http.javanet.NetHttpTransport}</li>
   *                  <li>Mocking {@link com.google.api.client.testing.http.MockHttpTransport}</li>
   *                  <li>Android {@code AndroidHttp.newCompatibleTransport()}</li>
   *                  </ul>
   */
  public SoundTouchApi(final URL endpoint, final HttpTransport transport) {
    soundTouchApiClient = new SoundTouchApiClient(endpoint, transport);
  }

  /**
   * Gets the KeyApi instance owned by this SoundTouchApi instance. The KeyApi is used to perform all key press/release/event related API calls.
   *
   * @return the KeyApi instance owned by this SoundTouchApi instance
   */

  @Override
  public KeyApi getKeyApi() {

    if (keyApi == null) {
      synchronized (this) {
        if (keyApi == null) {
          keyApi = new KeyApi(this);
        }
      }
    }
    return (keyApi);
  }


  /**
   * Gets the {@link InfoApi} instance owned by this SoundTouchApi instance. The {@link InfoApi} is used fetch the information form the Bose Speaker.
   *
   * @return the {@link InfoApi} instance owned by this SoundTouchApi instance
   */

  @Override
  public InfoApi getInfoApi() {

    if (infoApi == null) {
      synchronized (this) {
        if (infoApi == null) {
          infoApi = new InfoApi(this);
        }
      }
    }
    return (infoApi);
  }


  /**
   * Gets the {@link NowPlayingApi} instance owned by this SoundTouchApi instance. The {@link NowPlayingApi} is used to get the information what/if the speaker is playing
   *
   * @return the {@link NowPlayingApi} instance owned by this SoundTouchApi instance
   */

  @Override
  public NowPlayingApi getNowPlayingApi() {

    if (nowPlayingApi == null) {
      synchronized (this) {
        if (nowPlayingApi == null) {
          nowPlayingApi = new NowPlayingApi(this);
        }
      }
    }
    return (nowPlayingApi);
  }


  /**
   * Gets the {@link SourceApi} instance owned by this SoundTouchApi instance. The {@link SourceApi} is used to get possible Sources of the speaker
   *
   * @return the {@link SourceApi} instance owned by this SoundTouchApi instance
   */

  @Override
  public SourceApi getSourceApi() {

    if (sourceApi == null) {
      synchronized (this) {
        if (sourceApi == null) {
          sourceApi = new SourceApi(this);
        }
      }
    }
    return (sourceApi);
  }

  /**
   * Gets the {@link SelectApi} instance owned by this SoundTouchApi instance. The {@link SelectApi} is select sources of the speaker
   *
   * @return the {@link SelectApi} instance owned by this SoundTouchApi instance
   */

  @Override
  public SelectApi getSelectApi() {

    if (selectApi == null) {
      synchronized (this) {
        if (selectApi == null) {
          selectApi = new SelectApi(this);
        }
      }
    }
    return (selectApi);
  }


  /**
   * Gets the {@link VolumeApi} instance owned by this SoundTouchApi instance. The {@link VolumeApi} is used to perform changes on the volume of the speaker.
   *
   * @return the {@link VolumeApi} instance owned by this SoundTouchApi instance
   */

  @Override
  public VolumeApi getVolumeApi() {

    if (volumeApi == null) {
      synchronized (this) {
        if (volumeApi == null) {
          volumeApi = new VolumeApi(this);
        }
      }
    }
    return (volumeApi);
  }


  /**
   * Gets the {@link PresetApi} instance owned by this SoundTouchApi instance. The {@link PresetApi} is used to fetch the presets from the speaker.
   *
   * @return the {@link PresetApi} instance owned by this SoundTouchApi instance
   */

  @Override
  public PresetApi getPresetApi() {
    if (presetApi == null) {
      synchronized (this) {
        if (presetApi == null) {
          presetApi = new PresetApi(this);
        }
      }
    }
    return (presetApi);
  }

  /**
   * Gets the {@link NameApi} instance owned by this SoundTouchApi instance. The {@link NameApi} is used to set the name of the speaker
   *
   * @return the {@link NameApi} instance owned by this SoundTouchApi instance
   */

  @Override
  public NameApi getNameApi() {
    if (nameApi == null) {
      synchronized (this) {
        if (nameApi == null) {
          nameApi = new NameApi(this);
        }
      }
    }
    return (nameApi);
  }

  /**
   * Gets the {@link BassCapabilitiesApi} instance owned by this SoundTouchApi instance. The {@link BassCapabilitiesApi} is used to information on Bass Capabilities
   *
   * @return the {@link BassCapabilitiesApi} instance owned by this SoundTouchApi instance
   */


  @Override
  public BassCapabilitiesApi getBassCapabilitiesApi() {
    if (bassCapabilitiesApi == null) {
      synchronized (this) {
        if (bassCapabilitiesApi == null) {
          bassCapabilitiesApi = new BassCapabilitiesApi(this);
        }
      }
    }
    return (bassCapabilitiesApi);
  }


  /**
   * Gets the {@link BassApi} instance owned by this SoundTouchApi instance. The {@link BassApi} is used to information on Bass
   *
   * @return the {@link BassApi} instance owned by this SoundTouchApi instance
   */


  @Override
  public BassApi getBassApi() {
    if (bassApi == null) {
      synchronized (this) {
        if (bassApi == null) {
          bassApi = new BassApi(this);
        }
      }
    }
    return (bassApi);
  }


  /**
   * Gets the {@link ZoneApi} instance owned by this SoundTouchApi instance. The {@link ZoneApi} is set/get Zones
   *
   * @return the {@link ZoneApi} instance owned by this SoundTouchApi instance
   */


  @Override
  public ZoneApi getZoneApi() {
    if (zoneApi == null) {
      synchronized (this) {
        if (zoneApi == null) {
          zoneApi = new ZoneApi(this);
        }
      }
    }
    return (zoneApi);
  }

  /**
   * Gets the {@link GroupApi} instance owned by this SoundTouchApi instance. The {@link GroupApi} is get Groups
   *
   * @return the {@link GroupApi} instance owned by this SoundTouchApi instance
   */

  @Override
  public GroupApi getGroupApi() {
    if (groupApi == null) {
      synchronized (this) {
        if (groupApi == null) {
          groupApi = new GroupApi(this);
        }
      }
    }
    return (groupApi);
  }

  public SoundTouchApiClient getSoundTouchApiClient() {
    return soundTouchApiClient;
  }


}
