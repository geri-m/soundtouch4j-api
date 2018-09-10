package org.soundtouch4j.nowplaying;

import com.google.api.client.util.Key;

/**
 * Content Item of the NowPlaying Response.
 */

public class NowPlayingContentItem {

  @Key("@source")
  private SourceEnum source;

  public NowPlayingContentItem() {

  }

  public SourceEnum getSource() {
    return source;
  }
}
