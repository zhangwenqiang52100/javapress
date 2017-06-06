package com.zwq.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by Lancer on 2017/5/13.
 */
public class DateUtil {

  // 06. java.time.LocalTime --> java.util.Date
  public static Date LocalTimeToUdate(LocalTime localTime) {
    LocalDate localDate = LocalDate.now();
    LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
    ZoneId zone = ZoneId.systemDefault();
    Instant instant = localDateTime.atZone(zone).toInstant();
    Date date = Date.from(instant);
    return date;
  }
}
