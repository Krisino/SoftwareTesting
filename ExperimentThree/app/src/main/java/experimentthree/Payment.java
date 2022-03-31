package experimentthree;

public class Payment {

    public double getPrice(long minute) {
        double money;
        double BASEPRICE = 0.05;
        double STAGE = 20;
        if (minute >= STAGE) {
            double UPPERPRICE = 0.1;
            money = STAGE * BASEPRICE + (minute - STAGE) * UPPERPRICE;
        } else {
            money = minute * BASEPRICE;
        }
        return money;
    }
}
