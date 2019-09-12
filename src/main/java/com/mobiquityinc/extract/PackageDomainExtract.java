package com.mobiquityinc.extract;


import com.mobiquityinc.domain.ItemDomain;
import com.mobiquityinc.domain.PackageDomain;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Class that extract package value from line.
 * @author alvesfc
 * @version 1.0
 */
public class PackageDomainExtract implements ExtractInfo<PackageDomain> {

    private static final String THE_PACKAGE_LIMIT_IS_NOT_A_NUMBER = "The package limit is not a number!";
    private static final String THE_PACKAGE_LIMIT_IS_CAN_NOT_BE_MORE_THAN_100 = "The package limit is can not be more than 100!";
    private static final int MAX_PACKAGE = 100;

    private static AtomicInteger lineNumber = new AtomicInteger();

    @Override
    public PackageDomain extract(final String value) {
        PackageDomain packageDomain = new PackageDomain();
        ExtractInfo<List<ItemDomain>> itemsDomainExtract = new ItemsDomainExtract();

        try {
            String[] lineArr = value.split(":");
            packageDomain.setLimit(Double.parseDouble(lineArr[0].trim()));
        } catch (NumberFormatException ex) {
            throw new NumberFormatException(THE_PACKAGE_LIMIT_IS_NOT_A_NUMBER);
        }

        if(packageDomain.getLimit() > MAX_PACKAGE){
            throw new IllegalArgumentException(THE_PACKAGE_LIMIT_IS_CAN_NOT_BE_MORE_THAN_100);
        }
        packageDomain.setLine(lineNumber.incrementAndGet());
        packageDomain.setItems(itemsDomainExtract.extract(value));

        return packageDomain;
    }
}
