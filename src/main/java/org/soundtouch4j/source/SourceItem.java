package org.soundtouch4j.source;

import org.soundtouch4j.common.SourceEnum;
import com.google.api.client.util.Key;

public class SourceItem {

  @Key("@source")
  private SourceEnum source;

  @Key("@status")
  private SourceStatusEnum status;

  @Key("@sourceAccount")
  private String sourceAccount;

  @Key("@isLocal")
  private boolean isLocal;

  @Key("@multiroomallowed")
  private boolean multiroomallowed;

  @Key("text()")
  private String value;

  public SourceItem() {
    // Auto Init/Reflection Requires Empty Constructor
  }

  public SourceEnum getSource() {
    return source;
  }

  public SourceStatusEnum getStatus() {
    return status;
  }

  public String getSourceAccount() {
    return sourceAccount;
  }

  public boolean isLocal() {
    return isLocal;
  }

  public boolean isMultiroomallowed() {
    return multiroomallowed;
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return "SourceItem{" + "source=" + source + ", status=" + status + ", sourceAccount='" + sourceAccount + '\'' + ", isLocal=" + isLocal + ", multiroomallowed=" + multiroomallowed + ", value='" + value + '\'' + '}';
  }
}
