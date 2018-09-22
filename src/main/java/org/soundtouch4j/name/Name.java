package org.soundtouch4j.name;

import org.soundtouch4j.common.Request;
import com.google.api.client.util.Key;

public class Name implements Request {

  public static final String ELEMENT_NAME = "name";

  @Key("text()")
  private String value;


  public Name(final String value) {
    this.value = value;
  }

  public Name() {
    // Auto Init/Reflection Requires Empty Constructor
  }

}