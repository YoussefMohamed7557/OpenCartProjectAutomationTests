package test_cases.youssef.test_utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static String generateCurrentDateAndTime() {
        return new SimpleDateFormat("ddMMyyyyHHmmssSSS").format(new Date());
    }
}
