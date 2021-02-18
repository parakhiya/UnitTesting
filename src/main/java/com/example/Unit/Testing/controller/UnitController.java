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
  private UnitServiceImpl unitService;

  @GetMapping(value = SUM,consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
  public Response sum(@RequestBody RequestBodyClass requestBodyClass) {
      return unitService.sum(requestBodyClass.getOperand1(), requestBodyClass.getOperand2());
  }

  @GetMapping(value=MINUS,consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
  public Response minus(@RequestBody RequestBodyClass requestBodyClass) {
    return unitService.minus(requestBodyClass.getOperand1(), requestBodyClass.getOperand2());
  }

  @GetMapping(value=MULTIPLY,consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
  public Response multiply(@RequestBody RequestBodyClass requestBodyClass) {
    return unitService.multiply(requestBodyClass.getOperand1(), requestBodyClass.getOperand2());
  }

  @GetMapping(value=DIVIDE,consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
  public Response divide(@RequestBody RequestBodyClass requestBodyClass) {
    return unitService.divide(requestBodyClass.getOperand1(), requestBodyClass.getOperand2());
  }

  @GetMapping(value=FINDALL,produces = MediaType.APPLICATION_JSON_VALUE)
  public List<History> findAll() {
    return unitService.findAll();
  }

  @GetMapping(value=FINDBYOPERATOR,produces = MediaType.APPLICATION_JSON_VALUE)
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

  @GetMapping(value=FINDBYOPERAND,produces = MediaType.APPLICATION_JSON_VALUE)
  public List<History> findByOperand(@PathVariable int query) {
    return unitService.findByOperand(query);
  }

  @GetMapping(value=SUMBYOPERATOR,produces = MediaType.APPLICATION_JSON_VALUE)
  public Response sumByOperator(@PathVariable String query) {
    return unitService.sumByOperator(query);
  }
}
