package org.soundtouch4j;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.soundtouch4j.common.SourceEnum;
import org.soundtouch4j.preset.PresetResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.testing.http.HttpTesting;

public class PresetsApiTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(PresetsApiTest.class);

  @Test
  public void getPresets() throws SoundTouchApiException {
    final String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><presets><preset id=\"1\" createdOn=\"1536426168\" updatedOn=\"1536426168\"><ContentItem source" +
        "=\"INTERNET_RADIO\" location=\"18298\" sourceAccount=\"\" isPresetable=\"true\"><itemName>ORF Hitradio Ö3</itemName><containerArt>http://item.radio456.com/007452" + "/logo/logo-18298.jpg</containerArt></ContentItem></preset><preset id=\"2\" createdOn=\"1536426180\" updatedOn=\"1536436135\"><ContentItem source=\"INTERNET_RADIO\"" + " location=\"80709\" sourceAccount=\"\" isPresetable=\"true\"><itemName>1 Pure EDM Radio</itemName><containerArt>http://item.radio456.com/007452/logo/logo-80709" + ".jpg</containerArt></ContentItem></preset><preset id=\"3\" createdOn=\"1536426219\" updatedOn=\"1536426219\"><ContentItem source=\"INTERNET_RADIO\" " + "location=\"8992\" sourceAccount=\"\" isPresetable=\"true\"><itemName>ENERGY 93.3 München</itemName><containerArt>http://item.radio456.com/007452/logo/logo-8992" + ".jpg</containerArt></ContentItem></preset><preset id=\"4\" createdOn=\"1536426197\" updatedOn=\"1536426197\"><ContentItem source=\"INTERNET_RADIO\" " + "location=\"3687\" sourceAccount=\"\" isPresetable=\"true\"><itemName>95.5 Charivari</itemName><containerArt>http://item.radio456.com/007452/logo/logo-3687" + ".jpg</containerArt></ContentItem></preset><preset id=\"5\" createdOn=\"1536427328\" updatedOn=\"1536427328\"><ContentItem source=\"INTERNET_RADIO\" " + "location=\"76484\" sourceAccount=\"\" isPresetable=\"true\"><itemName>Costa Del Mar - DeepHouse</itemName><containerArt>http://item.radio456.com/007452/logo/logo" + "-76484.jpg</containerArt></ContentItem></preset><preset id=\"6\" createdOn=\"1536427431\" updatedOn=\"1536427431\"><ContentItem source=\"INTERNET_RADIO\" " + "location=\"45289\" sourceAccount=\"\" isPresetable=\"true\"><itemName>Deep House Lounge</itemName><containerArt>http://item.radio456.com/007452/logo/logo-45289" +
        ".jpg</containerArt></ContentItem></preset></presets>";
    final HttpTransport transport = Const.getHttpTransportFromString(xml);

    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    final PresetResponse response = soundTouchApi.getPresetApi()
        .getPresets();

    assertEquals("PresetResponse{presetList=[Preset{id=1, createdOn=Sat Sep 08 19:02:48 CEST 2018, updatedOn=Sat Sep 08 19:02:48 CEST 2018, " +
        "contentItem=ContentItem{source=INTERNET_RADIO, isPresetable=true, location='18298', sourceAccount='', itemName='ORF Hitradio Ö3', containerArt='http://item.radio456" +
        ".com/007452/logo/logo-18298.jpg'}}, Preset{id=2, createdOn=Sat Sep 08 19:03:00 CEST 2018, updatedOn=Sat Sep 08 21:48:55 CEST 2018, " +
        "contentItem=ContentItem{source=INTERNET_RADIO, isPresetable=true, location='80709', sourceAccount='', itemName='1 Pure EDM Radio', containerArt='http://item.radio456" +
        ".com/007452/logo/logo-80709.jpg'}}, Preset{id=3, createdOn=Sat Sep 08 19:03:39 CEST 2018, updatedOn=Sat Sep 08 19:03:39 CEST 2018, " +
        "contentItem=ContentItem{source=INTERNET_RADIO, isPresetable=true, location='8992', sourceAccount='', itemName='ENERGY 93.3 München', containerArt='http://item.radio456" +
        ".com/007452/logo/logo-8992.jpg'}}, Preset{id=4, createdOn=Sat Sep 08 19:03:17 CEST 2018, updatedOn=Sat Sep 08 19:03:17 CEST 2018, " +
        "contentItem=ContentItem{source=INTERNET_RADIO, isPresetable=true, location='3687', sourceAccount='', itemName='95.5 Charivari', containerArt='http://item.radio456" +
        ".com/007452/logo/logo-3687.jpg'}}, Preset{id=5, createdOn=Sat Sep 08 19:22:08 CEST 2018, updatedOn=Sat Sep 08 19:22:08 CEST 2018, " +
        "contentItem=ContentItem{source=INTERNET_RADIO, isPresetable=true, location='76484', sourceAccount='', itemName='Costa Del Mar - DeepHouse', containerArt='http://item" +
        ".radio456.com/007452/logo/logo-76484.jpg'}}, Preset{id=6, createdOn=Sat Sep 08 19:23:51 CEST 2018, updatedOn=Sat Sep 08 19:23:51 CEST 2018, " +
        "contentItem=ContentItem{source=INTERNET_RADIO, isPresetable=true, location='45289', sourceAccount='', itemName='Deep House Lounge', containerArt='http://item.radio456" +
        ".com/007452/logo/logo-45289.jpg'}}]}", response.toString());
    assertEquals(6, response.getPresetList()
        .size());
    assertEquals(1, response.getPresetList()
        .get(0)
        .getId());
    assertEquals(1536426168 * 1000L, response.getPresetList()
        .get(0)
        .getCreatedOn()
        .getTime());
    assertEquals(1536426168 * 1000L, response.getPresetList()
        .get(0)
        .getUpdatedOn()
        .getTime());
    assertEquals(SourceEnum.INTERNET_RADIO, response.getPresetList()
        .get(0)
        .getContentItem()
        .getSource());
    assertEquals("ORF Hitradio Ö3", response.getPresetList()
        .get(0)
        .getContentItem()
        .getItemName());
    assertEquals("http://item.radio456.com/007452/logo/logo-18298.jpg", response.getPresetList()
        .get(0)
        .getContentItem()
        .getContainerArt());
    assertEquals("", response.getPresetList()
        .get(0)
        .getContentItem()
        .getSourceAccount());
    assertEquals("18298", response.getPresetList()
        .get(0)
        .getContentItem()
        .getLocation());
    assertEquals(true, response.getPresetList()
        .get(0)
        .getContentItem()
        .isPresetable());

    LOGGER.info("Response: {}", response);

  }

}

