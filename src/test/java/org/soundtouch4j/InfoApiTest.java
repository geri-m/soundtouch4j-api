package org.soundtouch4j;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.soundtouch4j.info.InfoResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.testing.http.HttpTesting;
import com.google.api.client.testing.http.MockHttpTransport;
import com.google.api.client.testing.http.MockLowLevelHttpRequest;
import com.google.api.client.testing.http.MockLowLevelHttpResponse;
import com.google.api.client.xml.Xml;
import junit.framework.TestCase;

public class InfoApiTest extends TestCase {

  private static final Logger LOGGER = LoggerFactory.getLogger(InfoApiTest.class);

  public void test01_getInfo() {
    LOGGER.info("test01_getInfo started");
    final HttpTransport transport = new MockHttpTransport() {
      @Override
      public LowLevelHttpRequest buildRequest(final String method, final String url) {
        return new MockLowLevelHttpRequest() {
          @Override
          public LowLevelHttpResponse execute() {
            final MockLowLevelHttpResponse result = new MockLowLevelHttpResponse();
            result.setContentType(Xml.MEDIA_TYPE);
            result.setContent("<?xml version=\"1.0\" encoding=\"UTF-8\" ?><info deviceID=\"C8DF84AE0B6E\"><name>SoundTouch 20</name><type>SoundTouch " + "20</type" +
                "><margeAccountUUID>6990307</margeAccountUUID><components><component><componentCategory>SCM</componentCategory><softwareVersion>19.0.5.42017.2794643 " +
                "epdbuild.trunk.cepeswbld02.2018-04-25T18:23:30</softwareVersion><serialNumber>F8124895404720048620440</serialNumber></component><component><componentCategory" + ">PackagedProduct</componentCategory><serialNumber>069428P81639976AE</serialNumber></component></components><margeURL>https://streaming.bose" + ".com</margeURL><networkInfo type=\"SCM\"><macAddress>C8DF84AE0B6E</macAddress><ipAddress>192.168.178.61</ipAddress></networkInfo><networkInfo " + "type=\"SMSC\"><macAddress>C8DF84615084</macAddress><ipAddress>192.168.178" + ".61</ipAddress></networkInfo><moduleType>sm2</moduleType><variant>spotty</variant><variantMode>normal</variantMode><countryCode>GB</countryCode><regionCode>GB" + "</regionCode></info>");
            return result;
          }
        };
      }
    };

    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    try {
      final InfoResponse response = soundTouchApi.getInfoApi()
          .getInfo();
      assertEquals(response.getDeviceID(), "C8DF84AE0B6E");
      assertEquals(response.getName(), "SoundTouch 20");
      assertEquals(response.getType(), "SoundTouch 20");
      assertEquals(response.getComponents()
          .size(), 2);
      assertEquals(response.getComponents()
          .get(0)
          .getSoftwareVersion(), "19.0.5.42017.2794643 epdbuild.trunk.cepeswbld02.2018-04-25T18:23:30");
      assertEquals(response.getComponents()
          .get(0)
          .getSerialNumber(), "F8124895404720048620440");
      assertNull(response.getComponents()
          .get(1)
          .getSoftwareVersion());
      assertEquals(response.getComponents()
          .get(1)
          .getSerialNumber(), "069428P81639976AE");
      assertEquals(response.getNetworkInfo()
          .getIpAddress(), "192.168.178.61");
      assertEquals(response.getNetworkInfo()
          .getMacAddress(), "C8DF84615084");

    } catch (final SoundTouchApiException e) {
      LOGGER.error("Unable to get the basic information: {}", e.getMessage());
      Assert.fail();
    }
    LOGGER.info("test01_getInfo started");
  }

}
