/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author francisco trigo
 */
public class timeConvert {
    
    public static String toUTC(String dateTime) {
        Timestamp timestamp = Timestamp.valueOf(dateTime);

      
        LocalDateTime ldt = timestamp.toLocalDateTime();
        ZonedDateTime zdt = ldt.atZone(ZoneId.of(ZoneId.systemDefault().toString()));
        ZonedDateTime utczdt = zdt.withZoneSameInstant(ZoneId.of("UTC"));
        LocalDateTime ldtIn = utczdt.toLocalDateTime();
        String finishUTC =ldtIn.format(DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss"));
        return finishUTC;
    }
    
    public static String toLocal(String dateTime) {
        Timestamp timestamp = Timestamp.valueOf(dateTime);
        LocalDateTime ldt = timestamp.toLocalDateTime();
        ZonedDateTime zdtOut = ldt.atZone(ZoneId.of("UTC"));
        ZonedDateTime zdtOutToLocalTZ = zdtOut.withZoneSameInstant(ZoneId.of(ZoneId.systemDefault().toString()));
        LocalDateTime ldOutFinal = zdtOutToLocalTZ.toLocalDateTime();
        String finishLocal =ldOutFinal.format(DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss"));
        return finishLocal;
    }
    
    
    
    
}
