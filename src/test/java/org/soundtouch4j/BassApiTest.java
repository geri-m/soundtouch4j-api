package org.soundtouch4j;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import org.soundtouch4j.bass.BassGetResponse;
import org.soundtouch4j.zone.Zone;
import org.soundtouch4j.zone.ZoneMember;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.testing.http.HttpTesting;

public class BassApiTest {

  @Test
  public void getBassSuccessful() throws SoundTouchApiException {
    final String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><bass deviceID=\"C8DF84AE0B6E\"><targetbass>1</targetbass><actualbass>2</actualbass></bass>";

    final HttpTransport transport = Const.getHttpTransportFromString(xml);
    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);

    final BassGetResponse response = soundTouchApi.getBassApi()
        .getBass();

    assertEquals("C8DF84AE0B6E", response.getDeviceID());
    assertEquals(2, response.getActualBass());
    assertEquals(1, response.getTargetBass());
    assertEquals("BassGetResponse{deviceID='C8DF84AE0B6E', targetBass=1, actualBass=2}", response.toString());
  }

  @Test
  public void setBassSuccessful() throws SoundTouchApiException {
    final String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><status>/bass</status>";
    final HttpTransport transport = Const.getHttpTransportFromString(xml);
    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    soundTouchApi.getBassApi()
        .setBass(10);
  }

  @Test
  public void setBassBrokenResponse() {
    final HttpTransport transport = Const.getBrokenResponse();
    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    try {
      final Zone zone = new Zone("C8DF84AE0B6E");
      zone.addMember(new ZoneMember("192.168.178.61", "C8DF84AE0B6E"));
      assertEquals("Zone{macAddress='C8DF84AE0B6E', members=[ZoneMember{ipaddress='192.168.178.61', macAddress='C8DF84AE0B6E'}]}", zone.toString());
      soundTouchApi.getBassApi()
          .setBass(10);
      fail();
    } catch (final SoundTouchApiException e) {
      assertEquals("java.io.IOException", e.getMessage());
    }
  }

  @Test
  public void setBassIncorrectResponse() {
    final HttpTransport transport = Const.getIncorrectStatusResponse("NOTAsetBase");
    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    try {
      final Zone zone = new Zone("C8DF84AE0B6E");
      zone.addMember(new ZoneMember("192.168.178.61", "C8DF84AE0B6E"));
      assertEquals("Zone{macAddress='C8DF84AE0B6E', members=[ZoneMember{ipaddress='192.168.178.61', macAddress='C8DF84AE0B6E'}]}", zone.toString());
      soundTouchApi.getBassApi()
          .setBass(10);
      fail();
    } catch (final SoundTouchApiException e) {
      assertEquals("Invalid Response from Speaker. Response was '/NOTAsetBase'", e.getMessage());
    }
  }
}