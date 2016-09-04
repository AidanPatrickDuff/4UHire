package com.ait.beans;

import java.util.Date;

public class DateUtils {
  /** Given a Date, returns a String "Day, Month Number, Year", 
   *  e.g. "Wednesday, November 2, 2011". For results page.
   */
  public static String formatDate(Date date) {
    if (date == null) {
      return("");
    } else {
      return(String.format("%tA, %tB %te, %tY", 
                           date, date, date, date));
    }
  }
  public DateUtils() {} // Class cannot be instantiated
}
