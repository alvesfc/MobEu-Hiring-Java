package com.mobiquityinc.extract;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ItemCostExtractTest {

    private ExtractInfo<Double> extractInfo;

    @Before
    public void setUP(){
        this.extractInfo = new ItemCostExtract();
    }

    @Test
    public void extract() {
        Double expected = 50.20D;

        Double actual = this.extractInfo.extract("(10,15.20,€50.20)");
        Assert.assertEquals(expected,actual);
    }

    @Test(expected = NumberFormatException.class)
    public void testThrowCorrectMassageWhenItemCostIsNotNumber() {
        try {
            this.extractInfo.extract("(1,15.34,€5A.20)");
        } catch (NumberFormatException ex) {
            Assert.assertEquals("The item cost is not a number!", ex.getMessage());
            throw ex;
        }
        Assert.fail("Expect NumberFormatException");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowCorrectMessageWhenItemCostIsMoreThan100() {
        try {
            this.extractInfo.extract("(1,10.01,€100.01)");
        } catch (IllegalArgumentException ex) {
            Assert.assertEquals("The item cost is can not be more than 100!", ex.getMessage());
            throw ex;
        }
        Assert.fail("Expect IllegalArgumentException");
    }
}