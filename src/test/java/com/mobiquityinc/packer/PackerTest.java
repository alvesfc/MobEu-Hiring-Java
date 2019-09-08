package com.mobiquityinc.packer;


import com.mobiquityinc.exception.APIException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class PackerTest {

    private static String filePath(final String fileNAme){
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
        String expected = "8 : (1,15.3,€34)";
        String actual = Packer.pack(filePath("MyPack.txt"));

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void testReadEmptyFile() throws IOException, APIException {
        String expected = "";
        String actual = Packer.pack(filePath("EmptyFile.txt"));

        Assert.assertEquals(expected,actual);
    }
}