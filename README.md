# soundtouch4j

Java API for Bose Soundtouch API

API Documentation
- https://developer.bose.com/soundtouch-control-api/apis

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