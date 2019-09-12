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

    /**
     * Methodo that add into the collection so that the
     * total weight is less than or equal to the package limit and the
     * total cost is as large as possible.
     * @param item - Item with value.
     */
    public void addItem(ItemDomain item) {
        if((itemsWeight + item.getWeight() <= limit)){
            this.itemsWeight = this.itemsWeight + item.getWeight();
            this.items.add(item);
        }else {
            this.items.stream()
                    .filter(itemDomain -> isWeightLessAndCostLarge(item, itemDomain))
                    .forEach(itemDomain -> {
                        itemDomain.setId(item.getId());
                        itemDomain.setCost(item.getCost());
                    });
        }
    }

    /**
     * Verify if a new item into list is less or equal to old item
     * @param newItem - Item that will be add
     * @param oldItem - Item added
     * @return - true or false
     */
    private boolean isWeightLessAndCostLarge(ItemDomain newItem, ItemDomain oldItem) {
        return newItem.getWeight() <= oldItem.getWeight()
                &&  newItem.getCost() >= oldItem.getCost();
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
