package com.mobiquityinc.extract;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ItemWeightExtractTest {

    private ExtractInfo<Double> extractInfo;

    @Before
    public void setUP(){
        this.extractInfo = new ItemWeightExtract();
    }

    @Test
    public void extract() {
        Double expected = 15.20D;

        Double actual = this.extractInfo.extract("(10,15.20,€50.20)");
        Assert.assertEquals(expected,actual);
    }

    @Test(expected = NumberFormatException.class)
    public void testThrowCorrectMassageWhenItemWeightIsNotNumber() {
        try {
            this.extractInfo.extract("(1,15.A,€50.20)");
        } catch (NumberFormatException ex) {
            Assert.assertEquals("The item weight is not a number!", ex.getMessage());
            throw ex;
        }
        Assert.fail("Expect NumberFormatException");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowCorrectMessageWhenItemWeightIsMoreThan100() {
        try {
            this.extractInfo.extract("(1,100.01,€50.20)");
        } catch (IllegalArgumentException ex) {
            Assert.assertEquals("The item weight is can not be more than 100!", ex.getMessage());
            throw ex;
        }
        Assert.fail("Expect IllegalArgumentException");
    }

}