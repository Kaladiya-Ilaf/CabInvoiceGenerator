public class InvoiceService {
    private static final double MINIMUM_COST_PER_KILOMETER = 10;
    private static final int COST_PER_MINUTE = 1;
    private static final double MINIMUM_FARE = 5;
    private RideRepository rideRepository;

    public void setRideRepository(RideRepository rideRepository){
        this.rideRepository = rideRepository;
    }
    public double calculateFare(double distance, int time) {
        double totalFare = distance * MINIMUM_COST_PER_KILOMETER + time * COST_PER_MINUTE;
        return Math.max(totalFare, MINIMUM_FARE);
    }

    public InvoiceSummary calculateTotalFare(Ride[] rides) {
        double totalFare = 0;
        for (Ride ride : rides){
            totalFare += ride.cabRide.calculateCostOfCabRide(ride);
        }
        return new InvoiceSummary(rides.length, totalFare);
    }


    public void addRides(String userId, Ride[] rides) {
        rideRepository.addRide(userId, rides);
    }

    public InvoiceSummary getInvoiceSummary(String userId) {
        Ride[] rides = rideRepository.getRides(userId);
        return this.calculateTotalFare(rides);
    }
}
