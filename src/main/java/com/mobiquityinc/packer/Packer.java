package com.mobiquityinc.packer;

import com.mobiquityinc.domain.ItemDomain;
import com.mobiquityinc.domain.PackageDomain;
import com.mobiquityinc.domain.ResultDomain;
import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.extract.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

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
        ExtractInfo<PackageDomain> packageDomainExtract = new PackageDomainExtract();

        if (path.toFile().exists()) {
            try {
                StringBuilder  sb= new StringBuilder();
                 Files.lines(Paths.get(filePath))
                        .map(packageDomainExtract::extract)
                        .map(packageDomain -> {
                            ResultDomain resultDomain = new ResultDomain(packageDomain.getLine(),packageDomain.getLimit());
                            packageDomain.getItems()
                                    .stream()
                                    .sorted((item1, item2) -> Double.compare(item2.getWeight(), item1.getWeight()))
                                    .forEach(itemDomain -> resultDomain.addItem(itemDomain));
                            return resultDomain;
                        })
                        .map(ResultDomain::result)
                        .forEach(new Consumer<String>() {
                            @Override
                            public void accept(String s) {
                                sb.append(s);
                                sb.append("\n");
                            }
                        });

                 return sb.toString();
            } catch (Throwable ex) {
                throw new APIException(ex.getMessage());
            }
        } else {
            throw new IOException("File not exists!");
        }
    }
}
