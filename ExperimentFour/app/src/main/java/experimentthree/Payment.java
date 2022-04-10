package experimentthree;

public class Payment {
    public String getPrice(final long minute) {
        double money = 0.00;
        double PRICE = 0.05;
        if (minute < 20) {
            money = minute * PRICE;
        } else {
            double UPPERPRICE = 0.1;
            money = 20 * PRICE + (minute - 20) * UPPERPRICE;
        }

        return String.format("%.2f", money);
    }
}
