package org.soundtouch4j;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.soundtouch4j.common.ContentItem;
import org.soundtouch4j.common.ErrorEnum;
import org.soundtouch4j.common.SourceEnum;
import org.soundtouch4j.select.SelectResponse;
import com.google.api.client.http.HttpStatusCodes;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.testing.http.HttpTesting;
import com.google.api.client.testing.http.MockHttpTransport;
import com.google.api.client.testing.http.MockLowLevelHttpRequest;
import com.google.api.client.testing.http.MockLowLevelHttpResponse;
import com.google.api.client.xml.Xml;
import junit.framework.TestCase;

public class SelectApiTest extends TestCase {

  private static final Logger LOGGER = LoggerFactory.getLogger(SelectApiTest.class);

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
      final SelectResponse response = soundTouchApi.getSelectApi()
          .select(new ContentItem(SourceEnum.AUX, "AUX"));

      assertEquals(response.getStatus(), "/select");
      LOGGER.info("Response: {}", response);
    } catch (final SoundTouchApiException e) {
      LOGGER.error("Unable to get the basic information: {}", e.getMessage());
      Assert.fail();
    }
    LOGGER.info("test01_withToAux started");
  }


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
            result.setContent("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" + "<errors deviceID=\"C8DF84AE0B6E\">\n" + "    <error value=\"1045\" " + "name" +
                "=\"SELECT_ITEM_IN_WRONG_STATE\" severity=\"Unknown\">1045</error>\n" + "</errors>");
            return result;
          }
        };
      }
    };


    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    try {
      soundTouchApi.getSelectApi()
          .select(new ContentItem(SourceEnum.INTERNET_RADIO, ""));
      Assert.fail();
    } catch (final SoundTouchApiException e) {
      LOGGER.error("Unable to get the basic information: {}", e.getMessage());
      Assert.assertEquals(e.getHttpStatus(), HttpStatusCodes.STATUS_CODE_SERVER_ERROR);
      Assert.assertEquals(e.getError()
          .getErrorList()
          .get(0)
          .getName(), ErrorEnum.SELECT_ITEM_IN_WRONG_STATE);
      Assert.assertEquals(e.getError()
          .getErrorList()
          .get(0)
          .getSeverity(), "Unknown");
      Assert.assertEquals(e.getError()
          .getErrorList()
          .get(0)
          .getValue(), 1045);
    }
    LOGGER.info("test02_withToUnkown started");
  }

}
