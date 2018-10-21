package org.soundtouch4j;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import org.junit.jupiter.api.Test;
import org.soundtouch4j.info.InfoResponse;
import org.soundtouch4j.info.NetworkInfoTypeEnum;
import com.google.api.client.http.xml.XmlHttpContent;
import com.google.api.client.util.Key;
import com.google.api.client.xml.XmlObjectParser;

public class XmlParsingReferenceTest {


  @Test
  public void serializingXMLTest() throws IOException {
    final XmlHttpContent xmlContentForPostCall = new XmlHttpContent(SoundTouchApiClient.DICTIONARY, "AnyType", new AnyType());
    final OutputStream os = new ByteArrayOutputStream();
    xmlContentForPostCall.writeTo(os);
    assertEquals("<?xml version=\"1.0\"?><AnyType xmlns=\"\" />", os.toString());
  }

  @Test
  public void deserializingXMLTest() throws IOException {
    final String input = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><info deviceID=\"C8DF84AE0B6E\"><name>SoundTouch 20</name><type>SoundTouch 20</type><margeAccountUUID" +
        ">6990307</margeAccountUUID><components><component><componentCategory>SCM</componentCategory><softwareVersion>19.0.5.42017.2794643 epdbuild.trunk.cepeswbld02" + ".2018" +
        "-04-25T18:23:30</softwareVersion><serialNumber>F8124895404720048620440</serialNumber></component><component><componentCategory>PackagedProduct" + "</componentCategory" +
        "><serialNumber>069428P81639976AE</serialNumber></component></components><margeURL>https://streaming.bose.com</margeURL><networkInfo type=\"SCM" + "\"><macAddress" +
        ">C8DF84AE0B6E</macAddress><ipAddress>192.168.178.61</ipAddress></networkInfo><networkInfo type=\"SMSC\"><macAddress>C8DF84615084</macAddress" + "><ipAddress>192.168.178" +
        ".61</ipAddress></networkInfo><moduleType>sm2</moduleType><variant>spotty</variant><variantMode>normal</variantMode><countryCode>GB" + "</countryCode><regionCode>GB" +
        "</regionCode></info>";

    final XmlObjectParser parser = new XmlObjectParser(SoundTouchApiClient.DICTIONARY);
    final InfoResponse response = parser.parseAndClose(new StringReader(input), InfoResponse.class);
    assertEquals("C8DF84AE0B6E", response.getDeviceID());
    assertEquals("SoundTouch 20", response.getName());
    assertEquals("SoundTouch 20", response.getType());
    assertEquals(response.getComponents()
        .size(), 2);
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
  }

  public static class AnyType {
    @Key("@attr")
    public String attr;
    @Key
    public String elem;
    @Key
    public String rep;
    @Key
    public String value;
  }

}
