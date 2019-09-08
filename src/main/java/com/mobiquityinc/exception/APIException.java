package com.mobiquityinc.exception;


/**
 *
 * @author alvesfc
 * @version 1.0
 */
public class APIException extends Exception {

  public APIException(String message, Exception e) {
    super(message, e);
  }

  public APIException(String message) {
    super(message);
  }
}
