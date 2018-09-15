package org.soundtouch4j;

import java.io.IOException;
import java.io.StringReader;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.soundtouch4j.common.ContentItem;
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
import com.google.api.client.xml.XmlObjectParser;
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
            result.setContent("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" + "<status>/select</status>");
            return result;
          }
        };
      }
    };
    final XmlObjectParser parser = new XmlObjectParser(XmlParsingReferenceTest.XML_NAMESPACE_DICTIONARY);
    final String input = "<ContentItem source=\"AUX\" sourceAccount=\"AUX\"></ContentItem>";

    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    try {
      final ContentItem content = parser.parseAndClose(new StringReader(input), ContentItem.class);
      final SelectResponse response = soundTouchApi.getSelectApi()
          .select(content);

      assertEquals(response.getStatus(), "/select");

      LOGGER.info("Response: {}", response);

    } catch (final SoundTouchApiException e) {
      LOGGER.error("Unable to get the basic information: {}", e.getMessage());
      Assert.fail();
    } catch (final IOException e) {
      LOGGER.error("Error Parsing XML: {}", e.getMessage());
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
            result.setContent("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" + "<errors deviceID=\"C8DF84AE0B6E\">\n" + "    <error value=\"1045\" " +
                "name=\"SELECT_ITEM_IN_WRONG_STATE\" severity=\"Unknown\">1045</error>\n" + "</errors>");
            return result;
          }
        };
      }
    };
    final XmlObjectParser parser = new XmlObjectParser(XmlParsingReferenceTest.XML_NAMESPACE_DICTIONARY);
    final String input = "<ContentItem source=\"INTERNET_RADIO\" sourceAccount=\"\"></ContentItem>";

    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    try {
      final ContentItem content = parser.parseAndClose(new StringReader(input), ContentItem.class);
      soundTouchApi.getSelectApi()
          .select(content);
      Assert.fail();
    } catch (final SoundTouchApiException e) {
      LOGGER.error("Unable to get the basic information: {}", e.getMessage());
      Assert.assertEquals(e.getHttpStatus(), HttpStatusCodes.STATUS_CODE_SERVER_ERROR);
    } catch (final IOException e) {
      LOGGER.error("Error Parsing XML: {}", e.getMessage());
      Assert.fail();
    }
    LOGGER.info("test02_withToUnkown started");
  }

}
