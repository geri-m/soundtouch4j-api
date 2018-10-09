package org.soundtouch4j;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import org.soundtouch4j.group.Group;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.testing.http.HttpTesting;
import com.google.api.client.testing.http.MockHttpTransport;
import com.google.api.client.testing.http.MockLowLevelHttpRequest;
import com.google.api.client.testing.http.MockLowLevelHttpResponse;
import com.google.api.client.xml.Xml;

public class GroupTest {

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
      result.setContent("<?xml version=\"1.0\" encoding=\"UTF-8\" ?><info deviceID=\"C8DF84AE0B6E\"><name>SoundTouch 10</name><type>SoundTouch 10</type" + "><margeAccountUUID" + ">6990307</margeAccountUUID><components><component><componentCategory>SCM</componentCategory><softwareVersion>19.0.5.42017.2794643 " + "epdbuild.trunk.cepeswbld02" + ".2018-04-25T18:23:30</softwareVersion><serialNumber>F8124895404720048620440</serialNumber></component><component><componentCategory>" + "PackagedProduct" + "</componentCategory><serialNumber>069428P81639976AE</serialNumber></component></components><margeURL>https://streaming.bose.com</margeURL>" + "<networkInfo " + "type=\"SCM\"><macAddress>C8DF84AE0B6E</macAddress><ipAddress>192.168.178.61</ipAddress></networkInfo><networkInfo " + "type=\"SMSC\"><macAddress>C8DF84615084" + "</macAddress><ipAddress>192.168.178" + ".61</ipAddress></networkInfo><moduleType>sm2</moduleType><variant>spotty</variant><variantMode>normal</variantMode" + "><countryCode>GB</countryCode><regionCode>GB" + "</regionCode></info>");
      return result;
    }
  };

  private static final MockLowLevelHttpRequest INFO_MOCK_ST20 = new MockLowLevelHttpRequest() {
    @Override
    public LowLevelHttpResponse execute() {
      final MockLowLevelHttpResponse result = new MockLowLevelHttpResponse();
      result.setContentType(Xml.MEDIA_TYPE);
      result.setContent("<?xml version=\"1.0\" encoding=\"UTF-8\" ?><info deviceID=\"C8DF84AE0B6E\"><name>SoundTouch 20</name><type>SoundTouch 20</type" + "><margeAccountUUID" + ">6990307</margeAccountUUID><components><component><componentCategory>SCM</componentCategory><softwareVersion>19.0.5.42017.2794643 " + "epdbuild.trunk.cepeswbld02" + ".2018-04-25T18:23:30</softwareVersion><serialNumber>F8124895404720048620440</serialNumber></component><component><componentCategory>" + "PackagedProduct" + "</componentCategory><serialNumber>069428P81639976AE</serialNumber></component></components><margeURL>https://streaming.bose.com</margeURL>" + "<networkInfo " + "type=\"SCM\"><macAddress>C8DF84AE0B6E</macAddress><ipAddress>192.168.178.61</ipAddress></networkInfo><networkInfo " + "type=\"SMSC\"><macAddress>C8DF84615084" + "</macAddress><ipAddress>192.168.178" + ".61</ipAddress></networkInfo><moduleType>sm2</moduleType><variant>spotty</variant><variantMode>normal</variantMode" + "><countryCode>GB</countryCode><regionCode>GB" + "</regionCode></info>");
      return result;
    }
  };

  private static final MockLowLevelHttpRequest INFO_MOCK_VERSION_BROKEN = new MockLowLevelHttpRequest() {
    @Override
    public LowLevelHttpResponse execute() {
      final MockLowLevelHttpResponse result = new MockLowLevelHttpResponse();
      result.setContentType(Xml.MEDIA_TYPE);
      result.setContent("<?xml version=\"1.0\" encoding=\"UTF-8\" ?><info deviceID=\"C8DF84AE0B6E\"><name>SoundTouch 10</name><type>SoundTouch 10</type" + "><margeAccountUUID" + ">6990307</margeAccountUUID><components><component><componentCategory>SCM</componentCategory><softwareVersion>1x.0.5.42017.2794643 " + "epdbuild.trunk.cepeswbld02" + ".2018-04-25T18:23:30</softwareVersion><serialNumber>F8124895404720048620440</serialNumber></component><component><componentCategory>" + "PackagedProduct" + "</componentCategory><serialNumber>069428P81639976AE</serialNumber></component></components><margeURL>https://streaming.bose.com</margeURL>" + "<networkInfo " + "type=\"SCM\"><macAddress>C8DF84AE0B6E</macAddress><ipAddress>192.168.178.61</ipAddress></networkInfo><networkInfo " + "type=\"SMSC\"><macAddress>C8DF84615084" + "</macAddress><ipAddress>192.168.178" + ".61</ipAddress></networkInfo><moduleType>sm2</moduleType><variant>spotty</variant><variantMode>normal</variantMode" + "><countryCode>GB</countryCode><regionCode>GB" + "</regionCode></info>");
      return result;
    }
  };

  private static final MockLowLevelHttpRequest INFO_MOCK_NO_COMPONENTS = new MockLowLevelHttpRequest() {
    @Override
    public LowLevelHttpResponse execute() {
      final MockLowLevelHttpResponse result = new MockLowLevelHttpResponse();
      result.setContentType(Xml.MEDIA_TYPE);
      result.setContent("<?xml version=\"1.0\" encoding=\"UTF-8\" ?><info deviceID=\"C8DF84AE0B6E\"><name>SoundTouch 10</name><type>SoundTouch 10</type" + "><margeAccountUUID" + ">6990307</margeAccountUUID><components /><margeURL>https://streaming.bose.com</margeURL><networkInfo type=\"SCM\"><macAddress>C8DF84AE0B6E</macAddress><ipAddress>192" + ".168.178.61</ipAddress></networkInfo><networkInfo " + "type=\"SMSC\"><macAddress>C8DF84615084</macAddress><ipAddress>192.168.178" + ".61</ipAddress></networkInfo" + "><moduleType>sm2</moduleType><variant>spotty</variant><variantMode>normal</variantMode><countryCode>GB</countryCode><regionCode>GB" + "</regionCode></info>");
      return result;
    }
  };

  @Test
  public void getGroupSuccessful() throws SoundTouchApiException {
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

    final Group group = soundTouchApi.getGroupApi()
        .getGroup();

    assertEquals("$STRING", group.getId());
    assertEquals(2, group.getGroupRoles()
        .size());
    assertEquals("masterDeviceId", group.getMasterDeviceId());
    assertEquals("name", group.getName());
    assertEquals("status", group.getStatus());

    assertEquals("deviceId1", group.getGroupRoles()
        .get(0)
        .getDeviceId());
    assertEquals("ipAddress1", group.getGroupRoles()
        .get(0)
        .getIpAddress());
    assertEquals("LEFT1", group.getGroupRoles()
        .get(0)
        .getRole());

    assertEquals("deviceId2", group.getGroupRoles()
        .get(1)
        .getDeviceId());
    assertEquals("ipAddress2", group.getGroupRoles()
        .get(1)
        .getIpAddress());
    assertEquals("RIGHT2", group.getGroupRoles()
        .get(1)
        .getRole());

  }

  @Test
  public void getGroupEmptyList() throws SoundTouchApiException {
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

    final Group group = soundTouchApi.getGroupApi()
        .getGroup();

    assertEquals("$STRING", group.getId());
    assertEquals(0, group.getGroupRoles()
        .size());
    assertEquals("masterDeviceId", group.getMasterDeviceId());
    assertEquals("name", group.getName());
    assertEquals("status", group.getStatus());
  }

  @Test
  public void getGroupFailDueToST20() {
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
      fail();
    } catch (final SoundTouchApiException e) {
      assertEquals("/group Call Requires SoundTouch 10, but this is 'SoundTouch 20'", e.getMessage());
    }
  }

  @Test
  public void getGroupFailDueToBrokenVersion() {
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
      fail();
    } catch (final SoundTouchApiException e) {
      assertEquals("Unable to parse Version String '1x.0.5.42017.2794643 epdbuild.trunk.cepeswbld02.2018-04-25T18:23:30'", e.getMessage());
    }
  }

  @Test
  public void getGroupFailDueToNoComponents() {
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
      fail();
    } catch (final SoundTouchApiException e) {
      assertEquals("Unable to fetch Version of SoundTouch", e.getMessage());
    }
  }
}
