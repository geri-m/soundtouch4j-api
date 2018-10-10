package org.soundtouch4j;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.soundtouch4j.info.InfoResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.testing.http.HttpTesting;


public class NameApiTest {


  @Test
  public void setName() throws SoundTouchApiException {

    final String xml =
        "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><info deviceID=\"C8DF84AE0B6E\"><name>Geralds Box</name><type>SoundTouch 20</type><margeAccountUUID>6990307" +
            "</margeAccountUUID><components><component><componentCategory>SCM</componentCategory><softwareVersion>19.0.5.42017.2794643 epdbuild.trunk.cepeswbld02.2018" + "-04" + "-25T18:23:30</softwareVersion><serialNumber>F8124895404720048620440</serialNumber></component><component><componentCategory>PackagedProduct" + "</componentCategory" + "><serialNumber>069428P81639976AE</serialNumber></component></components><margeURL>https://streaming.bose.com</margeURL><networkInfo " + "type=\"SCM\"><macAddress" + ">C8DF84AE0B6E</macAddress><ipAddress>192.168.178.61</ipAddress></networkInfo><networkInfo type=\"SMSC\"><macAddress>C8DF84615084" + "</macAddress><ipAddress>192.168" + ".178.61</ipAddress></networkInfo><moduleType>sm2</moduleType><variant>spotty</variant><variantMode>normal</variantMode" + "><countryCode>GB</countryCode><regionCode" + ">GB</regionCode></info>";
    final HttpTransport transport = Const.getHttpTransportFromString(xml);

    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);

    final InfoResponse response = soundTouchApi.getNameApi()
        .setName("Geralds Box");
    assertEquals("C8DF84AE0B6E", response.getDeviceID());
    assertEquals("Geralds Box", response.getName());
    assertEquals("InfoResponse{deviceID='C8DF84AE0B6E', name='Geralds Box', type='SoundTouch 20', components={component=[Component{softwareVersion='19.0.5.42017.2794643 " +
        "epdbuild.trunk.cepeswbld02.2018-04-25T18:23:30', serialNumber='F8124895404720048620440'}, Component{softwareVersion='null', serialNumber='069428P81639976AE'}]}, " +
        "networkInfo=[NetworkInfo{type=SCM, macAddress='C8DF84AE0B6E', ipAddress='192.168.178.61'}, NetworkInfo{type=SMSC, macAddress='C8DF84615084', ipAddress='192.168.178" +
        ".61'}]}", response.toString());

  }


}
