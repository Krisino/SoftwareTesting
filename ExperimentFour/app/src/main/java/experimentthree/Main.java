package experimentthree;

import java.time.LocalDateTime;

public class Main {

    public String billing(final String startTime, final String endTime, final int startDST, final int endDST) {
        Date date = new Date();
        Payment payment = new Payment();
        LocalDateTime start = LocalDateTime.parse(startTime);
        LocalDateTime end = LocalDateTime.parse(endTime);
        long minute = date.calcMinute(start, startDST, end, endDST);
        if (minute == -1) {
            return "Talking time is wrong!";
        }
        return payment.getPrice(minute);
    }
}
