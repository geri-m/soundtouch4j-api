package org.soundtouch4j;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.soundtouch4j.zone.Zone;
import org.soundtouch4j.zone.ZoneMember;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.testing.http.HttpTesting;
import com.google.api.client.testing.http.MockHttpTransport;
import com.google.api.client.testing.http.MockLowLevelHttpRequest;
import com.google.api.client.testing.http.MockLowLevelHttpResponse;
import com.google.api.client.xml.Xml;


public class ZoneApiTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(ZoneApiTest.class);

  @Test
  public void test01_getZoneEmpty() {
    LOGGER.info("test01_getZoneEmpty started");
    final HttpTransport transport = new MockHttpTransport() {
      @Override
      public LowLevelHttpRequest buildRequest(final String method, final String url) {
        return new MockLowLevelHttpRequest() {
          @Override
          public LowLevelHttpResponse execute() {
            final MockLowLevelHttpResponse result = new MockLowLevelHttpResponse();
            result.setContentType(Xml.MEDIA_TYPE);
            result.setContent("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" + "<zone />");
            return result;
          }
        };
      }
    };

    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    try {
      soundTouchApi.getZoneApi()
          .getZone();

    } catch (final SoundTouchApiException e) {
      LOGGER.error("Unable to get the basic information: {}", e.getMessage());
      fail();
    }
    LOGGER.info("test01_getZoneEmpty completed");
  }

  @Test
  public void test02_getZoneList() {
    LOGGER.info("test02_getZoneList started");
    final HttpTransport transport = new MockHttpTransport() {
      @Override
      public LowLevelHttpRequest buildRequest(final String method, final String url) {
        return new MockLowLevelHttpRequest() {
          @Override
          public LowLevelHttpResponse execute() {
            final MockLowLevelHttpResponse result = new MockLowLevelHttpResponse();
            result.setContentType(Xml.MEDIA_TYPE);
            result.setContent("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" + "<zone master=\"C8DF84AE0B6E\">\n" + "    <member ipaddress=\"192.168.178" + ".61\">C8DF84AE0B6E" + "</member>\n" + "</zone>");
            return result;
          }
        };
      }
    };

    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    try {

      final Zone zone = soundTouchApi.getZoneApi()
          .getZone();

      assertEquals("C8DF84AE0B6E", zone.getDeviceID());
      assertEquals("192.168.178.61", zone.getMembers()
          .get(0)
          .getIpAddress());
      assertEquals("C8DF84AE0B6E", zone.getMembers()
          .get(0)
          .getMacAddress());
    } catch (final SoundTouchApiException e) {
      LOGGER.error("Unable to get the basic information: {}", e.getMessage());
      fail();
    }
    LOGGER.info("test02_getZoneList completed");
  }

  @Test
  public void test03_setZone() {
    LOGGER.info("test03_setZone started");
    final HttpTransport transport = new MockHttpTransport() {
      @Override
      public LowLevelHttpRequest buildRequest(final String method, final String url) {
        return new MockLowLevelHttpRequest() {
          @Override
          public LowLevelHttpResponse execute() {
            final MockLowLevelHttpResponse result = new MockLowLevelHttpResponse();
            result.setContentType(Xml.MEDIA_TYPE);
            result.setContent("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" + "<status>/setZone</status>");
            return result;
          }
        };
      }
    };

    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    try {
      final Zone zone = new Zone("C8DF84AE0B6E");
      zone.addMember(new ZoneMember("192.168.178.61", "C8DF84AE0B6E"));
      soundTouchApi.getZoneApi()
          .setZone(new Zone());

    } catch (final SoundTouchApiException e) {
      LOGGER.error("Unable to get the basic information: {}", e.getMessage());
      fail();
    }
    LOGGER.info("test03_setZone completed");
  }

  @Test
  public void test04_addZoneSlave() {
    LOGGER.info("test04_addZoneSlave started");
    final HttpTransport transport = new MockHttpTransport() {
      @Override
      public LowLevelHttpRequest buildRequest(final String method, final String url) {
        return new MockLowLevelHttpRequest() {
          @Override
          public LowLevelHttpResponse execute() {
            final MockLowLevelHttpResponse result = new MockLowLevelHttpResponse();
            result.setContentType(Xml.MEDIA_TYPE);
            result.setContent("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n<status>/addZoneSlave</status>");
            return result;
          }
        };
      }
    };

    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    try {
      final Zone zone = new Zone("C8DF84AE0B6E");
      zone.addMember(new ZoneMember("192.168.178.61", "C8DF84AE0B6E"));
      soundTouchApi.getZoneApi()
          .addZoneSlave(new Zone());

    } catch (final SoundTouchApiException e) {
      LOGGER.error("Unable to get the basic information: {}", e.getMessage());
      fail();
    }
    LOGGER.info("test04_addZoneSlave completed");
  }

  @Test
  public void test05_addZoneSlaveBrokenResponse() {
    LOGGER.info("test05_addZoneSlaveBrokenResponse started");
    final HttpTransport transport = Const.getBrokenResponse();

    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    try {
      final Zone zone = new Zone("C8DF84AE0B6E");
      zone.addMember(new ZoneMember("192.168.178.61", "C8DF84AE0B6E"));
      soundTouchApi.getZoneApi()
          .addZoneSlave(new Zone());
      fail();
    } catch (final SoundTouchApiException e) {
      LOGGER.error("Unable to get the basic information: {}", e.getMessage());

    }
    LOGGER.info("test05_addZoneSlaveBrokenResponse completed");
  }

  @Test
  public void test06_addZoneSlaveIncorrectResponse() {
    LOGGER.info("test05_addZoneSlaveBrokenResponse started");
    final HttpTransport transport = Const.getIncorrectStatusResponse("NOTAaddZoneSlave");

    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    try {
      final Zone zone = new Zone("C8DF84AE0B6E");
      zone.addMember(new ZoneMember("192.168.178.61", "C8DF84AE0B6E"));
      soundTouchApi.getZoneApi()
          .addZoneSlave(new Zone());
      fail();
    } catch (final SoundTouchApiException e) {
      LOGGER.error("Unable to get the basic information: {}", e.getMessage());
      assertEquals("Invalid Response from Speaker. Response was '/NOTAaddZoneSlave'", e.getMessage());
    }
    LOGGER.info("test06_addZoneSlaveIncorrectResponse completed");
  }

  @Test
  public void test07_removeZoneSlave() {
    LOGGER.info("test04_addZoneSlave started");
    final HttpTransport transport = new MockHttpTransport() {
      @Override
      public LowLevelHttpRequest buildRequest(final String method, final String url) {
        return new MockLowLevelHttpRequest() {
          @Override
          public LowLevelHttpResponse execute() {
            final MockLowLevelHttpResponse result = new MockLowLevelHttpResponse();
            result.setContentType(Xml.MEDIA_TYPE);
            result.setContent("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n<status>/removeZoneSlave</status>");
            return result;
          }
        };
      }
    };

    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    try {
      final Zone zone = new Zone("C8DF84AE0B6E");
      zone.addMember(new ZoneMember("192.168.178.61", "C8DF84AE0B6E"));
      soundTouchApi.getZoneApi()
          .removeZoneSlave(new Zone());

    } catch (final SoundTouchApiException e) {
      LOGGER.error("Unable to get the basic information: {}", e.getMessage());
      fail();
    }
    LOGGER.info("test04_addZoneSlave completed");
  }

  @Test
  public void test08_removeZoneSlaveBrokenResponse() {
    LOGGER.info("test05_addZoneSlaveBrokenResponse started");
    final HttpTransport transport = Const.getBrokenResponse();

    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    try {
      final Zone zone = new Zone("C8DF84AE0B6E");
      zone.addMember(new ZoneMember("192.168.178.61", "C8DF84AE0B6E"));
      soundTouchApi.getZoneApi()
          .removeZoneSlave(new Zone());
      fail();
    } catch (final SoundTouchApiException e) {
      LOGGER.error("Unable to get the basic information: {}", e.getMessage());

    }
    LOGGER.info("test05_addZoneSlaveBrokenResponse completed");
  }

  @Test
  public void test09_removeZoneSlaveIncorrectResponse() {
    LOGGER.info("test05_addZoneSlaveBrokenResponse started");
    final HttpTransport transport = Const.getIncorrectStatusResponse("NOTAremoveZoneSlave");

    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    try {
      final Zone zone = new Zone("C8DF84AE0B6E");
      zone.addMember(new ZoneMember("192.168.178.61", "C8DF84AE0B6E"));
      soundTouchApi.getZoneApi()
          .removeZoneSlave(new Zone());
      fail();
    } catch (final SoundTouchApiException e) {
      LOGGER.error("Unable to get the basic information: {}", e.getMessage());
      assertEquals("Invalid Response from Speaker. Response was '/NOTAremoveZoneSlave'", e.getMessage());
    }
    LOGGER.info("test06_addZoneSlaveIncorrectResponse completed");
  }

  @Test
  public void test10_setZoneBrokenResponse() {
    LOGGER.info("test10_setZoneBrokenResponse started");
    final HttpTransport transport = Const.getBrokenResponse();

    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    try {
      final Zone zone = new Zone("C8DF84AE0B6E");
      zone.addMember(new ZoneMember("192.168.178.61", "C8DF84AE0B6E"));
      soundTouchApi.getZoneApi()
          .setZone(new Zone());
      fail();
    } catch (final SoundTouchApiException e) {
      LOGGER.error("Unable to get the basic information: {}", e.getMessage());

    }
    LOGGER.info("test10_setZoneBrokenResponse completed");
  }

  @Test
  public void test11_setZoneIncorrectResponse() {
    LOGGER.info("test11_setZoneIncorrectResponse started");
    final HttpTransport transport = Const.getIncorrectStatusResponse("NOTAsetZone");

    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    try {
      final Zone zone = new Zone("C8DF84AE0B6E");
      zone.addMember(new ZoneMember("192.168.178.61", "C8DF84AE0B6E"));
      soundTouchApi.getZoneApi()
          .setZone(new Zone());
      fail();
    } catch (final SoundTouchApiException e) {
      LOGGER.error("Unable to get the basic information: {}", e.getMessage());
      assertEquals("Invalid Response from Speaker. Response was '/NOTAsetZone'", e.getMessage());
    }
    LOGGER.info("test11_setZoneIncorrectResponse completed");
  }


}
