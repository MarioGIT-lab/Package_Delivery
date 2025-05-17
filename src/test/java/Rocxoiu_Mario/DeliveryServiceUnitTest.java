package Rocxoiu_Mario;

import Rocxoiu_Mario.model.DeliveryPackage;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class DeliveryServiceUnitTest {

    @Test
    public void testProcessDeliveries() throws InterruptedException {
        List<DeliveryPackage> packages = Arrays.asList(
                new DeliveryPackage("TestTown", 1, 100, LocalDate.of(2023, 1, 1)),
                new DeliveryPackage("TestTown", 1, 200, LocalDate.of(2023, 1, 1))
        );

        DeliveryService service = new DeliveryService(packages);
        service.processDeliveries();
    }
}
