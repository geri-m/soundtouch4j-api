package org.soundtouch4j.source;

import java.util.ArrayList;
import java.util.List;
import org.soundtouch4j.SoundTouchApi;
import org.soundtouch4j.SoundTouchApiException;
import org.soundtouch4j.common.AbstractApi;
import org.soundtouch4j.common.SourceEnum;

public class SourceApi extends AbstractApi {

  private static final String PATH_FOR_API = "sources";

  public SourceApi(final SoundTouchApi soundTouchApi) {
    super(soundTouchApi);
  }


  /**
   * Method to get the Sources of the  the Sound Touch
   *
   * @return SourceResponse Response from the Speaker with all the relevant Sources
   * @throws SoundTouchApiException is thrown in case the communication to the speaker failed or the Speaker did response
   */

  public SourceResponse getSources() throws SoundTouchApiException {
    return soundTouchApi.getSoundTouchApiClient()
        .get(PATH_FOR_API, SourceResponse.class);

  }

  /**
   * Method of fetch only sources of a dedicated {@link SourceEnum}
   *
   * @param type Type of Sources to filter for
   * @return List of {@link SourceItem} with the given type
   * @throws SoundTouchApiException is thrown in case the communication to the speaker failed or the Speaker did response
   */

  public List<SourceItem> getSourcesByType(final SourceEnum type) throws SoundTouchApiException {
    final List<SourceItem> result = new ArrayList<SourceItem>();
    for (final SourceItem sourceItem : getSources().getSourceItems()) {
      if (sourceItem.getSource()
          .equals(type)) {
        result.add(sourceItem);
      }
    }
    return result;
  }

}
