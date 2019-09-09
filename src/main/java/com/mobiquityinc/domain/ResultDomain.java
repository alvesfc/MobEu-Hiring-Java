package com.mobiquityinc.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class ResultDomain {

    private Integer line;
    private Double limit;
    private List<ItemDomain> items;
    private Double itemsWeight;

    public ResultDomain(Integer line, Double limit) {
        this.line = line;
        this.limit = limit;
        this.itemsWeight = 0D;
        this.items = new ArrayList<>();
    }

    public Integer getLine() {
        return line;
    }

    public Double getLimit() {
        return limit;
    }

    public List<ItemDomain> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void addItem(ItemDomain item) {
        if((itemsWeight + item.getWeight() <= limit)){
            this.itemsWeight = this.itemsWeight + item.getWeight();
            this.items.add(item);
        }else {
            this.items.stream()
                    .filter(itemDomain -> itemDomain.getWeight().equals(item.getWeight())
                    && itemDomain.getCost() < item.getCost())
                    .forEach(itemDomain -> {
                        items.remove(itemDomain);
                        items.add(item);
                    });
        }
    }

    public String result(){
        if(this.items.isEmpty()){
            return  "-";
        }
        return  this.items.stream()
                .map(itemDomain -> itemDomain.getId().toString())
                .collect( Collectors.joining( ","));
    }

    @Override
    public String toString() {
        return "ResultDomain{" +
                "line=" + line +
                ", limit=" + limit +
                ", items=" + items +
                ", itemsWeight=" + itemsWeight +
                '}';
    }
}
