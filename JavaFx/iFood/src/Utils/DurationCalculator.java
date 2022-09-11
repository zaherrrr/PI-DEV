package Utils;

import java.sql.Timestamp;
import java.util.Calendar;

public class DurationCalculator {
    public static int[] DurationCalculator(Timestamp timestamp2) {
        java.util.Date date = new java.util.Date();
        Timestamp timestamp1 = new Timestamp(date.getTime());
        long milliseconds = timestamp1.getTime() - timestamp2.getTime();
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(milliseconds);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        int hr = c.get(Calendar.HOUR);
        int min = c.get(Calendar.MINUTE);
        int sec = c.get(Calendar.SECOND);
        return new int[]{mMonth, mDay, hr, min, sec};
    }
}
