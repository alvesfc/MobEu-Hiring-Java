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
import java.util.function.Function;
import java.util.function.Supplier;

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
                        .findFirst()
                        .map(Packer::buildPackageDomain)
                        .orElseThrow((Supplier<Throwable>) () -> new APIException("File is empty!"))
                        .toString();
            }catch (Throwable ex){
                throw new APIException(ex.getMessage());
            }
        } else {
            throw new IOException("File not exists!");
        }
    }

    private static PackageDomain buildPackageDomain(final String line)  {
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

    private static List<ItemDomain> buildItemDomain(final String line)  {
        ItemDomain itemDomain = new ItemDomain();
        List<ItemDomain> itemDomains = new ArrayList<>();
        String[] lineArr = line.split(" ");
        try {
            itemDomain.setId(Integer.parseInt(lineArr[2].split(",")[0].replace("(","")));
        } catch (NumberFormatException ex) {
            throw new NumberFormatException("The item id is not a number!");
        }

        try {
            itemDomain.setWeight(Double.parseDouble(lineArr[2].split(",")[1]));
        } catch (NumberFormatException ex) {
            throw new NumberFormatException("The item weight is not a number!");
        }

        try {
            itemDomain.setCost(Double.parseDouble(lineArr[2].split(",")[2]
                    .replace(")","")
            .replace("€","")));
        } catch (NumberFormatException ex) {
            throw new NumberFormatException("The item cost is not a number!");
        }
        itemDomains.add(itemDomain);

        return itemDomains;
    }
}
