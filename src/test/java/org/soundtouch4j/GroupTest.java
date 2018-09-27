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

  private static final MockLowLevelHttpRequest GROUP_MOCK_OK = new MockLowLevelHttpRequest() {
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

  private static final MockLowLevelHttpRequest GROUP_MOCK_EMPTY = new MockLowLevelHttpRequest() {
    @Override
    public LowLevelHttpResponse execute() {
      final MockLowLevelHttpResponse result = new MockLowLevelHttpResponse();
      result.setContentType(Xml.MEDIA_TYPE);
      result.setContent("<?xml version=\"1.0\" encoding=\"UTF-8\" ?><group id=\"$STRING\"><name>name</name><masterDeviceId>masterDeviceId</masterDeviceId><roles" +
          "/><senderIPAddress>senderIPAddress</senderIPAddress><status>status</status></group>");
      return result;
    }
  };

  private static final MockLowLevelHttpRequest INFO_MOCK_OK = new MockLowLevelHttpRequest() {
    @Override
    public LowLevelHttpResponse execute() {
      final MockLowLevelHttpResponse result = new MockLowLevelHttpResponse();
      result.setContentType(Xml.MEDIA_TYPE);
      result.setContent("<?xml version=\"1.0\" encoding=\"UTF-8\" ?><info deviceID=\"C8DF84AE0B6E\"><name>SoundTouch 10</name><type>SoundTouch 10</type" + "><margeAccountUUID" +
          ">6990307</margeAccountUUID><components><component><componentCategory>SCM</componentCategory><softwareVersion>19.0.5.42017.2794643 " + "epdbuild.trunk.cepeswbld02" +
          ".2018-04-25T18:23:30</softwareVersion><serialNumber>F8124895404720048620440</serialNumber></component><component><componentCategory>" + "PackagedProduct" +
          "</componentCategory><serialNumber>069428P81639976AE</serialNumber></component></components><margeURL>https://streaming.bose.com</margeURL>" + "<networkInfo " +
          "type=\"SCM\"><macAddress>C8DF84AE0B6E</macAddress><ipAddress>192.168.178.61</ipAddress></networkInfo><networkInfo " + "type=\"SMSC\"><macAddress>C8DF84615084" +
          "</macAddress><ipAddress>192.168.178" + ".61</ipAddress></networkInfo><moduleType>sm2</moduleType><variant>spotty</variant><variantMode>normal</variantMode" +
          "><countryCode>GB</countryCode><regionCode>GB" + "</regionCode></info>");
      return result;
    }
  };

  private static final MockLowLevelHttpRequest INFO_MOCK_ST20 = new MockLowLevelHttpRequest() {
    @Override
    public LowLevelHttpResponse execute() {
      final MockLowLevelHttpResponse result = new MockLowLevelHttpResponse();
      result.setContentType(Xml.MEDIA_TYPE);
      result.setContent("<?xml version=\"1.0\" encoding=\"UTF-8\" ?><info deviceID=\"C8DF84AE0B6E\"><name>SoundTouch 20</name><type>SoundTouch 20</type" + "><margeAccountUUID" +
          ">6990307</margeAccountUUID><components><component><componentCategory>SCM</componentCategory><softwareVersion>19.0.5.42017.2794643 " + "epdbuild.trunk.cepeswbld02" +
          ".2018-04-25T18:23:30</softwareVersion><serialNumber>F8124895404720048620440</serialNumber></component><component><componentCategory>" + "PackagedProduct" +
          "</componentCategory><serialNumber>069428P81639976AE</serialNumber></component></components><margeURL>https://streaming.bose.com</margeURL>" + "<networkInfo " +
          "type=\"SCM\"><macAddress>C8DF84AE0B6E</macAddress><ipAddress>192.168.178.61</ipAddress></networkInfo><networkInfo " + "type=\"SMSC\"><macAddress>C8DF84615084" +
          "</macAddress><ipAddress>192.168.178" + ".61</ipAddress></networkInfo><moduleType>sm2</moduleType><variant>spotty</variant><variantMode>normal</variantMode" +
          "><countryCode>GB</countryCode><regionCode>GB" + "</regionCode></info>");
      return result;
    }
  };

  private static final MockLowLevelHttpRequest INFO_MOCK_VERSION_BROKEN = new MockLowLevelHttpRequest() {
    @Override
    public LowLevelHttpResponse execute() {
      final MockLowLevelHttpResponse result = new MockLowLevelHttpResponse();
      result.setContentType(Xml.MEDIA_TYPE);
      result.setContent("<?xml version=\"1.0\" encoding=\"UTF-8\" ?><info deviceID=\"C8DF84AE0B6E\"><name>SoundTouch 10</name><type>SoundTouch 10</type" + "><margeAccountUUID" +
          ">6990307</margeAccountUUID><components><component><componentCategory>SCM</componentCategory><softwareVersion>1x.0.5.42017.2794643 " + "epdbuild.trunk.cepeswbld02" +
          ".2018-04-25T18:23:30</softwareVersion><serialNumber>F8124895404720048620440</serialNumber></component><component><componentCategory>" + "PackagedProduct" +
          "</componentCategory><serialNumber>069428P81639976AE</serialNumber></component></components><margeURL>https://streaming.bose.com</margeURL>" + "<networkInfo " +
          "type=\"SCM\"><macAddress>C8DF84AE0B6E</macAddress><ipAddress>192.168.178.61</ipAddress></networkInfo><networkInfo " + "type=\"SMSC\"><macAddress>C8DF84615084" +
          "</macAddress><ipAddress>192.168.178" + ".61</ipAddress></networkInfo><moduleType>sm2</moduleType><variant>spotty</variant><variantMode>normal</variantMode" +
          "><countryCode>GB</countryCode><regionCode>GB" + "</regionCode></info>");
      return result;
    }
  };

  private static final MockLowLevelHttpRequest INFO_MOCK_NO_COMPONENTS = new MockLowLevelHttpRequest() {
    @Override
    public LowLevelHttpResponse execute() {
      final MockLowLevelHttpResponse result = new MockLowLevelHttpResponse();
      result.setContentType(Xml.MEDIA_TYPE);
      result.setContent("<?xml version=\"1.0\" encoding=\"UTF-8\" ?><info deviceID=\"C8DF84AE0B6E\"><name>SoundTouch 10</name><type>SoundTouch 10</type" + "><margeAccountUUID" +
          ">6990307</margeAccountUUID><components /><margeURL>https://streaming.bose.com</margeURL><networkInfo type=\"SCM\"><macAddress>C8DF84AE0B6E</macAddress><ipAddress>192" +
          ".168.178.61</ipAddress></networkInfo><networkInfo " + "type=\"SMSC\"><macAddress>C8DF84615084</macAddress><ipAddress>192.168.178" + ".61</ipAddress></networkInfo" +
          "><moduleType>sm2</moduleType><variant>spotty</variant><variantMode>normal</variantMode><countryCode>GB</countryCode><regionCode>GB" + "</regionCode></info>");
      return result;
    }
  };

  public void test01_getGroup() {
    LOGGER.info("test01_getGroup started");
    final HttpTransport transport = new MockHttpTransport() {
      @Override
      public LowLevelHttpRequest buildRequest(final String method, final String url) {
        if (url.endsWith("/info")) {
          return INFO_MOCK_OK;
        } else {
          return GROUP_MOCK_OK;
        }
      }
    };

    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    try {
      final Group group = soundTouchApi.getGroupApi()
          .getGroup();

      Assert.assertEquals("$STRING", group.getId());
      Assert.assertEquals(2, group.getGroupRoles()
          .size());
      Assert.assertEquals("masterDeviceId", group.getMasterDeviceId());
      Assert.assertEquals("name", group.getName());
      Assert.assertEquals("status", group.getStatus());

      Assert.assertEquals("deviceId1", group.getGroupRoles()
          .get(0)
          .getDeviceId());
      Assert.assertEquals("ipAddress1", group.getGroupRoles()
          .get(0)
          .getIpAddress());
      Assert.assertEquals("LEFT1", group.getGroupRoles()
          .get(0)
          .getRole());

      Assert.assertEquals("deviceId2", group.getGroupRoles()
          .get(1)
          .getDeviceId());
      Assert.assertEquals("ipAddress2", group.getGroupRoles()
          .get(1)
          .getIpAddress());
      Assert.assertEquals("RIGHT2", group.getGroupRoles()
          .get(1)
          .getRole());


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
        if (url.endsWith("/info")) {
          return INFO_MOCK_OK;
        } else {
          return GROUP_MOCK_EMPTY;
        }
      }
    };

    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    try {
      final Group group = soundTouchApi.getGroupApi()
          .getGroup();

      Assert.assertEquals("$STRING", group.getId());
      Assert.assertEquals(0, group.getGroupRoles()
          .size());
      Assert.assertEquals("masterDeviceId", group.getMasterDeviceId());
      Assert.assertEquals("name", group.getName());
      Assert.assertEquals("status", group.getStatus());

    } catch (final SoundTouchApiException e) {
      LOGGER.error("Unable to get the basic information: {}", e.getMessage());
      Assert.fail();
    }
    LOGGER.info("test02_getGroupEmptyList started");
  }

  public void test03_getGroupFailDueToST20() {
    LOGGER.info("test03_getGroupFailDueToST20 started");
    final HttpTransport transport = new MockHttpTransport() {
      @Override
      public LowLevelHttpRequest buildRequest(final String method, final String url) {
        if (url.endsWith("/info")) {
          return INFO_MOCK_ST20;
        } else {
          return GROUP_MOCK_OK;
        }
      }
    };

    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    try {
      soundTouchApi.getGroupApi()
          .getGroup();
      Assert.fail();
    } catch (final SoundTouchApiException e) {
      assertEquals("/group Call Requires SoundTouch 10, but this is 'SoundTouch 20'", e.getMessage());
    }
    LOGGER.info("test03_getGroupFailDueToST20 started");
  }

  public void test03_getGroupFailDueToBrokenVersion() {
    LOGGER.info("test03_getGroupFailDueToBrokenVersion started");
    final HttpTransport transport = new MockHttpTransport() {
      @Override
      public LowLevelHttpRequest buildRequest(final String method, final String url) {
        if (url.endsWith("/info")) {
          return INFO_MOCK_VERSION_BROKEN;
        } else {
          return GROUP_MOCK_OK;
        }
      }
    };

    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    try {
      soundTouchApi.getGroupApi()
          .getGroup();
      Assert.fail();
    } catch (final SoundTouchApiException e) {
      assertEquals("Unable to parse Version String '1x.0.5.42017.2794643 epdbuild.trunk.cepeswbld02.2018-04-25T18:23:30'", e.getMessage());
    }
    LOGGER.info("test03_getGroupFailDueToBrokenVersion started");
  }

  public void test04_getGroupFailDueToNoComponents() {
    LOGGER.info("test04_getGroupFailDueToNoComponents started");
    final HttpTransport transport = new MockHttpTransport() {
      @Override
      public LowLevelHttpRequest buildRequest(final String method, final String url) {
        if (url.endsWith("/info")) {
          return INFO_MOCK_NO_COMPONENTS;
        } else {
          return GROUP_MOCK_OK;
        }
      }
    };

    final SoundTouch soundTouchApi = new SoundTouchApi(HttpTesting.SIMPLE_GENERIC_URL.toURL(), transport);
    try {
      soundTouchApi.getGroupApi()
          .getGroup();
      Assert.fail();
    } catch (final SoundTouchApiException e) {
      assertEquals("Unable to fetch Version of SoundTouch", e.getMessage());
    }
    LOGGER.info("test04_getGroupFailDueToNoComponents started");
  }

}
