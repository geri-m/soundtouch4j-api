package org.soundtouch4j.zone;

import java.util.ArrayList;
import java.util.List;
import org.soundtouch4j.common.Request;
import org.soundtouch4j.common.Response;
import com.google.api.client.util.Key;

public class Zone implements Response, Request {

  public static final String ELEMENT_NAME = "zone";

  @Key("@master")
  private String macAddress;

  @Key("member")
  private List<ZoneMember> members;

  public Zone() {
  }

  public Zone(final String macAddress) {
    this.macAddress = macAddress;
  }

  public synchronized void addMember(final ZoneMember member) {
    if (members == null) {
      members = new ArrayList<ZoneMember>();
    }
    members.add(member);
  }

  public String getDeviceID() {
    return macAddress;
  }

  public List<ZoneMember> getMembers() {
    return members;
  }

  @Override
  public String toString() {
    return "Zone{" + "macAddress='" + macAddress + '\'' + ", members=" + members + '}';
  }
}
