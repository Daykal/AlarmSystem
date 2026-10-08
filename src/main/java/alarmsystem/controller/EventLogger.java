package alarmsystem.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventLogger {
    public static final DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public void logEvent(String message) {
        String timeStamp = LocalDateTime.now().format(timeFormatter);
        System.out.println(timeStamp + ", " + message);
    }
}
