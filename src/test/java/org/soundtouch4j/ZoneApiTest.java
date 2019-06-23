package org.soundtouch4j;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.testing.http.HttpTesting;
import com.google.api.client.util.Preconditions;
import org.junit.jupiter.api.Test;
import org.soundtouch4j.zone.Zone;
import org.soundtouch4j.zone.ZoneMember;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class ZoneApiTest {

  @Test
  public void getZoneEmpty() {
    final String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n<zone />";
    final HttpTransport transport = Const.getHttpTransportFromString(xml);

    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    soundTouchApi.getZoneApi()
        .getZone();
  }

  @Test
  public void getZoneList() {
    final String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n<zone master=\"C8DF84AE0B6E\">\n    <member ipaddress=\"192.168.178.61\">C8DF84AE0B6E</member>\n</zone>";
    final HttpTransport transport = Const.getHttpTransportFromString(xml);
    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    final Zone zone = soundTouchApi.getZoneApi()
        .getZone();

    assertEquals("C8DF84AE0B6E", zone.getDeviceID());
    assertEquals("192.168.178.61", zone.getMembers()
        .get(0)
        .getIpAddress());
    assertEquals("C8DF84AE0B6E", zone.getMembers()
        .get(0)
        .getMacAddress());

  }

  @Test
  public void setZone() {
    final String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n<status>/setZone</status>";
    final HttpTransport transport = Const.getHttpTransportFromString(xml);
    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    final Zone zone = new Zone("C8DF84AE0B6E");
    zone.addMember(new ZoneMember("192.168.178.61", "C8DF84AE0B6E"));
    soundTouchApi.getZoneApi()
        .setZone(new Zone());

  }

  @Test
  public void addZoneSlave() {
    final String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n<status>/addZoneSlave</status>";
    final HttpTransport transport = Const.getHttpTransportFromString(xml);
    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    final Zone zone = new Zone("C8DF84AE0B6E");
    zone.addMember(new ZoneMember("192.168.178.61", "C8DF84AE0B6E"));
    soundTouchApi.getZoneApi()
        .addZoneSlave(new Zone());
  }

  @Test
  public void addZoneSlaveBrokenResponse() {
    final HttpTransport transport = Const.getBrokenResponse();
    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    try {
      final Zone zone = new Zone("C8DF84AE0B6E");
      zone.addMember(new ZoneMember("192.168.178.61", "C8DF84AE0B6E"));
      soundTouchApi.getZoneApi()
          .addZoneSlave(new Zone());
      fail();
    } catch (final SoundTouchApiException e) {
      assertEquals("java.io.IOException", e.getMessage());

    }
  }

  @Test
  public void addZoneSlaveIncorrectResponse() {
    final HttpTransport transport = Const.getIncorrectStatusResponse("NOTAaddZoneSlave");

    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    try {
      final Zone zone = new Zone("C8DF84AE0B6E");
      zone.addMember(new ZoneMember("192.168.178.61", "C8DF84AE0B6E"));
      soundTouchApi.getZoneApi()
          .addZoneSlave(new Zone());
      fail();
    } catch (final SoundTouchApiException e) {
      assertEquals("Invalid Response from Speaker. Response was '/NOTAaddZoneSlave'", e.getMessage());
    }
  }

  @Test
  public void removeZoneSlave() {
    final String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n<status>/removeZoneSlave</status>";
    final HttpTransport transport = Const.getHttpTransportFromString(xml);
    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    final Zone zone = new Zone("C8DF84AE0B6E");
    zone.addMember(new ZoneMember("192.168.178.61", "C8DF84AE0B6E"));
    soundTouchApi.getZoneApi()
        .removeZoneSlave(new Zone());
  }

  @Test
  public void removeZoneSlaveBrokenResponse() {
    final HttpTransport transport = Const.getBrokenResponse();
    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    try {
      final Zone zone = new Zone("C8DF84AE0B6E");
      zone.addMember(new ZoneMember("192.168.178.61", "C8DF84AE0B6E"));
      soundTouchApi.getZoneApi()
          .removeZoneSlave(new Zone());
      fail();
    } catch (final SoundTouchApiException e) {
      assertEquals("java.io.IOException", e.getMessage());
    }
  }

  @Test
  public void removeZoneSlaveIncorrectResponse() {
    final HttpTransport transport = Const.getIncorrectStatusResponse("NOTAremoveZoneSlave");
    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    try {
      final Zone zone = new Zone("C8DF84AE0B6E");
      zone.addMember(new ZoneMember("192.168.178.61", "C8DF84AE0B6E"));
      soundTouchApi.getZoneApi()
          .removeZoneSlave(new Zone());
      fail();
    } catch (final SoundTouchApiException e) {
      assertEquals("Invalid Response from Speaker. Response was '/NOTAremoveZoneSlave'", e.getMessage());
    }
  }

  @Test
  public void setZoneBrokenResponse() {
    final HttpTransport transport = Const.getBrokenResponse();
    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    try {
      final Zone zone = new Zone("C8DF84AE0B6E");
      zone.addMember(new ZoneMember("192.168.178.61", "C8DF84AE0B6E"));
      soundTouchApi.getZoneApi()
          .setZone(new Zone());
      fail();
    } catch (final SoundTouchApiException e) {
      assertEquals("java.io.IOException", e.getMessage());
    }
  }

  @Test
  public void setZoneIncorrectResponse() {
    final HttpTransport transport = Const.getIncorrectStatusResponse("NOTAsetZone");

    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    try {
      final Zone zone = new Zone("C8DF84AE0B6E");
      zone.addMember(new ZoneMember("192.168.178.61", "C8DF84AE0B6E"));
      soundTouchApi.getZoneApi()
          .setZone(new Zone());
      fail();
    } catch (final SoundTouchApiException e) {
      assertEquals("Invalid Response from Speaker. Response was '/NOTAsetZone'", e.getMessage());
    }
  }

  // Test added when switching from 1.25.0 to 1.26.0 as this was broken without guava.
  @Test
  public void testPrecondition() {
    Preconditions.checkState(true);
  }


}
