package org.soundtouch4j;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.soundtouch4j.group.Group;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.testing.http.HttpTesting;
import com.google.api.client.testing.http.MockHttpTransport;
import com.google.api.client.testing.http.MockLowLevelHttpRequest;
import com.google.api.client.testing.http.MockLowLevelHttpResponse;
import com.google.api.client.xml.Xml;
import junit.framework.TestCase;

public class GroupTest extends TestCase {


  private static final Logger LOGGER = LoggerFactory.getLogger(GroupTest.class);

  public void test01_getGroup() {
    LOGGER.info("test01_getGroup started");
    final HttpTransport transport = new MockHttpTransport() {
      @Override
      public LowLevelHttpRequest buildRequest(final String method, final String url) {
        return new MockLowLevelHttpRequest() {
          @Override
          public LowLevelHttpResponse execute() {
            final MockLowLevelHttpResponse result = new MockLowLevelHttpResponse();
            result.setContentType(Xml.MEDIA_TYPE);
            result.setContent("<?xml version=\"1.0\" encoding=\"UTF-8\" ?><group id=\"$STRING\"><name>$STRING</name><masterDeviceId>$MACADDR</masterDeviceId><roles><groupRole>" + "<deviceId>$MACADDR</deviceId><role>LEFT</role><ipAddress>$IPADDR</ipAddress></groupRole><groupRole><deviceId>$MACADDR</deviceId><role>RIGHT</role><ipAddress>" + "IPADDR</ipAddress></groupRole></roles><senderIPAddress>IPADDR</senderIPAddress><status>$GROUP_STATUS</status></group>");
            return result;
          }
        };
      }
    };

    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    try {
      final Group response = soundTouchApi.getGroupApi()
          .getGroup();

      LOGGER.info("Group: {}", response);


    } catch (final SoundTouchApiException e) {
      LOGGER.error("Unable to get the basic information: {}", e.getMessage());
      Assert.fail();
    }
    LOGGER.info("test01_getGroup started");
  }
}
