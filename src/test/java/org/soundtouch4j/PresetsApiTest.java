package org.soundtouch4j;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.soundtouch4j.common.SourceEnum;
import org.soundtouch4j.preset.PresetResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.testing.http.HttpTesting;
import com.google.api.client.testing.http.MockHttpTransport;
import com.google.api.client.testing.http.MockLowLevelHttpRequest;
import com.google.api.client.testing.http.MockLowLevelHttpResponse;
import com.google.api.client.xml.Xml;
import junit.framework.TestCase;

public class PresetsApiTest extends TestCase {

  private static final Logger LOGGER = LoggerFactory.getLogger(SourceApiTest.class);

  public void test01_getPresets() {
    LOGGER.info("test01_getPresets started");
    final HttpTransport transport = new MockHttpTransport() {
      @Override
      public LowLevelHttpRequest buildRequest(final String method, final String url) {
        return new MockLowLevelHttpRequest() {
          @Override
          public LowLevelHttpResponse execute() {
            final MockLowLevelHttpResponse result = new MockLowLevelHttpResponse();
            result.setContentType(Xml.MEDIA_TYPE);
            result.setContent("<?xml version=\"1.0\" encoding=\"UTF-8\" ?><presets><preset id=\"1\" createdOn=\"1536426168\" updatedOn=\"1536426168\"><ContentItem " + "source" + "=\"INTERNET_RADIO\" location=\"18298\" sourceAccount=\"\" isPresetable=\"true\"><itemName>ORF Hitradio Ö3</itemName><containerArt>http://item.radio456" + ".com" + "/007452/logo/logo-18298.jpg</containerArt></ContentItem></preset><preset id=\"2\" createdOn=\"1536426180\" updatedOn=\"1536436135\"><ContentItem " + "source" + "=\"INTERNET_RADIO\" location=\"80709\" sourceAccount=\"\" isPresetable=\"true\"><itemName>1 Pure EDM Radio</itemName><containerArt>http://item.radio456" + ".com" + "/007452/logo/logo-80709.jpg</containerArt></ContentItem></preset><preset id=\"3\" createdOn=\"1536426219\" updatedOn=\"1536426219\"><ContentItem " + "source" + "=\"INTERNET_RADIO\" location=\"8992\" sourceAccount=\"\" isPresetable=\"true\"><itemName>ENERGY 93.3 München</itemName><containerArt>http://item.radio456" + ".com/007452/logo/logo-8992.jpg</containerArt></ContentItem></preset><preset id=\"4\" createdOn=\"1536426197\" updatedOn=\"1536426197\"><ContentItem " + "source" + "=\"INTERNET_RADIO\" location=\"3687\" sourceAccount=\"\" isPresetable=\"true\"><itemName>95.5 Charivari</itemName><containerArt>http://item.radio456" + ".com" + "/007452/logo/logo-3687.jpg</containerArt></ContentItem></preset><preset id=\"5\" createdOn=\"1536427328\" updatedOn=\"1536427328\"><ContentItem " + "source" + "=\"INTERNET_RADIO\" location=\"76484\" sourceAccount=\"\" isPresetable=\"true\"><itemName>Costa Del Mar - DeepHouse</itemName><containerArt>http://item" + ".radio456.com/007452/logo/logo-76484.jpg</containerArt></ContentItem></preset><preset id=\"6\" createdOn=\"1536427431\" updatedOn=\"1536427431\"><ContentItem " + "source=\"INTERNET_RADIO\" location=\"45289\" sourceAccount=\"\" isPresetable=\"true\"><itemName>Deep House Lounge</itemName><containerArt>http://item.radio456" + ".com/007452/logo/logo-45289.jpg</containerArt></ContentItem></preset></presets>");
            return result;
          }
        };
      }
    };

    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    try {
      final PresetResponse response = soundTouchApi.getPresetApi()
          .getPresets();

      assertEquals(response.getPresetList()
          .size(), 6);
      assertEquals(response.getPresetList()
          .get(0)
          .getId(), 1);
      assertEquals(response.getPresetList()
          .get(0)
          .getCreatedOn()
          .getTime(), 1536426168 * 1000L);
      assertEquals(response.getPresetList()
          .get(0)
          .getUpdatedOn()
          .getTime(), 1536426168 * 1000L);
      assertEquals(response.getPresetList()
          .get(0)
          .getContentItem()
          .getSource(), SourceEnum.INTERNET_RADIO);
      assertEquals(response.getPresetList()
          .get(0)
          .getContentItem()
          .getItemName(), "ORF Hitradio Ö3");
      assertEquals(response.getPresetList()
          .get(0)
          .getContentItem()
          .getContainerArt(), "http://item.radio456" + ".com/007452/logo/logo-18298.jpg");
      assertEquals(response.getPresetList()
          .get(0)
          .getContentItem()
          .getSourceAccount(), "");
      assertEquals(response.getPresetList()
          .get(0)
          .getContentItem()
          .getLocation(), "18298");
      assertEquals(response.getPresetList()
          .get(0)
          .getContentItem()
          .isPresetable(), true);

      LOGGER.info("Response: {}", response);


    } catch (final SoundTouchApiException e) {
      LOGGER.error("Unable to get the basic information: {}", e.getMessage());
      Assert.fail();
    }
    LOGGER.info("test01_getPresets started");
  }

}

