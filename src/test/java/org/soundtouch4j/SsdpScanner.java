package org.soundtouch4j;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.resourcepool.ssdp.client.SsdpClient;
import io.resourcepool.ssdp.model.DiscoveryListener;
import io.resourcepool.ssdp.model.DiscoveryRequest;
import io.resourcepool.ssdp.model.SsdpService;
import io.resourcepool.ssdp.model.SsdpServiceAnnouncement;

public class SsdpScanner {

  private static final Logger LOGGER = LoggerFactory.getLogger(SsdpScanner.class);
  private static final String BOSE_URN = "urn:schemas-upnp-org:device:MediaRenderer:1";

  private SsdpScanner() {
    // static methods only
  }

  /**
   * Blocking Call to scan for Services meeting the Requirements of the BOSE Soundtouch. The Speaker needs to be on the same networks as our computer.
   *
   * @param timeToScanInMilliseconds Duration in Milliseconds to scan for a device. Within a home network with a single router, 2 - 5 seconds provided sound results.
   * @return List of {@code SsdpService} that will be returned by the search.
   */

  public static synchronized List<SsdpService> synchronousBlockingDeviceScanner(final int timeToScanInMilliseconds) {
    final SsdpClient client = SsdpClient.create();
    final List<SsdpService> servicesFound = new ArrayList<>();

    final DiscoveryRequest networkStorageDevice = DiscoveryRequest.builder()
        .serviceType(BOSE_URN)
        .build();

    client.discoverServices(networkStorageDevice, new DiscoveryListener() {
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
    });

    // We can do that for now, as we are on a separate thread anyway. Not nice, but does the job.
    try {
      Thread.sleep(timeToScanInMilliseconds);
    } catch (final InterruptedException e) {
      LOGGER.error("Error on Sleep: {}", e.getMessage());
    }

    LOGGER.info("Discovery Stopped and {} services found", servicesFound.size());
    client.stopDiscovery();

    return servicesFound;
  }
}
