package com.mobiquityinc.domain;

import java.util.Objects;


/**
 * Domain class that represents an item.
 *
 * @author alvesfc
 * @version 1.0
 */
public class ItemDomain {

    private Integer id;
    private Double weight;
    private Double cost;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemDomain that = (ItemDomain) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "ItemDomain{" +
                "id=" + id +
                ", weight=" + weight +
                ", cost=" + cost +
                '}';
    }
}
