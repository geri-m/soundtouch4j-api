package org.soundtouch4j.nowplaying;

import com.google.api.client.util.Value;

public enum StreamTypeEnum {

  @Value TRACK_ONDEMAND,

  @Value RADIO_STREAMING,

  @Value RADIO_TRACKS,

  @Value NO_TRANSPORT_CONTROLS
}
