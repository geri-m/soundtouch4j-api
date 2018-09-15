package org.soundtouch4j;

import java.net.URL;
import org.soundtouch4j.info.InfoApi;
import org.soundtouch4j.key.KeyApi;
import org.soundtouch4j.nowplaying.NowPlayingApi;
import org.soundtouch4j.source.SourceApi;
import com.google.api.client.http.HttpTransport;

public class SoundTouchApi implements SoundTouch {

  // Single One Instance of the API that does the call to the Box. We want to have this single object to avoid parallel calls.
  private final SoundTouchApiClient soundTouchApiClient;
  private KeyApi keyApi;
  private InfoApi infoApi;
  private NowPlayingApi nowPlayingApi;
  private SourceApi sourceApi;

  /**
   * This is the Entrypoint of the API. You have to create an entry point with the URL and the HTTP Transport implementation.
   *
   * @param endpoint  This is a {@link URL} of the endpoint you want to connect to. You can use the {@code SsdpScanner} to Scan for your Speaker and retrieve the IP-Address of it.
   * @param transport Here you have to specify the implementation of Transport. Having this parameter gives us platform independence and better testability
  <ul>
  <li>J2SE {@link com.google.api.client.http.javanet.NetHttpTransport}</li>
  <li>Mocking {@link com.google.api.client.testing.http.MockHttpTransport}</li>
  <li>Android {@code AndroidHttp.newCompatibleTransport()}</li>
  </ul>
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
   * Gets the InfoApi instance owned by this SoundTouchApi instance. The InfoApi is used fetch the information form the BOSE Speaker.
   *
   * @return the InfoApi instance owned by this SoundTouchApi instance
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
   * Gets the NowPlayingApi instance owned by this SoundTouchApi instance. The NowPlayingApi is used to perform all key press/release/event related API calls.
   *
   * @return the KeyApi instance owned by this SoundTouchApi instance
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
   * Gets the KeyApi instance owned by this SoundTouchApi instance. The KeyApi is used to perform all key press/release/event related API calls.
   *
   * @return the KeyApi instance owned by this SoundTouchApi instance
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

  public SoundTouchApiClient getSoundTouchApiClient() {
    return soundTouchApiClient;
  }


}
