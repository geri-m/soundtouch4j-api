package org.soundtouch4j.common;

import com.google.api.client.util.Value;

/**
 * Enumeration fo the State of Playing of the SoundTouch
 */

public enum SourceEnum {

  // There are more Values to come. There are basically two states. Standby and "playing"

  @Value INTERNET_RADIO,
  @Value BLUETOOTH,
  @Value STANDBY,
  @Value AUX,
  @Value NOTIFICATION,
  @Value QPLAY,
  @Value UPNP,
  @Value SPOTIFY,
  @Value STORED_MUSIC_MEDIA_RENDERER,
  @Value ALEXA,
  @Value TUNEIN;

}
