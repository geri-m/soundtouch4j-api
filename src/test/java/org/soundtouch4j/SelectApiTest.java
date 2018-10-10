package org.soundtouch4j;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import org.soundtouch4j.common.ContentItem;
import org.soundtouch4j.common.ErrorEnum;
import org.soundtouch4j.common.SourceEnum;
import com.google.api.client.http.HttpStatusCodes;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.testing.http.HttpTesting;

public class SelectApiTest {

  @Test
  public void selectWithSwitchToAuxSuccessful() throws SoundTouchApiException {
    final String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n<status>/select</status>";
    final HttpTransport transport = Const.getHttpTransportFromString(xml);
    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);

    soundTouchApi.getSelectApi()
        .select(new ContentItem(SourceEnum.AUX, "AUX"));
  }

  @Test
  public void selectWithSwitchToUnkown() {
    final String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><errors deviceID=\"C8DF84AE0B6E\"><error value=\"1045\" name=\"SELECT_ITEM_IN_WRONG_STATE\" severity" +
        "=\"Unknown\">1045</error></errors>";
    final HttpTransport transport = Const.getHttpTransportFromString(xml, HttpStatusCodes.STATUS_CODE_SERVER_ERROR);
    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    try {
      soundTouchApi.getSelectApi()
          .select(new ContentItem(SourceEnum.INTERNET_RADIO, ""));
      fail();
    } catch (final SoundTouchApiException e) {
      assertEquals(HttpStatusCodes.STATUS_CODE_SERVER_ERROR, e.getHttpStatus());
      assertEquals(ErrorEnum.SELECT_ITEM_IN_WRONG_STATE, e.getError()
          .getErrorList()
          .get(0)
          .getName());
      assertEquals("Unknown", e.getError()
          .getErrorList()
          .get(0)
          .getSeverity());
      assertEquals(1045, e.getError()
          .getErrorList()
          .get(0)
          .getValue());
      assertEquals("1045", e.getError()
          .getErrorList()
          .get(0)
          .getText());
    }
  }
}
