package com.mobiquityinc.domain;

import java.util.List;

/**
 * Domain class that represents a package.
 *
 * @author alvesfc
 * @version 1.0
 */
public class PackageDomain {

    private Double limit;
    private List<ItemDomain> items;

    public Double getLimit() {
        return limit;
    }

    public void setLimit(Double limit) {
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
