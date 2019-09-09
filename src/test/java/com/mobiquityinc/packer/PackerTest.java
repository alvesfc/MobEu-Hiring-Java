package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

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
        String expected = "3\n" + "-\n" + "5,3\n" + "6,9\n";
        String actual = Packer.pack(filePath("MyPack.txt"));
        System.out.println(expected);
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

    @Test(expected = APIException.class)
    public void testThrowExceptionWhenItemCostIsMoreThan100() throws IOException, APIException {
        try {
            Packer.pack(filePath("ItemCostMoreThan100.txt"));
        } catch (APIException ex) {
            Assert.assertEquals("The item cost is can not be more than 100!", ex.getMessage());
            throw ex;
        }
        Assert.fail("Expect APIException");
    }

    @Test(expected = APIException.class)
    public void testThrowExceptionWhenItemWeightIsMoreThan100() throws IOException, APIException {
        try {
            Packer.pack(filePath("ItemWeightMoreThan100.txt"));
        } catch (APIException ex) {
            Assert.assertEquals("The item weight is can not be more than 100!", ex.getMessage());
            throw ex;
        }
        Assert.fail("Expect APIException");
    }

    @Test(expected = APIException.class)
    public void testThrowExceptionWhenItemsIsMoreThan15() throws IOException, APIException {
        try {
            Packer.pack(filePath("ItemsMoreThan15.txt"));
        } catch (APIException ex) {
            Assert.assertEquals("Can not have more than 15 items!", ex.getMessage());
            throw ex;
        }
        Assert.fail("Expect APIException");
    }

    @Test(expected = APIException.class)
    public void testThrowExceptionWhenPackageLimitMoreThan100() throws IOException, APIException {
        try {
            Packer.pack(filePath("PackageLimitMoreThan100.txt"));
        } catch (APIException ex) {
            Assert.assertEquals("The package limit is can not be more than 100!", ex.getMessage());
            throw ex;
        }
        Assert.fail("Expect APIException");
    }
}