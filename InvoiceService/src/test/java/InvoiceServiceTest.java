import org.junit.Assert;
import org.junit.Test;

public class InvoiceServiceTest {
    @Test
    public void givenDistanceAndTime_shouldReturnTotalFare() {
        InvoiceGanerator invoiceGanerator = new InvoiceGanerator();
        double distance = 2.0;
        int time = 5;
        double fare = invoiceGanerator.calculateFare(distance, time);
        Assert.assertEquals(25, fare, 0.0);
    }

    @Test
    public void givenDistanceAndTime_whenLess_shouldReturnMinimumFare() {
        InvoiceGanerator invoiceGanerator = new InvoiceGanerator();
        double distance = 0.2;
        int time = 1;
        double fare = invoiceGanerator.calculateFare(distance, time);
        Assert.assertEquals(5, fare, 0.0);
    }
}
