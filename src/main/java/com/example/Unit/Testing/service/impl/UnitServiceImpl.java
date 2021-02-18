package com.example.Unit.Testing.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Unit.Testing.entity.History;
import com.example.Unit.Testing.entity.Response;
import com.example.Unit.Testing.repository.UnitRepository;

@Service
public class UnitServiceImpl implements com.example.Unit.Testing.service.UnitService {
  @Autowired
  private UnitRepository unitRepository;
  private Logger logger = LoggerFactory.getLogger(UnitServiceImpl.class);

  private History saveHistory(int operand1, int operand2, String operator, float result) {
    History history = new History();
    history.setOperand1(operand1);
    history.setOperand2(operand2);
    history.setResult((float) result);
    history.setOperator(operator);
    unitRepository.save(history);
    return history;
  }

  private Response createResponse(Double result, String message) {
    Response response = new Response();
    response.setResult(result);
    response.setMessage(message);
    return response;
  }

  @Override
  public Response sum(int operand1, int operand2) {
    long result = (long) operand1 + (long) operand2;
    try {
      if (result > Integer.MAX_VALUE) {
        throw new ArithmeticException("Integer OverFlow!");
      }
    } catch (ArithmeticException e) {
      logger.error("Exception is throwen {}", e.getMessage());
      return createResponse(null, e.getMessage());
    }
    History history = saveHistory(operand1, operand2, "+", result);
    logger.info("Sum is called {}", history.toString());
    return createResponse((double) result, "Successful Operation");
  }

  @Override
  public Response minus(int operand1, int operand2) {
    long result = (long) operand1 - (long) operand2;
    try {
      if (result < Integer.MIN_VALUE) {
        throw new ArithmeticException("Integer UnderFlow!");
      } else if (result > Integer.MAX_VALUE) {
        throw new ArithmeticException("Integer OverFlow!");
      }
    } catch (ArithmeticException e) {
      logger.error("Exception is throwen {}", e.getMessage());
      return createResponse(null, e.getMessage());
    }
    History history = saveHistory(operand1, operand2, "-", result);
    logger.info("Minus is called {}", history.toString());
    return createResponse((double) result, "Successful Operation");
  }

  @Override
  public Response multiply(int operand1, int operand2) {
    long result = (long) operand1 * (long) operand2;
    try {
      if (result < Integer.MIN_VALUE) {
        throw new ArithmeticException("Integer UnderFlow!");
      } else if (result > Integer.MAX_VALUE) {
        throw new ArithmeticException("Integer OverFlow!");
      }
    } catch (ArithmeticException e) {
      logger.error("Exception is throwen {}", e.getMessage());
      return createResponse(null, e.getMessage());
    }
    History history = saveHistory(operand1, operand2, "*", result);
    logger.info("Multiply is called {}", history.toString());
    return createResponse((double) result, "Successful Operation");
  }

  @Override
  public Response divide(int operand1, int operand2) {
    try {
      if (operand2 == 0) {
        throw new IllegalArgumentException("divisor is 0");
      }
    } catch (IllegalArgumentException e) {
      logger.error("Exception is throwen {}", e.getMessage());
      return createResponse(null, e.getMessage());
    }
    float result = (float) operand1 / operand2;
    History history = saveHistory(operand1, operand2, "/", result);
    logger.info("Divide is called {}", history.toString());
    return createResponse((double) result, "Successful Operation");
  }

  @Override
  public List<History> findAll() {
    Iterable<History> employeeIterable = unitRepository.findAll();
    List<History> employeeList = new ArrayList<>();
    employeeIterable.forEach(employeeList::add);
    logger.info("All records are accessed");
    return employeeList;
  }

  @Override
  public List<History> findByOperator(String query) {
    logger.info("Find by operator is called");
    return unitRepository.findByOperator(query);
  }

  @Override
  public List<History> findByOperand(int query) {
    logger.info("Find by Operand is called");
    return unitRepository.findByOperand1OrOperand2(query, query);
  }

  @Override
  public Response sumByOperator(String query) {
    List<History> list = unitRepository.findByOperator(query);
    double result = 0;
    for (History history : list) {
      result += history.getOperand1() + history.getOperand2();
    }
    logger.info("Sum by Operator is called ");
    return createResponse(result, "Successful Operation");
  }
}
