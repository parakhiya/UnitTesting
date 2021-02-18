package com.example.Unit.Testing.entity;

import java.io.Serializable;

public class Response  {
  private Double result;
  private String message;

  public Double getResult() {
    return result;
  }

  public void setResult(Double result) {
    this.result = result;
  }

  public String getMessage() {
    return message;
  }

  @Override
  public String toString() {
    return "Response{" + "result=" + result + ", message='" + message + '\'' + '}';
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
