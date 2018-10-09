package org.soundtouch4j;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.soundtouch4j.common.ContentItem;
import org.soundtouch4j.common.ErrorEnum;
import org.soundtouch4j.common.SourceEnum;
import com.google.api.client.http.HttpStatusCodes;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.testing.http.HttpTesting;
import com.google.api.client.testing.http.MockHttpTransport;
import com.google.api.client.testing.http.MockLowLevelHttpRequest;
import com.google.api.client.testing.http.MockLowLevelHttpResponse;
import com.google.api.client.xml.Xml;

public class SelectApiTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(SelectApiTest.class);

  @Test
  public void test01_withToAux() {
    LOGGER.info("test01_withToAux started");
    final HttpTransport transport = new MockHttpTransport() {
      @Override
      public LowLevelHttpRequest buildRequest(final String method, final String url) {
        return new MockLowLevelHttpRequest() {
          @Override
          public LowLevelHttpResponse execute() {
            final MockLowLevelHttpResponse result = new MockLowLevelHttpResponse();
            result.setContentType(Xml.MEDIA_TYPE);
            result.setContent("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n<status>/select</status>");
            return result;
          }
        };
      }
    };


    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    try {
      soundTouchApi.getSelectApi()
          .select(new ContentItem(SourceEnum.AUX, "AUX"));


    } catch (final SoundTouchApiException e) {
      LOGGER.error("Unable to get the basic information: {}", e.getMessage());
      fail();
    }
    LOGGER.info("test01_withToAux started");
  }

  @Test
  public void test02_withToUnkown() {
    LOGGER.info("test02_withToUnkown started");
    final HttpTransport transport = new MockHttpTransport() {
      @Override
      public LowLevelHttpRequest buildRequest(final String method, final String url) {
        return new MockLowLevelHttpRequest() {
          @Override
          public LowLevelHttpResponse execute() {
            final MockLowLevelHttpResponse result = new MockLowLevelHttpResponse();
            result.setContentType(Xml.MEDIA_TYPE);
            result.setStatusCode(HttpStatusCodes.STATUS_CODE_SERVER_ERROR);
            result.setContent("<?xml version=\"1.0\" encoding=\"UTF-8\" ?><errors deviceID=\"C8DF84AE0B6E\"><error value=\"1045\" name=\"SELECT_ITEM_IN_WRONG_STATE\" " +
                "severity=\"Unknown\">1045</error></errors>");
            return result;
          }
        };
      }
    };


    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    try {
      soundTouchApi.getSelectApi()
          .select(new ContentItem(SourceEnum.INTERNET_RADIO, ""));
      fail();
    } catch (final SoundTouchApiException e) {
      LOGGER.error("Unable to get the basic information: {}", e.getMessage());
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
    LOGGER.info("test02_withToUnkown started");
  }

}
