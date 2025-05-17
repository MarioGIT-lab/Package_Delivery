package Rocxoiu_Mario;

import Rocxoiu_Mario.model.DeliveryPackage;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        PackageLoader loader = new PackageLoader();
        List<DeliveryPackage> packages = loader.loadPackages("packages.txt");

        DeliveryService service = new DeliveryService(packages);
        service.processDeliveries();
    }
}
