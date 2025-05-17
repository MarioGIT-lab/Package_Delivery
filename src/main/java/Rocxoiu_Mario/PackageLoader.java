package Rocxoiu_Mario;

import Rocxoiu_Mario.model.DeliveryPackage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PackageLoader {

    public List<DeliveryPackage> loadPackages(String resourceFileName) throws Exception {
        List<DeliveryPackage> packages = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(getClass().getClassLoader().getResourceAsStream(resourceFileName)))) {

            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length != 4) continue;

                String location = parts[0];
                int distance = Integer.parseInt(parts[1]);
                int value = Integer.parseInt(parts[2]);
                LocalDate date = LocalDate.parse(parts[3]);

                packages.add(new DeliveryPackage(location, distance, value, date));
            }
        }

        return packages;
    }
}
