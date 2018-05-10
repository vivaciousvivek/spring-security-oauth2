package com.example.security.util;
/**
 * Api response for all api that will give the boolean success/fail and message
 *
 * @author VIVEK KUMAR SINGH
 * @since (2018-05-02 12:48:06)
 */
public class ApiResponse {
  private boolean success;
  private String message;

  public ApiResponse(boolean success, String message) {
    this.success = success;
    this.message = message;
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
