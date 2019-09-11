package com.mobiquityinc.extract;


import com.mobiquityinc.domain.ItemDomain;
import com.mobiquityinc.domain.PackageDomain;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author alvesfc
 * @version 1.0
 */
public class PackageDomainExtract implements ExtractInfo<PackageDomain> {

    private static AtomicInteger lineNumber = new AtomicInteger();

    @Override
    public PackageDomain extract(final String value) {
        PackageDomain packageDomain = new PackageDomain();
        ExtractInfo<List<ItemDomain>> itemsDomainExtract = new ItemsDomainExtract();

        try {
            String[] lineArr = value.split(":");
            packageDomain.setLimit(Double.parseDouble(lineArr[0].trim()));
        } catch (NumberFormatException ex) {
            throw new NumberFormatException("The package limit is not a number!");
        }

        if(packageDomain.getLimit() > 100){
            throw new IllegalArgumentException("The package limit is can not be more than 100!");
        }
        packageDomain.setLine(lineNumber.incrementAndGet());
        packageDomain.setItems(itemsDomainExtract.extract(value));

        return packageDomain;
    }
}
