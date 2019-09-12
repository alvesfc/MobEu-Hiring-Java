package com.mobiquityinc.extract;

import com.mobiquityinc.domain.ItemDomain;
import com.mobiquityinc.exception.APIException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ItemsDomainExtractTest extends AbstractTest{

    private ExtractInfo<List<ItemDomain>> extractInfo;

    @Before
    public void setUP(){
        this.extractInfo = new ItemsDomainExtract();
    }

    @Test
    public void extract() {
        List<ItemDomain> expected = new ArrayList<>();
        expected.add(getItemDomain(1,53.38,45D));
        expected.add(getItemDomain(2,10D,20D));

        List<ItemDomain> actual = this.extractInfo.extract("81 : (1,53.38,€45) (2,10,€20)");
        Assert.assertEquals(2,actual.size());
        Assert.assertEquals(expected,actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowCorrectMessageWhenItemsIsMoreThan15() throws IOException, APIException {
        try {
            this.extractInfo.extract("100 : (1,53.38,€45) (2,88.62,€98) (3,1.30,€2) (4,0.70,€1) (5,1,€1) (6,1,€9) (7,1.1,€4) (8,0.10,€1) (9,1.38,€4) (9,8,€98) (10,1.13,€2) (11,0.70,€1) (12,5.38,€45) (13,88.2,€9) (14,1.90,€2) (15,0.1,€1) (16,0.3,€2)");
        } catch (IllegalArgumentException ex) {
            Assert.assertEquals("Can not have more than 15 items!", ex.getMessage());
            throw ex;
        }
        Assert.fail("Expect IllegalArgumentException");
    }

}