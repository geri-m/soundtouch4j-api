package org.soundtouch4j.nowplaying;


import org.soundtouch4j.common.Response;
import com.google.api.client.util.Key;

/**
 * Response Element form /nowPlaying. There are two States: STANDBY and "playing". For now, we only process the 'contentItem' Element.
 * <p>
 * XML Response from Speaker in Standby
 * </p>
 * <pre>
 * {@code
 * <?xml version="1.0" encoding="UTF-8" ?>
 * <nowPlaying deviceID="C8DF84AE0B6E" source="STANDBY">
 * <ContentItem source="STANDBY" isPresetable="true" />
 * </nowPlaying>
 * }
 * </pre>
 * <p>
 * XML Response from Speaker Playing (eg. INTERNET_RADIO)
 * </p>
 * <pre>
 * {@code
 * <?xml version="1.0" encoding="UTF-8" ?>
 * <nowPlaying deviceID="C8DF84AE0B6E" source="INTERNET_RADIO">
 * <ContentItem source="INTERNET_RADIO" location="45289" sourceAccount="" isPresetable="true">
 * <itemName>Deep House Lounge</itemName>
 * <containerArt>http://item.radio456.com/007452/logo/logo-45289.jpg</containerArt>
 * </ContentItem>
 * <track></track>
 * <artist></artist>
 * <album></album>
 * <stationName>Deep House Lounge</stationName>
 * <art artImageStatus="IMAGE_PRESENT">http://item.radio456.com/007452/logo/logo-45289.jpg</art>
 * <playStatus>PLAY_STATE</playStatus>
 * <description>MP3  128 kbps  Internet Only,  www.deephouselounge.com is an underground house and electronic dance music radio station / community.</description>
 * <stationLocation>Internet Only</stationLocation>
 * </nowPlaying>
 * }
 * </pre>
 *
 * <p>
 * XML Response from Speaker streaming Bluetooth
 * </p>
 * <pre>
 * {@code
 * <?xml version="1.0" encoding="UTF-8" ?>
 * <nowPlaying deviceID="C8DF84AE0B6E" source="BLUETOOTH" sourceAccount="">
 *     <ContentItem source="BLUETOOTH" location="" sourceAccount="" isPresetable="false">
 *         <itemName></itemName>
 *     </ContentItem>
 *     <track></track>
 *     <artist></artist>
 *     <album></album>
 *     <stationName></stationName>
 *     <art artImageStatus="SHOW_DEFAULT_IMAGE" />
 *     <playStatus>INVALID_PLAY_STATUS</playStatus>
 *     <connectionStatusInfo status="CONNECTING" deviceName="Geralds MacBook Pro" />
 * </nowPlaying>
 * }
 * </pre>
 */

public class NowPlayingResponse implements Response {

  @Key("@source")
  private SourceEnum source;

  @Key("@deviceID")
  private String deviceID;

  @Key("ContentItem")
  private ContentItem contentItem;

  @Key
  private String track;

  @Key
  private String artist;

  @Key
  private String album;

  @Key
  private String genre;

  @Key
  private String rating;

  @Key
  private String stationName;

  @Key
  private Art art;

  @Key
  private Time time;

  @Key
  private String skipEnabled;

  @Key
  private String skipPreviousEnabled;

  @Key
  private String favoriteEnabled;

  @Key
  private String isFavorite;

  @Key
  private String rateEnabled;

  // TODO: Work with ENUM Type here
  @Key
  private String playStatus;

  @Key
  private String description;

  @Key
  private String stationLocation;

  @Key
  private ConnectionStatusInfo connectionStatusInfo;

  public NowPlayingResponse() {

  }

  public ContentItem getContentItem() {
    return contentItem;
  }

  public String getTrack() {
    return track;
  }

  public String getArtist() {
    return artist;
  }

  public String getAlbum() {
    return album;
  }

  public String getStationName() {
    return stationName;
  }

  public Art getArt() {
    return art;
  }

  public String getPlayStatus() {
    return playStatus;
  }

  public String getDescription() {
    return description;
  }

  public String getStationLocation() {
    return stationLocation;
  }

  public SourceEnum getSource() {
    return source;
  }

  public String getDeviceID() {
    return deviceID;
  }

  public String getGenre() {
    return genre;
  }

  public String getRating() {
    return rating;
  }

  public Time getTime() {
    return time;
  }

  public boolean isSkipEnabled() {
    return skipEnabled != null;
  }

  public boolean isSkipPreviousEnabled() {
    return skipPreviousEnabled != null;
  }

  public boolean isFavoriteEnabled() {
    return favoriteEnabled != null;
  }

  public boolean isIsFavorite() {
    return isFavorite != null;
  }

  public boolean isRateEnabled() {
    return rateEnabled != null;
  }

  public ConnectionStatusInfo getConnectionStatusInfo() {
    return connectionStatusInfo;
  }


  @Override
  public String toString() {
    return "NowPlayingResponse{" + "source=" + source + ", deviceID='" + deviceID + '\'' + ", contentItem=" + contentItem + ", track='" + track + '\'' + ", artist='" + artist + '\'' + ", album='" + album + '\'' + ", genre='" + genre + '\'' + ", rating='" + rating + '\'' + ", stationName='" + stationName + '\'' + ", art=" + art + ", time=" + time + ", skipEnabled=" + isSkipEnabled() + ", skipPreviousEnabled=" + isSkipPreviousEnabled() + ", favoriteEnabled=" + favoriteEnabled + ", isFavorite=" + isIsFavorite() + ", rateEnabled=" + rateEnabled + ", playStatus=" + playStatus + ", description='" + description + '\'' + ", stationLocation='" + stationLocation + '\'' + ", connectionStatusInfo=" + connectionStatusInfo + '}';
  }
}
