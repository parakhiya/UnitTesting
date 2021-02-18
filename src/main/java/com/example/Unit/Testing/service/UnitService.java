package com.example.Unit.Testing.service;
import com.example.Unit.Testing.entity.History;
import com.example.Unit.Testing.entity.Response;
import java.util.List;

public interface UnitService {
     /**
      * @param operand1
      * @param operand2
      * @return
      */
  Response sum(int operand1, int operand2);
     /**
      * @param operand1
      * @param operand2
      * @return
      */
  Response minus(int operand1, int operand2);
     /**
      * @param operand1
      * @param operand2
      * @return
      */
  Response multiply(int operand1, int operand2);
     /**
      * @param operand1
      * @param operand2
      * @return
      */
  Response divide(int operand1, int operand2);
     /**
      * @return
      */
  List<History> findAll();
     /**
      * @param query
      * @return
      */
  List<History> findByOperator(String query);
     /**
      * @param query
      * @return
      */
  List<History> findByOperand(int query);
  /**
   * @param query
   * @return
   */
  Response sumByOperator(String query);
}
