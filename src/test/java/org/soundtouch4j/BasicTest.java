package org.soundtouch4j;

import java.net.MalformedURLException;
import java.net.URL;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasicTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(BasicTest.class);
  private static final int TIME_TO_SCAN_FOR_DEVICE_IN_MS = 3000;


  @Test
  public void test01_basicTestWithBox() {
    LOGGER.info("test01_basicTestWithBox started");

    /*
    Not doing an Active Search for the Beginning

    final List<SsdpService> services = SsdpScanner.synchronousBlockingDeviceScanner(TIME_TO_SCAN_FOR_DEVICE_IN_MS);
    for (final SsdpService service : services) {
      LOGGER.info("Service '{}' with IP '{}' found", service.getSerialNumber(), service.getRemoteIp());
    }
    */


    final URL boseEndpoint;
    try {
      // boseEndpoint = new URL("http://"+ services.get(0).getRemoteIp().getHostAddress() + ":8090");
      boseEndpoint = new URL(Const.URL);
      LOGGER.info("Using urL: {}", boseEndpoint.toString());
    } catch (final MalformedURLException e) {
      // LOGGER.error("Failed to Create URL from IP: {}. Msg: {}", services.get(0).getRemoteIp(), e.getMessage());
      Assert.fail();
      return;
    }

    final SoundTouchApi soundTouchApi = new SoundTouchApi(boseEndpoint);
    try {
      soundTouchApi.getKeyApi()
          .power();
    } catch (final SoundTouchApiException e) {
      LOGGER.error("Unable to press the Power Button: {}", e.getMessage());
      Assert.fail();
      return;
    }

    LOGGER.info("test01_basicTestWithBox passed");
  }


}