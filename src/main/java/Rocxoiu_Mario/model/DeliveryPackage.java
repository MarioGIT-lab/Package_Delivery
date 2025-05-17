package Rocxoiu_Mario.model;

import java.time.LocalDate;

public class DeliveryPackage {
    private final String location;
    private final int distance;
    private final int value;
    private final LocalDate deliveryDate;

    public DeliveryPackage(String location, int distance, int value, LocalDate deliveryDate) {
        this.location = location;
        this.distance = distance;
        this.value = value;
        this.deliveryDate = deliveryDate;
    }

    public String getLocation() {
        return location;
    }

    public int getDistance() {
        return distance;
    }

    public int getValue() {
        return value;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    @Override
    public String toString() {
        return "DeliveryPackage{" +
                "location='" + location + '\'' +
                ", distance=" + distance +
                ", value=" + value +
                ", deliveryDate=" + deliveryDate +
                '}';
    }
}
