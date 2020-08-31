public class InvoiceGanerator {

    private static final double MINIMUM_COST_PER_KILOMETER = 10;
    private static final int COST_PER_MINUTE = 1;

    public double calculateFare(double distance, int time) {
        return distance * MINIMUM_COST_PER_KILOMETER + time * COST_PER_MINUTE;
    }
}
