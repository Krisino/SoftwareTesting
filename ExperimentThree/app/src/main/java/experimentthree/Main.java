package experimentthree;

import java.time.LocalDateTime;

public class Main {

    public double billing(String startTime, String endTime, int startDST, int endDST) {
        Date date = new Date();
        Payment payment = new Payment();
        LocalDateTime start = LocalDateTime.parse(startTime);
        LocalDateTime end = LocalDateTime.parse(endTime);
        long minute = date.calcMinute(start, startDST, end, endDST);
        if (minute == -1) {
            return 0.00;
        }
        return payment.getPrice(minute);
    }
}
