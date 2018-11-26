package org.soundtouch4j.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.google.api.client.util.Key;

public class ErrorResponse implements Response, Serializable {

  private static final long serialVersionUID = 1L;

  @Key("error")
  private List<Error> errorList;

  public ErrorResponse() {
    // Auto Init/Reflection Requires Empty Constructor
  }

  public List<Error> getErrorList() {
    return new ArrayList<Error>(errorList);
  }

}
