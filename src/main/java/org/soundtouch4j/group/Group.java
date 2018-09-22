package org.soundtouch4j.group;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.soundtouch4j.common.Response;
import com.google.api.client.util.Key;

public class Group implements Response {

  private static final String GROUP_ROLE = "groupRole";

  @Key("@id")
  private String id;

  @Key("name")
  private String name;

  @Key("masterDeviceId")
  private String masterDeviceId;

  @Key
  private Map<String, List<GroupRole>> roles;

  @Key("status")
  private String status;

  public Group() {
    // Auto Init/Reflection Requires Empty Constructor
  }

  public String getName() {
    return name;
  }

  public String getMasterDeviceId() {
    return masterDeviceId;
  }

  public List<GroupRole> getGroupRoles() {
    if ((roles.get(GROUP_ROLE) == null) || roles.get(GROUP_ROLE)
        .isEmpty()) {
      return new ArrayList<GroupRole>();
    }

    return new ArrayList<GroupRole>(roles.get(GROUP_ROLE));
  }

  public String getStatus() {
    return status;
  }

  public String getId() {
    return id;
  }

  @Override
  public String toString() {
    return "Group{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", masterDeviceId='" + masterDeviceId + '\'' + ", roles=" + roles + ", status='" + status + '\'' + '}';
  }
}
