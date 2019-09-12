package com.mobiquityinc.extract;

import com.mobiquityinc.domain.ItemDomain;
import com.mobiquityinc.domain.PackageDomain;

import java.util.List;

public class AbstractTest {

    protected PackageDomain getPackageDomain(final Double limit, final List<ItemDomain> items) {
        PackageDomain domain = new PackageDomain();
        domain.setLimit(limit);
        domain.setItems(items);
        return domain;
    }

    protected ItemDomain getItemDomain(final Integer id, final Double weight, final Double cost) {
        ItemDomain itemDomain = new ItemDomain();
        itemDomain.setId(id);
        itemDomain.setWeight(weight);
        itemDomain.setCost(cost);
        return itemDomain;
    }
}
