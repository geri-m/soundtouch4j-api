package org.soundtouch4j;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import org.soundtouch4j.info.InfoResponse;
import org.soundtouch4j.info.NetworkInfoTypeEnum;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.testing.http.HttpTesting;

public class InfoApiTest {

  @Test
  public void getInfo() throws SoundTouchApiException {
    final String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><info deviceID=\"C8DF84AE0B6E\"><name>SoundTouch 20</name><type>SoundTouch 20</type" + "><margeAccountUUID" +
        ">6990307</margeAccountUUID><components><component><componentCategory>SCM</componentCategory><softwareVersion>19.0.5.42017.2794643 epdbuild.trunk.cepeswbld02" + ".2018" +
        "-04-25T18:23:30</softwareVersion><serialNumber>F8124895404720048620440</serialNumber></component><component><componentCategory>PackagedProduct</componentCategory" +
        "><serialNumber>069428P81639976AE</serialNumber></component></components><margeURL>https://streaming.bose.com</margeURL><networkInfo " + "type=\"SCM\"><macAddress" +
        ">C8DF84AE0B6E</macAddress><ipAddress>192.168.178.61</ipAddress></networkInfo><networkInfo " + "type=\"SMSC\"><macAddress>C8DF84615084</macAddress><ipAddress>192.168" +
        ".178" + ".61</ipAddress></networkInfo><moduleType>sm2</moduleType><variant>spotty</variant><variantMode>normal</variantMode><countryCode>GB</countryCode><regionCode>GB" + "</regionCode></info>";
    final HttpTransport transport = Const.getHttpTransportFromString(xml);

    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);

    final InfoResponse response = soundTouchApi.getInfoApi()
        .getInfo();
    assertEquals("C8DF84AE0B6E", response.getDeviceID());
    assertEquals("SoundTouch 20", response.getName());
    assertEquals("SoundTouch 20", response.getType());
    assertEquals(2, response.getComponents()
        .size());
    assertEquals("19.0.5.42017.2794643 epdbuild.trunk.cepeswbld02.2018-04-25T18:23:30", response.getComponents()
        .get(0)
        .getSoftwareVersion());
    assertEquals("F8124895404720048620440", response.getComponents()
        .get(0)
        .getSerialNumber());
    assertNull(response.getComponents()
        .get(1)
        .getSoftwareVersion());
    assertEquals("069428P81639976AE", response.getComponents()
        .get(1)
        .getSerialNumber());
    assertEquals(2, response.getNetworkInfo()
        .size());
    assertEquals("192.168.178.61", response.getNetworkInfo()
        .get(0)
        .getIpAddress());
    assertEquals("C8DF84AE0B6E", response.getNetworkInfo()
        .get(0)
        .getMacAddress());
    assertEquals(NetworkInfoTypeEnum.SCM, response.getNetworkInfo()
        .get(0)
        .getType());
    assertEquals("C8DF84615084", response.getNetworkInfo()
        .get(1)
        .getMacAddress());
    assertEquals("192.168.178.61", response.getNetworkInfo()
        .get(1)
        .getIpAddress());
    assertEquals(NetworkInfoTypeEnum.SMSC, response.getNetworkInfo()
        .get(1)
        .getType());

    assertEquals(19, response.getComponents()
        .get(0)
        .getVersion());
  }

  @Test
  public void getInfoBrokenVersion() throws SoundTouchApiException {
    final String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><info deviceID=\"C8DF84AE0B6E\"><name>SoundTouch 20</name><type>SoundTouch 20</type" + "><margeAccountUUID" +
        ">6990307</margeAccountUUID><components><component><componentCategory>SCM</componentCategory><softwareVersion>1x.0.5.42017.2794643 epdbuild.trunk.cepeswbld02" + ".2018" +
        "-04-25T18:23:30</softwareVersion><serialNumber>F8124895404720048620440</serialNumber></component><component><componentCategory>PackagedProduct</componentCategory" +
        "><serialNumber>069428P81639976AE</serialNumber></component></components><margeURL>https://streaming.bose.com</margeURL><networkInfo " + "type=\"SCM\"><macAddress" +
        ">C8DF84AE0B6E</macAddress><ipAddress>192.168.178.61</ipAddress></networkInfo><networkInfo " + "type=\"SMSC\"><macAddress>C8DF84615084</macAddress><ipAddress>192.168" +
        ".178" + ".61</ipAddress></networkInfo><moduleType>sm2</moduleType><variant>spotty</variant><variantMode>normal</variantMode><countryCode>GB</countryCode><regionCode>GB" + "</regionCode></info>";
    final HttpTransport transport = Const.getHttpTransportFromString(xml);

    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);

    final InfoResponse response = soundTouchApi.getInfoApi()
        .getInfo();
    assertEquals("1x.0.5.42017.2794643 epdbuild.trunk.cepeswbld02.2018-04-25T18:23:30", response.getComponents()
        .get(0)
        .getSoftwareVersion());

    try {
      response.getComponents()
          .get(0)
          .getVersion();
      fail();
    } catch (final Exception e) {
      assertEquals("Unable to parse Version String '1x.0.5.42017.2794643 epdbuild.trunk.cepeswbld02.2018-04-25T18:23:30'", e.getMessage());
    }
  }
}
