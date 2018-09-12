package org.soundtouch4j.nowplaying;

import com.google.api.client.util.Value;

public enum ArtImageStatusEnum {

  @Value INVALID,

  @Value SHOW_DEFAULT_IMAGE,

  @Value DOWNLOADING,

  @Value IMAGE_PRESENT;

}
