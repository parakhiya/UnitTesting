package com.example.Unit.Testing.controller;

import com.example.Unit.Testing.entity.History;
import com.example.Unit.Testing.entity.RequestBodyClass;
import com.example.Unit.Testing.entity.Response;
import com.example.Unit.Testing.service.UnitService;
import com.example.Unit.Testing.service.impl.UnitServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.example.Unit.Testing.constant.UnitConstant.*;

@RestController
@RequestMapping(CALCULATOR)
public class UnitController {

  @Autowired
  UnitServiceImpl unitService;

  @GetMapping(value = SUM,consumes = MediaType.APPLICATION_JSON_VALUE)
  public Response sum(@RequestBody RequestBodyClass requestBodyClass) {
      return unitService.sum(requestBodyClass.getOperand1(), requestBodyClass.getOperand2());
  }

  @GetMapping(MINUS)
  public Response minus(@RequestBody RequestBodyClass requestBodyClass) {
    return unitService.minus(requestBodyClass.getOperand1(), requestBodyClass.getOperand2());
  }

  @GetMapping(MULTIPLY)
  public Response multiply(@RequestBody RequestBodyClass requestBodyClass) {
    return unitService.multiply(requestBodyClass.getOperand1(), requestBodyClass.getOperand2());
  }

  @GetMapping(DIVIDE)
  public Response divide(@RequestBody RequestBodyClass requestBodyClass) {
    return unitService.divide(requestBodyClass.getOperand1(), requestBodyClass.getOperand2());
  }

  @GetMapping(FINDALL)
  public List<History> findAll() {
    return unitService.findAll();
  }

  @GetMapping(FINDBYOPERATOR)
  public List<History> findByOperator(@PathVariable String query) {
    if (query.equals("*") || query.equals("/") || query.equals("+") || query.equals("-"))
      return unitService.findByOperator(query);
    else {
      History history = new History();
      history.setOperator("Please enter proper Operator");
      List<History> list = new ArrayList<>();
      list.add(history);
      return list;
    }
  }

  @GetMapping(FINDBYOPERAND)
  public List<History> findByOperand(@PathVariable int query) {
    return unitService.findByOperand(query);
  }

  @GetMapping(SUMBYOPERATOR)
  public Response sumByOperator(@PathVariable String query) {
    return unitService.sumByOperator(query);
  }
}
