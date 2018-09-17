# Java API for Bose SoundTouch API - SoundTouch4J (st4j)

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
  <version>1.0.0-SNAPSHOT</version>
</dependency>
```

This will add the [Google HTTP Client](https://developers.google.com/api-client-library/java/google-http-java-client/) and the XML Implmenetation
to your project

### Usage in an Android Project

Similar to the Maven Project, we include the JAR, but exclude some binaries that are shipped with the Google Client Lib.

```ruby
implementation ('org.soundtouch4j:soundtouch4j-api:1.0.0-SNAPSHOT') {
  exclude  module: 'httpclient'
  exclude  module: 'xpp3'
  exclude   module: 'commons-logging'
}
```

# Tech behind the scene

## HTTP Client Lib

For the REST Calls I'm trying the Google HTTP Client Library. I've not used this before, but I want 
to try something new anyway. It also supports XML as Body Format (but is beta), but hey: no risk no fun.

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

```
implementation 'io.resourcepool:ssdp-client:2.2.0'
```