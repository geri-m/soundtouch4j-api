package org.soundtouch4j.key;

/**
 * Enum for the current state of a button of the Sound Touch
 */

public enum KeyStateEnum {

  // the key states _MUST_ be lower case.
  PRESS("press"),
  RELEASE("release");

  private final String value;

  KeyStateEnum(final String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
