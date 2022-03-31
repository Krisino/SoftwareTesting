package experimentthree;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class Date {
    /*
        DST = 0: normal time
        DST = 1: Summer DST
    */
    public long calcMinute(LocalDateTime startTime, int SDST, LocalDateTime endTime, int EDST) {
        int MAX_MINUTE = 30 * 60;
        long startSecond = startTime.toEpochSecond(ZoneOffset.ofHours(0));
        long endSecond = endTime.toEpochSecond(ZoneOffset.ofHours(0));
        long sec = endSecond - startSecond;
        long minute = sec / 60;
        if (sec % 60 != 0) {
            minute++;
        }
        if (SDST == 0 && EDST == 1) {
            minute -= 60;
        } else if (SDST == 1 && EDST == 0) {
            minute += 60;
        }
        if (minute > MAX_MINUTE) {
            return MAX_MINUTE;
        } else if (minute <= 0) {
            return 0;
        }
        return minute;
    }
}
