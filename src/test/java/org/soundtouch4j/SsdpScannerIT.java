package org.soundtouch4j;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.resourcepool.ssdp.client.SsdpClient;
import io.resourcepool.ssdp.model.DiscoveryListener;
import io.resourcepool.ssdp.model.DiscoveryRequest;
import io.resourcepool.ssdp.model.SsdpService;
import io.resourcepool.ssdp.model.SsdpServiceAnnouncement;

@Disabled
public class SsdpScannerIT {

  private static final Logger LOGGER = LoggerFactory.getLogger(SsdpScannerIT.class);
  private static final String BOSE_URN = "urn:schemas-upnp-org:device:MediaRenderer:1";

  /**
   * Blocking Call to scan for Services meeting the Requirements of the BOSE SoundTouch. The Speaker needs to be on the same networks as our computer.
   */

  @Test
  public void test01_findDevices() {
    final SsdpClient client = SsdpClient.create();

    final BoseDiscoveryListener listener = new BoseDiscoveryListener();

    final DiscoveryRequest networkStorageDevice = DiscoveryRequest.builder()
        .serviceType(BOSE_URN)
        .build();

    client.discoverServices(networkStorageDevice, listener);

    // This is the way to do, but we are not on Java 8
    // await().atMost(5, TimeUnit.SECONDS).until(() -> listener.getServicesFound().size() == 1);
    await().atMost(5, TimeUnit.SECONDS)
        .until(new Callable<Boolean>() {
          @Override
          public Boolean call() {
            return listener.getServicesFound()
                .size() == 1;
          }
        });
    client.stopDiscovery();
    assertEquals(1, listener.getServicesFound()
        .size());
    LOGGER.info("Discovery Stopped and {} services found", listener.getServicesFound()
        .size());
  }


  private static class BoseDiscoveryListener implements DiscoveryListener {

    final List<SsdpService> servicesFound = new ArrayList<SsdpService>();

    @Override
    public void onServiceDiscovered(final SsdpService service) {
      LOGGER.debug("Found service at IP: {}", service.getRemoteIp()
          .toString());
      servicesFound.add(service);
    }

    @Override
    public void onServiceAnnouncement(final SsdpServiceAnnouncement announcement) {
      LOGGER.debug("Service announced something: {}", announcement);
    }

    @Override
    public void onFailed(final Exception ex) {
      LOGGER.debug("Service onFailed: {}", ex.getMessage());
    }

    List<SsdpService> getServicesFound() {
      return new ArrayList<SsdpService>(servicesFound);
    }
  }
}
