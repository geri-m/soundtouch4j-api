package name;

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
  }

  public static String getElementName() {
    return ELEMENT_NAME;
  }

  @Override
  public String toString() {
    return "Name{" + "name='" + name + '\'' + '}';
  }
}