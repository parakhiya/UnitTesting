package com.example.Unit.Testing;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.lenient;

import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.example.Unit.Testing.entity.History;
import com.example.Unit.Testing.entity.Response;
import com.example.Unit.Testing.repository.UnitRepository;
import com.example.Unit.Testing.service.UnitService;
import com.example.Unit.Testing.service.impl.UnitServiceImpl;


public class CalculatorTest {
  @Mock
  private UnitRepository unitRepository;
  @InjectMocks
  private UnitService unitServiceImpl = new UnitServiceImpl();
  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();
  @Captor
  private ArgumentCaptor<History> historyCaptor;

  @Test
  public void testSum() {
    lenient().when(unitRepository.save(new History())).thenReturn(null);
    Response response = unitServiceImpl.sum(2, 2);
    Double result = response.getResult();
    assertEquals(new Double(4.0), result);
    Mockito.verify(unitRepository).save(historyCaptor.capture());
  }

  @Test
  public void testSumException() {
    Response response = unitServiceImpl.sum(Integer.MAX_VALUE, 44);

    if (response.getMessage().equals("Integer OverFlow!"))
      assertEquals("Integer OverFlow!", response.getMessage());
    else {
      fail("Integer OverFlow!");
    }
  }

  @Test
  public void testMinus() {
    lenient().when(unitRepository.save(new History())).thenReturn(null);
    Response response = unitServiceImpl.minus(2, 2);
    Double result = response.getResult();
    assertEquals(new Double(0.0), result);
    Mockito.verify(unitRepository).save(historyCaptor.capture());
  }

  @Test
  public void testMinusExceptionOverFlow() {
    Response response = unitServiceImpl.minus(Integer.MAX_VALUE, -9);
    if (response.getMessage().equals("Integer OverFlow!"))
      assertEquals("Integer OverFlow!", response.getMessage());
    else {
      fail("Integer OverFlow!");
    }
  }

  @Test
  public void testMinusExceptionUnderFlow() {

    Response response = unitServiceImpl.minus(-9, Integer.MAX_VALUE);
    if (response.getMessage().equals("Integer UnderFlow!"))
      assertEquals("Integer UnderFlow!", response.getMessage());
    else {
      fail("Integer UnderFlow!");
    }
  }

  @Test
  public void testMultiply() {
    lenient().when(unitRepository.save(new History())).thenReturn(null);
    Response response = unitServiceImpl.multiply(3, 2);
    Double result = response.getResult();
    assertEquals(new Double(6.0), result);
    Mockito.verify(unitRepository).save(historyCaptor.capture());
  }

  @Test
  public void testMultiplyExceptionOverFlow() {
    Response response = unitServiceImpl.multiply(Integer.MAX_VALUE, 2);
    if (response.getMessage().equals("Integer OverFlow!"))
      assertEquals("Integer OverFlow!", response.getMessage());
    else {
      fail("Integer OverFlow!");
    }
  }

  @Test
  public void testMultiplyExceptionUnderFlow() {

    Response response = unitServiceImpl.multiply(Integer.MAX_VALUE, -2);
    if (response.getMessage().equals("Integer UnderFlow!"))
      assertEquals("Integer UnderFlow!", response.getMessage());
    else {
      fail("Integer UnderFlow!");
    }
  }

  @Test
  public void testDivide() {
    lenient().when(unitRepository.save(new History())).thenReturn(null);
    Response response = unitServiceImpl.divide(2, 2);
    Double result = response.getResult();
    assertEquals(new Double(1.0), result);
    Mockito.verify(unitRepository).save(historyCaptor.capture());
  }

  @Test
  public void testDivideException() {
    Response response = unitServiceImpl.divide(2, 0);
    if (response.getMessage().equals("divisor is 0"))
      assertEquals("divisor is 0", response.getMessage());
    else {
      fail("divisor is 0");
    }
  }

  @Test
  public void testSumByOperator() {
    lenient().when(unitRepository.save(new History())).thenReturn(null);
    List<History> list = new ArrayList<>();
    list.add(new History("hle23", 2, 3, "*", 6.0, null));
    lenient().when(unitRepository.findByOperator("*")).thenReturn(list);
    Response response = unitServiceImpl.sumByOperator("*");
    System.out.println(response);
    Double result = response.getResult();
    assertEquals(new Double(5.0), result);
  }

}