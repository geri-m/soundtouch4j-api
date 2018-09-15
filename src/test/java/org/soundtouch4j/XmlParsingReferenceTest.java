package org.soundtouch4j;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.soundtouch4j.info.InfoResponse;
import org.soundtouch4j.info.NetworkInfoTypeEnum;
import com.google.api.client.http.xml.XmlHttpContent;
import com.google.api.client.util.Key;
import com.google.api.client.xml.XmlNamespaceDictionary;
import com.google.api.client.xml.XmlObjectParser;
import junit.framework.TestCase;

public class XmlParsingReferenceTest extends TestCase {

  private static final Logger LOGGER = LoggerFactory.getLogger(XmlParsingReferenceTest.class);
  public static final XmlNamespaceDictionary XML_NAMESPACE_DICTIONARY = new XmlNamespaceDictionary().set("", "");

  public void test01_serializingXMLTest() throws IOException {
    LOGGER.info("test01_serializingXMLTest started");

    final XmlHttpContent xmlContentForPostCall = new XmlHttpContent(XML_NAMESPACE_DICTIONARY, "AnyType", new AnyType());

    final OutputStream os = new ByteArrayOutputStream();
    xmlContentForPostCall.writeTo(os);
    assertEquals("<?xml version=\"1.0\"?><AnyType xmlns=\"\" />", os.toString());
    LOGGER.info("test01_serializingXMLTest passed");
  }

  public void test02_deserializingXMLTest() throws IOException {
    LOGGER.info("test02_deserializingXMLTest started");



    final String input =
        "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><info deviceID=\"C8DF84AE0B6E\"><name>SoundTouch 20</name><type>SoundTouch " + "20</type><margeAccountUUID" + ">6990307" +
            "</margeAccountUUID><components><component><componentCategory>SCM</componentCategory><softwareVersion>19.0.5.42017.2794643 epdbuild" + ".trunk.cepeswbld02" + ".2018" +
            "-04-25T18:23:30</softwareVersion><serialNumber>F8124895404720048620440</serialNumber></component><component><componentCategory>PackagedProduct" +
            "</componentCategory><serialNumber>069428P81639976AE</serialNumber></component></components><margeURL>https://streaming.bose.com</margeURL><networkInfo " + "type" +
            "=\"SCM" + "\"><macAddress>C8DF84AE0B6E</macAddress><ipAddress>192.168.178.61</ipAddress></networkInfo><networkInfo " + "type=\"SMSC\"><macAddress>C8DF84615084" +
            "</macAddress" + "><ipAddress>192.168.178" + ".61</ipAddress></networkInfo><moduleType>sm2</moduleType><variant>spotty</variant><variantMode>normal</variantMode" +
            "><countryCode>GB" + "</countryCode><regionCode>GB" + "</regionCode></info>";

    final XmlObjectParser parser = new XmlObjectParser(XML_NAMESPACE_DICTIONARY);
    final InfoResponse response = parser.parseAndClose(new StringReader(input), InfoResponse.class);
    LOGGER.info(response.toString());
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
        .size(), 2);
    assertEquals(response.getNetworkInfo()
        .get(0)
        .getIpAddress(), "192.168.178.61");
    assertEquals(response.getNetworkInfo()
        .get(0)
        .getMacAddress(), "C8DF84AE0B6E");
    assertEquals(response.getNetworkInfo()
        .get(0)
        .getType(), NetworkInfoTypeEnum.SCM);
    assertEquals(response.getNetworkInfo()
        .get(1)
        .getMacAddress(), "C8DF84615084");
    assertEquals(response.getNetworkInfo()
        .get(1)
        .getIpAddress(), "192.168.178.61");
    assertEquals(response.getNetworkInfo()
        .get(1)
        .getType(), NetworkInfoTypeEnum.SMSC);

    LOGGER.info("test02_deserializingXMLTest passed");
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
