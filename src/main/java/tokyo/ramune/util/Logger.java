package tokyo.ramune.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Logger {

    private static final Calendar cal = Calendar.getInstance();
    private static final SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d, H/m/s");

    public static void info(String message) {
        System.out.println("[" + sdf.format(cal.getTime()) + "] [INFO]" + message);
    }

    public static void warn(String message) {
        System.out.println("[" + sdf.format(cal.getTime()) + "]" + ConsoleColor.RED + "[WARNING]" + message);
    }
}
