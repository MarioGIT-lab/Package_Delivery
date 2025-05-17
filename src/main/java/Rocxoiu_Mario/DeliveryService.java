package Rocxoiu_Mario;

import Rocxoiu_Mario.model.DeliveryPackage;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class DeliveryService {
    private final List<DeliveryPackage> packages;
    private double totalValue = 0;
    private double totalRevenue = 0;

    public DeliveryService(List<DeliveryPackage> packages) {
        this.packages = packages;
    }

    public void processDeliveries() throws InterruptedException {
        Map<String, Map<LocalDate, List<DeliveryPackage>>> grouped = packages.stream()
                .collect(Collectors.groupingBy(DeliveryPackage::getLocation,
                        Collectors.groupingBy(DeliveryPackage::getDeliveryDate)));

        ExecutorService executor = Executors.newFixedThreadPool(10);
        List<Future<?>> futures = new ArrayList<>();

        for (Map.Entry<String, Map<LocalDate, List<DeliveryPackage>>> locationEntry : grouped.entrySet()) {
            String location = locationEntry.getKey();
            Map<LocalDate, List<DeliveryPackage>> dateMap = locationEntry.getValue();

            for (Map.Entry<LocalDate, List<DeliveryPackage>> dateEntry : dateMap.entrySet()) {
                LocalDate date = dateEntry.getKey();
                List<DeliveryPackage> group = dateEntry.getValue();

                int distance = group.get(0).getDistance();

                futures.add(executor.submit(() -> {
                    try {
                        System.out.println("[Delivering for " + location + " and date " + date + " in " + distance + " seconds]");
                        TimeUnit.SECONDS.sleep(distance);

                        int groupValue = group.stream().mapToInt(DeliveryPackage::getValue).sum();
                        double groupRevenue = distance;

                        synchronized (this) {
                            totalValue += groupValue;
                            totalRevenue += groupRevenue;
                        }
                    } catch (Exception e) {
                        System.err.println("Error in delivery thread for " + location + " on " + date + ": " + e.getMessage());
                        e.printStackTrace();
                    }
                }));
            }
        }

        for (Future<?> future : futures) {
            try {
                future.get();
            } catch (ExecutionException e) {
                System.err.println("Execution error: " + e.getMessage());
                e.printStackTrace();
            }
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.HOURS);

        System.out.println("\n--- Delivery Summary ---");
        System.out.println("Total Package Value: " + totalValue);
        System.out.println("Total Revenue: " + totalRevenue);
    }
}

