package com.mobiquityinc.packer;

import com.mobiquityinc.domain.ItemDomain;
import com.mobiquityinc.domain.PackageDomain;
import com.mobiquityinc.exception.APIException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * The class is responsible to process a file.
 *
 * @author alvesfc
 * @version 1.0
 */
public class Packer {

    private Packer() {
    }

    /**
     * The method is responsible to process a file with all information.
     *
     * @param filePath - String with file location.
     * @return {@link String} with processing return.
     * @throws APIException - Exception always that finding a constraint.
     */
    public static String pack(String filePath) throws APIException, IOException {
        Path path = Paths.get(filePath);
        if (path.toFile().exists()) {

            try {
                return Files.lines(Paths.get(filePath))
                        .map(Packer::buildPackageDomain)
                        .collect(Collectors.toList())
                        .toString();
            } catch (Throwable ex) {
                throw new APIException(ex.getMessage());
            }
        } else {
            throw new IOException("File not exists!");
        }
    }

    private static PackageDomain buildPackageDomain(final String line) {
        PackageDomain packageDomain = new PackageDomain();

        try {
            String[] lineArr = line.split(":");
            packageDomain.setLimit(Double.parseDouble(lineArr[0].trim()));
        } catch (NumberFormatException ex) {
            throw new NumberFormatException("The package limit is not a number!");
        }
        packageDomain.setItems(buildItemDomain(line));
        return packageDomain;
    }

    private static List<ItemDomain> buildItemDomain(final String line) {
        List<ItemDomain> itemDomains = new ArrayList<>();
        String[] lineArr = line.split(" ");

        for (int i = 2; i < lineArr.length; i++) {
            ItemDomain itemDomain = new ItemDomain();
            itemDomain.setId(buildItemID(lineArr[i]));
            itemDomain.setWeight(buildItemWeight(lineArr[i]));
            itemDomain.setCost(buildItemCost(lineArr[i]));
            itemDomains.add(itemDomain);
        }

        return itemDomains;
    }

    private static Integer buildItemID(String s) {
        try {
            return Integer.parseInt(s.split(",")[0].replace("(", ""));
        } catch (NumberFormatException ex) {
            throw new NumberFormatException("The item id is not a number!");
        }
    }

    private static Double buildItemWeight(String s) {
        try {
            Double value = Double.parseDouble(s.split(",")[1]);

            if(value > 100D){
                throw new IllegalArgumentException("The item weight is can not be more than 100!");
            }

            return value;
        } catch (NumberFormatException ex) {
            throw new NumberFormatException("The item weight is not a number!");
        }
    }

    private static Double buildItemCost(String s) {
        try {
            Double value =Double.parseDouble(s.split(",")[2]
                    .replace(")", "")
                    .replace("€", ""));

            if(value > 100D){
                throw new IllegalArgumentException("The item cost is can not be more than 100!");
            }

            return value;
        } catch (NumberFormatException ex) {
            throw new NumberFormatException("The item cost is not a number!");
        }
    }
}
