import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InvoiceServiceTest {
    InvoiceService invoiceService = null;

    @Before
    public void setUp(){
        invoiceService = new InvoiceService();
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
        Ride[] rides = {new Ride(2.0, 5),
                        new Ride(0.1, 5),
                        new Ride(0.1,1)
        };
        InvoiceSummary summary = invoiceService.calculateFare(rides);
        InvoiceSummary expectedInvoiceSummary =  new InvoiceSummary(3, 36.0);
        Assert.assertEquals(expectedInvoiceSummary, summary);
    }

    @Test
    public void givenUserIDAndRides_shouldReturnInvoiceSummary() {
        String userId = "a@i.com";
        Ride[] rides = {new Ride(2.0, 5),
                new Ride(0.1, 5),
                new Ride(0.1,1)
        };
        invoiceService.addRides(userId, rides);
        InvoiceSummary summary = invoiceService.getInvoiceSummary(userId);
        InvoiceSummary expectedInvoiceSummary =  new InvoiceSummary(3, 36.0);
        Assert.assertEquals(expectedInvoiceSummary, summary);
    }
}
