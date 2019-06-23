package org.soundtouch4j;


import com.google.api.client.http.HttpTransport;
import com.google.api.client.testing.http.HttpTesting;
import org.junit.jupiter.api.Test;
import org.soundtouch4j.basscapabilities.BaseCapabilitiesResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class BassCapabilitiesApiTest {

  @Test
  public void getBassCapabilities() {
    final String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><bassCapabilities deviceID=\"C8DF84AE0B6E\"><bassAvailable>true</bassAvailable><bassMin>-9</bassMin" +
        "><bassMax>0</bassMax><bassDefault>0</bassDefault></bassCapabilities>";
    final HttpTransport transport = Const.getHttpTransportFromString(xml);

    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    final BaseCapabilitiesResponse response = soundTouchApi.getBassCapabilitiesApi()
        .getBassCapabilities();
    assertEquals("BaseCapabilitiesResponse{deviceID=C8DF84AE0B6E, bassAvailable=true, bassMin=-9, bassMax=0, bassDefault=0}", response.toString());
    assertEquals("C8DF84AE0B6E", response.getDeviceID());
    assertEquals(-9, response.getBassMin());
    assertEquals(0, response.getBassMax());
    assertEquals(0, response.getBassDefault());
    assertTrue(response.isBassAvailable());
  }
}

