import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InvoiceServiceTest {
    InvoiceGenerator invoiceGenerator = null;

    @Before
    public void setUp() throws Exception {
        invoiceGenerator = new InvoiceGenerator();
    }

    @Test
    public void givenDistanceAndTime_whenSufficient_shouldReturnTotalFare() {
        double distance = 2.0;
        int time = 5;
        double fare = invoiceGenerator.calculateFare(distance, time);
        Assert.assertEquals(25, fare, 0.0);
    }

    @Test
    public void givenDistanceAndTime_whenLess_shouldReturnMinimumFare() {
        double distance = 0.2;
        int time = 1;
        double fare = invoiceGenerator.calculateFare(distance, time);
        Assert.assertEquals(5, fare, 0.0);
    }

    @Test
    public void givenDistanceAndTime_whenForMultipleRides_shouldReturnTotalFare() {
        Ride[] rides = {new Ride(2.0, 5),
                        new Ride(0.1, 5),
                        new Ride(0.1,1)
                        };
        double fare = invoiceGenerator.calculateFare(rides);
        Assert.assertEquals(36, fare, 0.0);
    }
}
