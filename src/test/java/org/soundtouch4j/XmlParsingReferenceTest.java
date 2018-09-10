package org.soundtouch4j;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xmlpull.v1.XmlPullParserException;
import com.google.api.client.http.xml.XmlHttpContent;
import com.google.api.client.util.Key;
import com.google.api.client.xml.XmlNamespaceDictionary;

public class XmlParsingReferenceTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(XmlParsingReferenceTest.class);

  @Test
  public void test01_serializingXMLTest() throws XmlPullParserException, IOException {
    LOGGER.info("test01_serializingXMLTest started");

    final XmlNamespaceDictionary dict = new XmlNamespaceDictionary().set("", "");
    final XmlHttpContent xmlContentForPostCall = new XmlHttpContent(dict, "AnyType", new AnyType());

    final OutputStream os = new ByteArrayOutputStream();
    xmlContentForPostCall.writeTo(os);
    LOGGER.info(os.toString());

    LOGGER.info("test01_serializingXMLTest passed");
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
