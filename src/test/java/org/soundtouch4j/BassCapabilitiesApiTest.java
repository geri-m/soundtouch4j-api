package org.soundtouch4j;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.soundtouch4j.basscapabilities.BaseCapabilitiesResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.testing.http.HttpTesting;


public class BassCapabilitiesApiTest {

  @Test
  public void getBassCapabilities() throws SoundTouchApiException {
    final String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><bassCapabilities " + "deviceID=\"C8DF84AE0B6E\"><bassAvailable>true</bassAvailable><bassMin>-9" + "</bassMin" + "><bassMax>0" + "</bassMax><bassDefault>0</bassDefault></bassCapabilities>";
    final HttpTransport transport = Const.getHttpTransportFromString(xml);

    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    final BaseCapabilitiesResponse response = soundTouchApi.getBassCapabilitiesApi()
        .getBassCapabilities();
    assertEquals("C8DF84AE0B6E", response.getDeviceID());
    assertEquals(-9, response.getBassMin());
    assertEquals(0, response.getBassMax());
    assertEquals(0, response.getBassDefault());
    assertTrue(response.isBassAvailable());
  }
}

