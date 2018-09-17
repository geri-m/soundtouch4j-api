package org.soundtouch4j.common;

import java.util.ArrayList;
import java.util.List;
import com.google.api.client.util.Key;

public class ErrorResponse implements Response {

  @Key("error")
  private List<Error> errorList;

  public ErrorResponse() {
  }

  public List<Error> getErrorList() {
    return new ArrayList<Error>(errorList);
  }

  @Override
  public String toString() {
    return "ErrorResponse{" + "error=" + errorList + '}';
  }
}
