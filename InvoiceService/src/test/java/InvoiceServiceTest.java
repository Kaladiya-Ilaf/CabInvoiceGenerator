import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class InvoiceServiceTest {

    @Mock
    InvoiceService invoiceService;
    InvoiceSummary expectedInvoiceSummary;
    private String userId = "a@i.com";
    Ride[] rides;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        invoiceService = new InvoiceService();
        RideRepository rideRepository = new RideRepository();
        invoiceService.setRideRepository(rideRepository);
        rides = new Ride[]{
                new Ride(CabRide.NORMAL, 2.0, 5),
                new Ride(CabRide.PREMIUM, 2.0, 2),
        };
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
        expectedInvoiceSummary = new InvoiceSummary(2, 59.0);
        Assert.assertEquals(expectedInvoiceSummary, summary);
    }

    @Test
    public void givenDistanceAndTime_whenForMultipleRides_shouldReturnIncorrectInvoiceSummary() {
        InvoiceSummary summary = invoiceService.calculateTotalFare(rides);
        expectedInvoiceSummary = new InvoiceSummary(2, 9.0);
        Assert.assertNotEquals(expectedInvoiceSummary, summary);
    }

    @Test
    public void givenUserIDAndRides_shouldReturnInvoiceSummary() {
        invoiceService.addRides(userId, rides);
        InvoiceSummary summary = invoiceService.getInvoiceSummary(userId);
        expectedInvoiceSummary = new InvoiceSummary(2, 59.0);
        Assert.assertEquals(expectedInvoiceSummary, summary);
    }

    @Test
    public void givenUserIDAndRides_shouldReturnIncorrectInvoiceSummary() {
        invoiceService.addRides(userId, rides);
        InvoiceSummary summary = invoiceService.getInvoiceSummary(userId);
        expectedInvoiceSummary = new InvoiceSummary(3, 51.0);
        Assert.assertNotEquals(expectedInvoiceSummary, summary);
    }



}
