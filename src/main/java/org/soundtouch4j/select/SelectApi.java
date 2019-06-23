package org.soundtouch4j.select;

import org.soundtouch4j.SoundTouchApi;
import org.soundtouch4j.common.AbstractApi;
import org.soundtouch4j.common.ContentItem;

public class SelectApi extends AbstractApi {

  private static final String PATH_FOR_API = "select";

  public SelectApi(final SoundTouchApi soundTouchApi) {
    super(soundTouchApi);
  }


  /**
   * Method to get the Sources of the  the Sound Touch
   *
   * @param content The {@link ContentItem} to select on the Speaker
   */

  public void select(final ContentItem content) {
    soundTouchApi.getSoundTouchApiClient()
        .post(PATH_FOR_API, ContentItem.ELEMENT_NAME, content, SelectResponse.class);
  }
}