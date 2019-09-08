package com.mobiquityinc.domain;

import java.util.List;

/**
 * Domain class that represents a package.
 *
 * @author alvesfc
 * @version 1.0
 */
public class PackageDomain {

    private double limit;
    private List<ItemDomain> items;

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    public List<ItemDomain> getItems() {
        return items;
    }

    public void setItems(List<ItemDomain> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "PackageDomain{" +
                "limit=" + limit +
                ", items=" + items +
                '}';
    }
}
