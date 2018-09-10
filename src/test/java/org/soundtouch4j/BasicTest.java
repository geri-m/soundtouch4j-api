package org.soundtouch4j;

import java.util.List;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.resourcepool.ssdp.model.SsdpService;

public class BasicTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(BasicTest.class);

  @Test
  public void test01_basicTestWithBox() {

    final List<SsdpService> services = SsdpScanner.synchronousBlockingDeviceScaner(5000);

    for (final SsdpService service : services) {
      LOGGER.info("Service '{}' with IP '{}' found", service.getSerialNumber(), service.getRemoteIp());
    }

  }

}
