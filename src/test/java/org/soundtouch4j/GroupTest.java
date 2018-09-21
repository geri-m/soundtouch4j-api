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
            result.setContent("<?xml version=\"1.0\" encoding=\"UTF-8\" ?><group id=\"$STRING\"><name>name</name><masterDeviceId>masterDeviceId</masterDeviceId><roles><groupRole" +
                "><deviceId>deviceId1</deviceId><role>LEFT1</role><ipAddress>ipAddress1</ipAddress></groupRole><groupRole><deviceId>deviceId2</deviceId><role>RIGHT2</role" +
                "><ipAddress>ipAddress2</ipAddress></groupRole></roles><senderIPAddress>senderIPAddress</senderIPAddress><status>status</status></group>");
            return result;
          }
        };
      }
    };

    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    try {
      final Group group = soundTouchApi.getGroupApi()
          .getGroup();

      Assert.assertEquals(group.getId(), "$STRING");
      Assert.assertEquals(group.getGroupRoles()
          .size(), 2);
      Assert.assertEquals(group.getMasterDeviceId(), "masterDeviceId");
      Assert.assertEquals(group.getName(), "name");
      Assert.assertEquals(group.getStatus(), "status");

      Assert.assertEquals(group.getGroupRoles()
          .get(0)
          .getDeviceId(), "deviceId1");
      Assert.assertEquals(group.getGroupRoles()
          .get(0)
          .getIpAddress(), "ipAddress1");
      Assert.assertEquals(group.getGroupRoles()
          .get(0)
          .getRole(), "LEFT1");

      Assert.assertEquals(group.getGroupRoles()
          .get(1)
          .getDeviceId(), "deviceId2");
      Assert.assertEquals(group.getGroupRoles()
          .get(1)
          .getIpAddress(), "ipAddress2");
      Assert.assertEquals(group.getGroupRoles()
          .get(1)
          .getRole(), "RIGHT2");


    } catch (final SoundTouchApiException e) {
      LOGGER.error("Unable to get the basic information: {}", e.getMessage());
      Assert.fail();
    }
    LOGGER.info("test01_getGroup started");
  }

  public void test02_getGroupEmptyList() {
    LOGGER.info("test02_getGroupEmptyList started");
    final HttpTransport transport = new MockHttpTransport() {
      @Override
      public LowLevelHttpRequest buildRequest(final String method, final String url) {
        return new MockLowLevelHttpRequest() {
          @Override
          public LowLevelHttpResponse execute() {
            final MockLowLevelHttpResponse result = new MockLowLevelHttpResponse();
            result.setContentType(Xml.MEDIA_TYPE);
            result.setContent("<?xml version=\"1.0\" encoding=\"UTF-8\" ?><group id=\"$STRING\"><name>name</name><masterDeviceId>masterDeviceId</masterDeviceId><roles" +
                "/><senderIPAddress>senderIPAddress</senderIPAddress><status>status</status></group>");
            return result;
          }
        };
      }
    };

    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    try {
      final Group group = soundTouchApi.getGroupApi()
          .getGroup();

      Assert.assertEquals(group.getId(), "$STRING");
      Assert.assertEquals(group.getGroupRoles()
          .size(), 0);
      Assert.assertEquals(group.getMasterDeviceId(), "masterDeviceId");
      Assert.assertEquals(group.getName(), "name");
      Assert.assertEquals(group.getStatus(), "status");

    } catch (final SoundTouchApiException e) {
      LOGGER.error("Unable to get the basic information: {}", e.getMessage());
      Assert.fail();
    }
    LOGGER.info("test02_getGroupEmptyList started");
  }
}
