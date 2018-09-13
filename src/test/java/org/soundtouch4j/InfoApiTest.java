package org.soundtouch4j;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.soundtouch4j.info.InfoResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.testing.http.HttpTesting;
import com.google.api.client.testing.http.MockHttpTransport;
import com.google.api.client.testing.http.MockLowLevelHttpRequest;
import com.google.api.client.testing.http.MockLowLevelHttpResponse;
import com.google.api.client.xml.Xml;
import junit.framework.TestCase;

public class InfoApiTest extends TestCase {

  private static final Logger LOGGER = LoggerFactory.getLogger(InfoApiTest.class);
  private static final String SAMPLE = "123\u05D9\u05e0\u05D9\u05D1";

  public void test01_fetchInfoFromSpeaker() {
    LOGGER.info("test01_fetchInfoFromSpeaker started");


    final URL boseEndpoint;
    try {
      boseEndpoint = new URL(Const.URL);
    } catch (final MalformedURLException e) {
      // LOGGER.error("Failed to Create URL from IP: {}. Msg: {}", services.get(0).getRemoteIp(), e.getMessage());
      Assert.fail();
      return;
    }

    final SoundTouchApi soundTouchApi = new SoundTouchApi(boseEndpoint, new NetHttpTransport());
    try {
      final InfoResponse response = soundTouchApi.getInfoApi()
          .getInfo();
      LOGGER.info("Info: '{}'", response);
    } catch (final SoundTouchApiException e) {
      LOGGER.error("Unable to get the basic information: {}", e.getMessage());
      Assert.fail();
      return;
    }

    LOGGER.info("test01_fetchInfoFromSpeaker passed");
  }

  public void test02_ParseAsString_utf8() {
    final HttpTransport transport = new MockHttpTransport() {
      @Override
      public LowLevelHttpRequest buildRequest(final String method, final String url) throws IOException {
        return new MockLowLevelHttpRequest() {
          @Override
          public LowLevelHttpResponse execute() {
            final MockLowLevelHttpResponse result = new MockLowLevelHttpResponse();
            result.setContentType(Xml.MEDIA_TYPE);
            result.setContent("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" + "<info deviceID=\"C8DF84AE0B6E\">\n" + "    <name>SoundTouch 20</name>\n" + "    <type>SoundTouch" +
                " 20</type>\n" + "    <margeAccountUUID>6990307</margeAccountUUID>\n" + "    <components>\n" + "        <component>\n" + "            <componentCategory>SCM" +
                "</componentCategory>\n" + "            <softwareVersion>19.0.5.42017.2794643 epdbuild.trunk.cepeswbld02.2018-04-25T18:23:30</softwareVersion>\n" + "            " +
                "<serialNumber>F8124895404720048620440</serialNumber>\n" + "        </component>\n" + "        <component>\n" + "            <componentCategory>PackagedProduct" +
                "</componentCategory>\n" + "            <serialNumber>069428P81639976AE</serialNumber>\n" + "        </component>\n" + "    </components>\n" + "    <margeURL" +
                ">https://streaming.bose.com</margeURL>\n" + "    <networkInfo type=\"SCM\">\n" + "        <macAddress>C8DF84AE0B6E</macAddress>\n" + "        <ipAddress>192.168" +
                ".178.61</ipAddress>\n" + "    </networkInfo>\n" + "    <networkInfo type=\"SMSC\">\n" + "        <macAddress>C8DF84615084</macAddress>\n" + "        " +
                "<ipAddress>192.168.178.61</ipAddress>\n" + "    </networkInfo>\n" + "    <moduleType>sm2</moduleType>\n" + "    <variant>spotty</variant>\n" + "    <variantMode" +
                ">normal</variantMode>\n" + "    <countryCode>GB</countryCode>\n" + "    <regionCode>GB</regionCode>\n" + "</info>");
            return result;
          }
        };
      }
    };

    final SoundTouchApi soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    try {
      final InfoResponse response = soundTouchApi.getInfoApi()
          .getInfo();
      LOGGER.info("Info: '{}'", response);
    } catch (final SoundTouchApiException e) {
      LOGGER.error("Unable to get the basic information: {}", e.getMessage());
      Assert.fail();
    }

  }

}
