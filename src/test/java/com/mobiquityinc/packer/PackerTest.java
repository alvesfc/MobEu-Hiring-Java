package com.mobiquityinc.packer;


import com.mobiquityinc.exception.APIException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class PackerTest {

    private static final String FILE_PATH = System.getProperty("user.dir") + "/src/test/resources/MyPack.txt";

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
        String actual = Packer.pack(FILE_PATH);

        Assert.assertEquals(expected,actual);
    }
}