package org.soundtouch4j;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.soundtouch4j.common.SourceEnum;
import org.soundtouch4j.source.SourceItem;
import org.soundtouch4j.source.SourceResponse;
import org.soundtouch4j.source.SourceStatusEnum;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.testing.http.HttpTesting;


public class SourceApiTest {

  @Test
  public void getSources() throws SoundTouchApiException {
    final String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><sources deviceID=\"C8DF84AE0B6E\"><sourceItem source=\"AUX\" sourceAccount=\"AUX\" status=\"READY\" " +
        "isLocal=\"true\" multiroomallowed=\"true\">AUX IN</sourceItem><sourceItem source=\"INTERNET_RADIO\" status=\"READY\" isLocal=\"false\" multiroomallowed=\"true\" " +
        "/><sourceItem source=\"BLUETOOTH\" status=\"UNAVAILABLE\" isLocal=\"true\" multiroomallowed=\"true\" /><sourceItem source=\"NOTIFICATION\" status=\"UNAVAILABLE\" " +
        "isLocal=\"false\" multiroomallowed=\"true\" /><sourceItem source=\"QPLAY\" sourceAccount=\"QPlay1UserName\" status=\"UNAVAILABLE\" isLocal=\"true\" " +
        "multiroomallowed=\"true\">QPlay1UserName</sourceItem><sourceItem source=\"QPLAY\" sourceAccount=\"QPlay2UserName\" status=\"UNAVAILABLE\" isLocal=\"true\" " +
        "multiroomallowed=\"true\">QPlay2UserName</sourceItem><sourceItem source=\"UPNP\" sourceAccount=\"UPnPUserName\" status=\"UNAVAILABLE\" isLocal=\"false\" " +
        "multiroomallowed=\"true\">UPnPUserName</sourceItem><sourceItem source=\"SPOTIFY\" status=\"UNAVAILABLE\" isLocal=\"false\" multiroomallowed=\"true\" /><sourceItem " +
        "source=\"STORED_MUSIC_MEDIA_RENDERER\" sourceAccount=\"StoredMusicUserName\" status=\"UNAVAILABLE\" isLocal=\"false\" multiroomallowed=\"true\">StoredMusicUserName" + "</sourceItem><sourceItem source=\"ALEXA\" status=\"READY\" isLocal=\"false\" multiroomallowed=\"true\" /><sourceItem source=\"TUNEIN\" status=\"READY\" isLocal" + "=\"false\" multiroomallowed=\"true\" /></sources>";
    final HttpTransport transport = Const.getHttpTransportFromString(xml);

    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);

    final SourceResponse response = soundTouchApi.getSourceApi()
        .getSources();

    assertEquals("C8DF84AE0B6E", response.getDeviceID());
    assertEquals(11, response.getSourceItems()
        .size());

    assertEquals(SourceEnum.AUX, response.getSourceItems()
        .get(0)
        .getSource());

    assertEquals("AUX", response.getSourceItems()
        .get(0)
        .getSourceAccount());

    assertEquals(SourceStatusEnum.READY, response.getSourceItems()
        .get(0)
        .getStatus());

    assertTrue(response.getSourceItems()
        .get(0)
        .isLocal());

    assertTrue(response.getSourceItems()
        .get(0)
        .isMultiroomallowed());

    assertEquals("AUX IN", response.getSourceItems()
        .get(0)
        .getValue());

    final List<SourceItem> items1 = soundTouchApi.getSourceApi()
        .getSourcesByType(SourceEnum.AUX);
    assertEquals(1, items1.size());

    final List<SourceItem> items2 = soundTouchApi.getSourceApi()
        .getSourcesByType(SourceEnum.QPLAY);
    assertEquals(2, items2.size());

    final List<SourceItem> items3 = soundTouchApi.getSourceApi()
        .getSourcesByType(SourceEnum.STANDBY);
    assertEquals(0, items3.size());
  }
}
