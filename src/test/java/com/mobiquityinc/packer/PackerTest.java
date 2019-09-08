package com.mobiquityinc.packer;


import com.mobiquityinc.domain.ItemDomain;
import com.mobiquityinc.domain.PackageDomain;
import com.mobiquityinc.exception.APIException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PackerTest {

    private static String filePath(final String fileNAme) {
        return System.getProperty("user.dir") + "/src/test/resources/" + fileNAme;
    }

    @Test(expected = IOException.class)
    public void testNoFile() throws IOException {
        try {
            Packer.pack("wrong_path");
        } catch (APIException ex) {
            Assert.fail(ex.getMessage());
        }
        Assert.fail("Expect IOException");
    }

    @Test
    public void testReadFile() throws IOException, APIException {
        String expected = "[PackageDomain{limit=81.0, items=[ItemDomain{id=1, weight=53.38, cost=45.0}, ItemDomain{id=2, weight=88.62, cost=98.0}]}, PackageDomain{limit=8.0, items=[ItemDomain{id=1, weight=15.3, cost=34.0}]}]";
        String actual = Packer.pack(filePath("MyPack.txt"));

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testReadEmptyFile() throws IOException, APIException {
        String expected = "[]";
        String actual = Packer.pack(filePath("EmptyFile.txt"));

        Assert.assertEquals(expected, actual);
    }

    @Test(expected = APIException.class)
    public void testThrowExceptionWhenPackageLimitIsNotNumber() throws IOException, APIException {
        try {
            Packer.pack(filePath("PackageLimitNotNumber.txt"));
        } catch (APIException ex) {
            Assert.assertEquals("The package limit is not a number!", ex.getMessage());
            throw ex;
        }
        Assert.fail("Expect APIException");
    }

    @Test(expected = APIException.class)
    public void testThrowExceptionWhenItemIDtIsNotNumber() throws IOException, APIException {
        try {
            Packer.pack(filePath("ItemIDNotNumber.txt"));
        } catch (APIException ex) {
            Assert.assertEquals("The item id is not a number!", ex.getMessage());
            throw ex;
        }
        Assert.fail("Expect APIException");
    }

    @Test(expected = APIException.class)
    public void testThrowExceptionWhenItemWeightIsNotNumber() throws IOException, APIException {
        try {
            Packer.pack(filePath("ItemWeightNotNumber.txt"));
        } catch (APIException ex) {
            Assert.assertEquals("The item weight is not a number!", ex.getMessage());
            throw ex;
        }
        Assert.fail("Expect APIException");
    }

    @Test(expected = APIException.class)
    public void testThrowExceptionWhenItemCostIsNotNumber() throws IOException, APIException {
        try {
            Packer.pack(filePath("ItemCostNotNumber.txt"));
        } catch (APIException ex) {
            Assert.assertEquals("The item cost is not a number!", ex.getMessage());
            throw ex;
        }
        Assert.fail("Expect APIException");
    }
}