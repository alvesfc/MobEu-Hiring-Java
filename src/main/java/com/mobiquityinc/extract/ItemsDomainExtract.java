package com.mobiquityinc.extract;


import com.mobiquityinc.domain.ItemDomain;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that extract Items from line.
 * @author alvesfc
 * @version 1.0
 */
public class ItemsDomainExtract implements ExtractInfo<List<ItemDomain>> {

    private static final String CAN_NOT_HAVE_MORE_THAN_15_ITEMS = "Can not have more than 15 items!";
    private static final int MAX_ITEMS = 15;
    private static final String EMPTY = " ";

    @Override
    public List<ItemDomain> extract(final String value) {
        List<ItemDomain> itemDomains = new ArrayList<>();
        String[] lineArr = value.split(EMPTY);
        ExtractInfo<Double> itemCostExtract = new ItemCostExtract();
        ExtractInfo<Double> itemWeightExtract = new ItemWeightExtract();
        ExtractInfo<Integer> itemIDExtract = new ItemIDExtract();

        for (int i = 2; i < lineArr.length; i++) {
            ItemDomain itemDomain = new ItemDomain();
            itemDomain.setId(itemIDExtract.extract(lineArr[i]));
            itemDomain.setWeight(itemWeightExtract.extract(lineArr[i]));
            itemDomain.setCost(itemCostExtract.extract(lineArr[i]));
            itemDomains.add(itemDomain);
        }

        if(itemDomains.size() > MAX_ITEMS){
            throw new IllegalArgumentException(CAN_NOT_HAVE_MORE_THAN_15_ITEMS);
        }

        return itemDomains;
    }
}
