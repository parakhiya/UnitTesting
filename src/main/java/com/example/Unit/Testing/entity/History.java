package com.example.Unit.Testing.entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class History {
  @Id
  private String id;
  private int operand1;
  private int operand2;
  private String operator;
  private double result;
  private String message;
  @Override
  public String toString() {
    return "History{" + "id='" + id + '\'' + ", operand1=" + operand1 + ", operand2=" + operand2 + ", operator='"
        + operator + '\'' + ", result=" + result + ", message='" + message + '\'' + '}';
  }
  public String getMessage() {
    return message;
  }
  public void setMessage(String message) {
    this.message = message;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public int getOperand1() {
    return operand1;
  }
  public void setOperand1(int operand1) {
    this.operand1 = operand1;
  }
  public int getOperand2() {
    return operand2;
  }
  public void setOperand2(int operand2) {
    this.operand2 = operand2;
  }
  public String getOperator() {
    return operator;
  }
  public void setOperator(String operator) {
    this.operator = operator;
  }
  public double getResult() {
    return result;
  }

  public History() {
  }

  public History(String id, int operand1, int operand2, String operator, double result, String message) {
    this.id = id;
    this.operand1 = operand1;
    this.operand2 = operand2;
    this.operator = operator;
    this.result = result;
    this.message = message;
  }

  public void setResult(double result) {
    this.result = result;
  }
}
