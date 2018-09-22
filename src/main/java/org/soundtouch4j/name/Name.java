package org.soundtouch4j.name;

import org.soundtouch4j.common.Request;
import com.google.api.client.util.Key;

public class Name implements Request {

  public static final String ELEMENT_NAME = "name";

  @Key("text()")
  private String name;


  public Name(final String name) {
    this.name = name;
  }

  public Name() {
    // Auto Init/Reflection Requires Empty Constructor
  }

  public static String getElementName() {
    return ELEMENT_NAME;
  }

  @Override
  public String toString() {
    return "Name{" + "name='" + name + '\'' + '}';
  }
}