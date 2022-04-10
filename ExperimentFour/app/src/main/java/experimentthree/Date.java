package experimentthree;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class Date {
    /*
        DST = 0: not DST
        DST = 1: DST
    */
    public long calcMinute(final LocalDateTime startTime, final int SDST, final LocalDateTime endTime, final int EDST) {
        long MAX_MINUTE = 30 * 60;
        long startSecond = startTime.toEpochSecond(ZoneOffset.ofHours(0));
        long endSecond = endTime.toEpochSecond(ZoneOffset.ofHours(0));
        long sec = endSecond - startSecond;
        if (SDST == 0 && EDST == 1) {
            sec -= 60 * 60;
        }
        if (SDST == 1 && EDST == 0) {
            sec += 60 * 60;
        }
        long minute = sec / 60;
        if (sec % 60 != 0) {
            minute++;
        }
        if (minute > MAX_MINUTE || minute < 0) {
            return -1;
        }
        return minute;
    }
}
