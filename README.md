[![CircleCI](https://circleci.com/gh/geri-m/soundtouch4j-api.svg?style=svg)](https://circleci.com/gh/geri-m/soundtouch4j-api)
[![Javadocs](http://www.javadoc.io/badge/org.soundtouch4j/soundtouch4j-api.svg)](http://www.javadoc.io/doc/org.soundtouch4j/soundtouch4j-api)
[![license](https://img.shields.io/badge/license-MIT-brightgreen.svg?style=flat)](https://github.com/geri-m/soundtouch4j-api/blob/master/LICENSE)
[![Release Version](https://img.shields.io/badge/release-1.0.5-brightgreen.svg)](https://github.com/Tencent/MMKV/releases)
[![Platform](https://img.shields.io/badge/Platform-Java-brightgreen.svg)](https://github.com/Tencent/MMKV/wiki/home)
[![Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=geri-m_soundtouch4j-api&metric=alert_status)](https://sonarcloud.io/dashboard?id=geri-m_soundtouch4j-api)

# Java API for Bose SoundTouch API - SoundTouch4J

Bose is offering a [line of speaker](https://www.bose.com/en_us/products/speakers/smart_home/soundtouch_family.html) that have 
[REST API](https://developer.bose.com/soundtouch-control-api/apis).

This project offers an API implemented in Java in to the seamlessly interact with the Speakers using Java or Android applications.

The API is built and tested with and for a Bose SoundTouch 20 with Firmware Version 19.0.5.42017.2794643.

## Usage in a Maven Project

Simply add the dependency

```xml
<dependency>
  <groupId>org.soundtouch4j</groupId>
  <artifactId>soundtouch4j-api</artifactId>
  <version>1.0.5</version>
</dependency>
```

This will add the [Google HTTP Client](https://developers.google.com/api-client-library/java/google-http-java-client/) and the XML Implementation
to your project

## Usage in an Android Project

Similar to the Maven Project, we include the JAR, but exclude some binaries that are shipped with the Google Client Lib.

```groovy
implementation ('org.soundtouch4j:soundtouch4j-api:1.0.5') {
  exclude  module: 'httpclient'
  exclude  module: 'xpp3'
  exclude  module: 'commons-logging'
}
```

# Releases

## Version 1.0.5 (September 27th, 2018)

- Update ```/group``` endpoint and add check for Version and Product Type

## Version 1.0.4 (September 22nd, 2018)

- Adding Checks in the Set-Endpoints on the Response that is received from the Speaker
- Increase Test Coverage
- Adding CI and Code Inspection via SonarCloud

## Version 1.0.3 (September 21st, 2018)

Adding Endpoint
- ```/getGroup```
- ```/getZone```
- ```/setZone```
- ```/addZoneSlave```
- ```/removeZoneSlave```


## Version 1.0.2 (September 18th, 2018)

Adding Endpoint
- ```/bass```
- ```/bassCapabilities```

## Version 1.0.1 (September 18th, 2018)

Adding Endpoint
- ```/name```

## Version 1.0.0 (September 18th, 2018)

Available Endpoints
- ```/info```
- ```/key```
- ```/nowplaying```
- ```/preset```
- ```/select```
- ```/source```
- ```/volume```

The methods in the Implementation mirror 1:1 the behavior of the REST API of the Speaker. 

Library is compiled for Java 6, so it can be used easily on Android as well.

# Usage SoundTouch4J

In order to use a dynamic URL for your speaker, please have look on the SsdpScanner in the Tests.

## Java SE

```java
// Create a SoundTouch instance to communicate with your speaker
final SoundTouch soundTouchApi = new SoundTouchApi("http://soudntouch.sample.net", new NetHttpTransport());

 // If the speaker is not on, turn it on
if (soundTouchApi.getNowPlayingApi().nowPlaying().isInStandbyMode()) {
  soundTouchApi.getKeyApi().power();
}
```

## Android

```java
// Create a SoundTouch instance to communicate with your speaker
final SoundTouch soundTouchApi = new SoundTouchApi("http://soudntouch.sample.net",  AndroidHttp.newCompatibleTransport());

 // If the speaker is not on, turn it on
if (soundTouchApi.getNowPlayingApi().nowPlaying().isInStandbyMode()) {
  soundTouchApi.getKeyApi().power();
}
```


# About

## HTTP Client Lib

For the REST Calls ST4J is using the [Google HTTP Client](https://developers.google.com/api-client-library/java/google-http-java-client/).
The XML Support on this library is beta, but it works like a charm.

This is a helpful example for the XML Usage of the Lib.
- https://github.com/google/google-http-java-client/blob/dev/google-http-client-xml/src/test/java/com/google/api/client/xml/XmlTest.java
- https://stackoverflow.com/questions/14751115/sending-post-request-using-com-google-api-client-http-httprequest-object-in-goog

### XML Parsing

There is an Issue with with XPP on Android and on J2SE, so there is a Hack in SoundTouchApiClient.java
- https://stackoverflow.com/questions/52264672/different-serializing-of-xml-on-android-and-j2se-using-google-http-lib-and-xpp

### Testing HTTP Client

The Google HTTP Client comes with a simple mock, that allows us to test all the request in a nice fashion.
- https://github.com/googleapis/google-http-java-client/blob/dev/google-http-client/src/test/java/com/google/api/client/http/HttpResponseTest.java
- https://github.com/googleapis/google-http-java-client/blob/dev/google-http-client/src/test/java/com/google/api/client/http/HttpRequestTest.java

## SSDP - Simple Service Discovery Protocol

For the SSDP Implementation we use [ResourcePools SSDP](https://github.com/resourcepool/ssdp-client)'s Implementation. If you can to make use of their implementation too, add this

```xml
<dependency>
  <groupId>io.resourcepool</groupId>
  <artifactId>ssdp-client</artifactId>
  <version>2.2.0</version>
</dependency>
```   
 
or on Android

```groovy
implementation 'io.resourcepool:ssdp-client:2.2.0'
```

## Awaitility -- Async Operations easy

For the Async Operations in the Tests there Awaitility is used: https://github.com/awaitility/awaitility

```xml
<dependency>
 <groupId>org.awaitility</groupId>
 <artifactId>awaitility</artifactId>
 <version>3.1.2</version>
 <scope>test</scope>
</dependency>
```

### Usage

Sample method for scanning for devices. 

```java
public static synchronized List<SsdpService> synchronousBlockingDeviceScanner(final int timeToScanInMilliseconds) {
  final String boseUrn = "urn:schemas-upnp-org:device:MediaRenderer:1";
  
  final SsdpClient client = SsdpClient.create();
  final List<SsdpService> servicesFound = new ArrayList<SsdpService>();

  final DiscoveryRequest networkStorageDevice = DiscoveryRequest.builder()
      .serviceType(boseUrn)
      .build();

  client.discoverServices(networkStorageDevice, new DiscoveryListener() {
    @Override
    public void onServiceDiscovered(final SsdpService service) {
      System.out.println("Found service at IP: " + service.getRemoteIp()
          .toString());
      servicesFound.add(service);
    }

    @Override
    public void onServiceAnnouncement(final SsdpServiceAnnouncement announcement) {
      System.out.println("Service announced something: " + announcement);
    }

    @Override
    public void onFailed(final Exception ex) {
      System.out.println("Service onFailed: " + ex.getMessage());
    }
  });

  // ... wait 

  System.out.println("Discovery Stopped and Serives Found: " + servicesFound.size());
  client.stopDiscovery();
  return servicesFound;
}
```

# License

Copyright 2018 Gerald Madlmayr

Licensed under the MIT License (the "License"); you may not use this file except 
in compliance with the License. You may obtain a copy of the License at

```
https://opensource.org/licenses/MIT
```

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
