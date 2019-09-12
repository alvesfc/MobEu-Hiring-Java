package com.mobiquityinc.extract;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ItemIDExtractTest {

    private ExtractInfo<Integer> extractInfo;

    @Before
    public void setUP(){
        this.extractInfo = new ItemIDExtract();
    }

    @Test
    public void extract() {
        Integer expected = 10;

        Integer actual = this.extractInfo.extract("(10,15.20,€50.20)");
        Assert.assertEquals(expected,actual);
    }

    @Test(expected = NumberFormatException.class)
    public void testThrowCorrectMassageWhenItemIDtIsNotNumber() {
        try {
            this.extractInfo.extract("(AA,15.20,€50.20)");
        } catch (NumberFormatException ex) {
            Assert.assertEquals("The item id is not a number!", ex.getMessage());
            throw ex;
        }
        Assert.fail("Expect NumberFormatException");
    }
}