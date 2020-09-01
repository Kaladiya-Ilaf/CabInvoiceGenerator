import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InvoiceServiceTest {
    InvoiceService invoiceService = null;
    Ride[] rides = null;
    InvoiceSummary expectedInvoiceSummary = null;

    @Before
    public void setUp(){
        invoiceService = new InvoiceService();
        RideRepository rideRepository = new RideRepository();
        invoiceService.setRideRepository(rideRepository);
        rides = new Ride[]{
                new Ride(CabRide.NORMAL, 2.0, 5),
                new Ride(CabRide.PREMIUM,2.0, 2),
        };
        expectedInvoiceSummary =  new InvoiceSummary(2, 59.0);
    }

    @Test
    public void givenDistanceAndTime_whenSufficient_shouldReturnTotalFare() {
        double distance = 2.0;
        int time = 5;
        double fare = invoiceService.calculateFare(distance, time);
        Assert.assertEquals(25, fare, 0.0);
    }

    @Test
    public void givenDistanceAndTime_whenLess_shouldReturnMinimumFare() {
        double distance = 0.2;
        int time = 1;
        double fare = invoiceService.calculateFare(distance, time);
        Assert.assertEquals(5, fare, 0.0);
    }

    @Test
    public void givenDistanceAndTime_whenForMultipleRides_shouldReturnInvoiceSummary() {
        InvoiceSummary summary = invoiceService.calculateTotalFare(rides);
        Assert.assertEquals(expectedInvoiceSummary, summary);
    }

    @Test
    public void givenUserIDAndRides_shouldReturnInvoiceSummary() {
        String userId = "a@i.com";
        invoiceService.addRides(userId, rides);
        InvoiceSummary summary = invoiceService.getInvoiceSummary(userId);
        Assert.assertEquals(expectedInvoiceSummary, summary);
    }
}
