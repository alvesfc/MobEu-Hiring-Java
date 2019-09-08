package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The class is responsible to process a file.
 * @author alvesfc
 * @version 1.0
 */
public class Packer {

  private Packer() {
  }

  /**
   * The method is responsible to process a file with all information.
   * @param filePath - String with file location.
   * @return {@link String} with processing return.
   * @throws APIException - Exception always that finding a constraint.
   */
  public static String pack(String filePath) throws APIException, IOException {
    Path path = Paths.get(filePath);
    if(path.toFile().exists()){
      return Files.lines(Paths.get(filePath)).findFirst().orElse("");
    }else {
      throw new IOException("File not exists!");
    }
  }
}
