package com.mobiquityinc.extract;

import com.mobiquityinc.domain.ItemDomain;
import com.mobiquityinc.domain.PackageDomain;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PackageDomainExtractTest extends AbstractTest{

    private ExtractInfo<PackageDomain> extractInfo;

    @Before
    public void setUP(){
        this.extractInfo = new PackageDomainExtract();
    }

    @Test
    public void extract() {
        PackageDomain expected = new PackageDomain();
        List<ItemDomain> items = new ArrayList<>();
        items.add(getItemDomain(1,90.72,13D));
        items.add(getItemDomain(2,33.80D,40D));
        expected.setLine(1);
        expected.setItems(items);
        expected.setLimit(56D);

        PackageDomain actual = this.extractInfo.extract("56 : (1,90.72,€13) (2,33.80,€40)  ");
        Assert.assertEquals(expected.getLimit(),actual.getLimit());
        Assert.assertEquals(expected.getItems(),actual.getItems());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowCorrectMessageWhenPackageLimitMoreThan100() {
        try {
            this.extractInfo.extract("100.01 : (1,90.72,€13) (2,33.80,€40)  ");
        } catch (IllegalArgumentException ex) {
            Assert.assertEquals("The package limit is can not be more than 100!", ex.getMessage());
            throw ex;
        }
        Assert.fail("Expect IllegalArgumentException");
    }
}