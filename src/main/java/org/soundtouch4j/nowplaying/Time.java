package org.soundtouch4j.nowplaying;

import com.google.api.client.util.Key;

public class Time {

  @Key("@text()")
  private int value;

  @Key("@total")
  private int total;

  public Time() {

  }

  public int getValue() {
    return value;
  }

  public int getTotal() {
    return total;
  }

  @Override
  public String toString() {
    return "Time{" + "value=" + value + ", total=" + total + '}';
  }
}
