package org.soundtouch4j.zone;

import java.util.List;
import org.soundtouch4j.common.Request;
import org.soundtouch4j.common.Response;
import com.google.api.client.util.Key;

public class Zone implements Response, Request {

  public static final String ELEMENT_NAME = "zone";

  @Key("@master")
  private String master;

  @Key("member")
  private List<ZoneMember> member;


  public String getDeviceID() {
    return master;
  }

  public List<ZoneMember> getMember() {
    return member;
  }

  @Override
  public String toString() {
    return "Zone{" + "master='" + master + '\'' + ", member=" + member + '}';
  }
}
