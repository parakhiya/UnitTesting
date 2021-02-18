package com.example.Unit.Testing.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class History {
  @Id
  private String id;
  private int operand1;
  private int operand2;
  private String operator;
  private double result;
  private String message;
}
