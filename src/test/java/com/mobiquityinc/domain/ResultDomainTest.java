package com.mobiquityinc.domain;

import com.mobiquityinc.extract.AbstractTest;
import org.junit.Assert;
import org.junit.Test;


public class ResultDomainTest extends AbstractTest {

    private ResultDomain resultDomain;

    @Test
    public void testIfIRemoveAntemWithWeightLessThanOtherAndSameCost() {
        ItemDomain expected = getItemDomain(6,72.30,76D);

        this.resultDomain = new ResultDomain(1,56D);
        this.resultDomain.addItem(getItemDomain(6,48.77D,79D));
        this.resultDomain.addItem(getItemDomain(8,19.36,79D));
        this.resultDomain.addItem(getItemDomain(9,6.76,64D));
        this.resultDomain.addItem(getItemDomain(7,81.80D,45D));
        this.resultDomain.addItem(getItemDomain(2,33.80D,40D));
        this.resultDomain.addItem(getItemDomain(5,46.81D,36D));
        this.resultDomain.addItem(getItemDomain(1,90.72D,13D));
        this.resultDomain.addItem(getItemDomain(4,37.97D,16D));
        this.resultDomain.addItem(getItemDomain(3,43.15D,10D));

        Assert.assertEquals(2,this.resultDomain.getItems().size());
        Assert.assertEquals(8,this.resultDomain.getItems().get(0).getId().intValue());
        Assert.assertEquals(9,this.resultDomain.getItems().get(1).getId().intValue());
    }
}