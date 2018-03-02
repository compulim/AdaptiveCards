/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.microsoft.adaptivecards.objectmodel;

public final class DateTimePreparsedTokenFormat {
  public final static DateTimePreparsedTokenFormat RegularString = new DateTimePreparsedTokenFormat("RegularString", AdaptiveCardObjectModelJNI.DateTimePreparsedTokenFormat_RegularString_get());
  public final static DateTimePreparsedTokenFormat Time = new DateTimePreparsedTokenFormat("Time");
  public final static DateTimePreparsedTokenFormat DateCompact = new DateTimePreparsedTokenFormat("DateCompact");
  public final static DateTimePreparsedTokenFormat DateShort = new DateTimePreparsedTokenFormat("DateShort");
  public final static DateTimePreparsedTokenFormat DateLong = new DateTimePreparsedTokenFormat("DateLong");

  public final int swigValue() {
    return swigValue;
  }

  public String toString() {
    return swigName;
  }

  public static DateTimePreparsedTokenFormat swigToEnum(int swigValue) {
    if (swigValue < swigValues.length && swigValue >= 0 && swigValues[swigValue].swigValue == swigValue)
      return swigValues[swigValue];
    for (int i = 0; i < swigValues.length; i++)
      if (swigValues[i].swigValue == swigValue)
        return swigValues[i];
    throw new IllegalArgumentException("No enum " + DateTimePreparsedTokenFormat.class + " with value " + swigValue);
  }

  private DateTimePreparsedTokenFormat(String swigName) {
    this.swigName = swigName;
    this.swigValue = swigNext++;
  }

  private DateTimePreparsedTokenFormat(String swigName, int swigValue) {
    this.swigName = swigName;
    this.swigValue = swigValue;
    swigNext = swigValue+1;
  }

  private DateTimePreparsedTokenFormat(String swigName, DateTimePreparsedTokenFormat swigEnum) {
    this.swigName = swigName;
    this.swigValue = swigEnum.swigValue;
    swigNext = this.swigValue+1;
  }

  private static DateTimePreparsedTokenFormat[] swigValues = { RegularString, Time, DateCompact, DateShort, DateLong };
  private static int swigNext = 0;
  private final int swigValue;
  private final String swigName;
}

