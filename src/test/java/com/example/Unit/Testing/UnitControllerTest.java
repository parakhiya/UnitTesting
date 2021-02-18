package com.example.Unit.Testing;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.Unit.Testing.controller.UnitController;
import com.example.Unit.Testing.entity.RequestBodyClass;
import com.example.Unit.Testing.entity.Response;
import com.example.Unit.Testing.service.impl.UnitServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

//@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(UnitController.class)
public class UnitControllerTest {
  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();
  @InjectMocks
  private UnitController unitController;
  @Autowired
  MockMvc mockMvc;
  @Mock
  private UnitServiceImpl unitServiceImpl;
  @Captor
  ArgumentCaptor<Response> responseCapture;
  @Test
  public void testSum() throws Exception {
    Response response = new Response();
    response.setResult(4.0);
    response.setMessage("Successful Operation");
    when(unitServiceImpl.sum(2, 2)).thenReturn(response);
    mockMvc.perform(get("/calculator/sum/").contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(new ObjectMapper().writeValueAsString(new RequestBodyClass(2,2))))
        .andDo(print())
        .andExpect(jsonPath("$.result", CoreMatchers.equalTo(4.0)))
        .andExpect(jsonPath("$.message", CoreMatchers.equalTo("Successful Operation"))).andExpect(status().isOk());
    Mockito.verify(unitServiceImpl).sum(2, 2);
  }
  @Test
  public void testSumException() throws Exception {
    Response response = new Response();
    response.setResult(null);
    response.setMessage("Integer OverFlow!");
    when(unitServiceImpl.sum(2147483647, 2)).thenReturn(response);
    mockMvc.perform(get("/calculator/sum").contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(new ObjectMapper().writeValueAsString(new RequestBodyClass(2147483647,2))))
        .andDo(print())
        .andExpect(jsonPath("$.result", CoreMatchers.equalTo(null)))
        .andExpect(jsonPath("$.message", CoreMatchers.equalTo("Integer OverFlow!"))).andExpect(status().isOk());
    Mockito.verify(unitServiceImpl).sum(2147483647, 2);
  }
  @Test
  public void testMinusExceptionOverFlow() throws Exception {
    Response response = new Response();
    response.setResult(null);
    response.setMessage("Integer OverFlow!");
    when(unitServiceImpl.minus(2147483647, -9)).thenReturn(response);
    mockMvc.perform(get("/calculator/minus").contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(new ObjectMapper().writeValueAsString(new RequestBodyClass(2147483647,-9)))).andDo(print())
        .andExpect(jsonPath("$.result", CoreMatchers.equalTo(null)))
        .andExpect(jsonPath("$.message", CoreMatchers.equalTo("Integer OverFlow!"))).andExpect(status().isOk());
    Mockito.verify(unitServiceImpl).minus(2147483647, -9);
  }
  @Test
  public void testMinusExceptionUnderFlow() throws Exception {
    Response response = new Response();
    response.setResult(null);
    response.setMessage("Integer UnderFlow!");
    when(unitServiceImpl.minus(-9, 2147483647)).thenReturn(response);
    mockMvc.perform(get("/calculator/minus").contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(new ObjectMapper().writeValueAsString(new RequestBodyClass(-9,2147483647)))).andDo(print())
        .andExpect(jsonPath("$.result", CoreMatchers.equalTo(null)))
        .andExpect(jsonPath("$.message", CoreMatchers.equalTo("Integer UnderFlow!"))).andExpect(status().isOk());
    Mockito.verify(unitServiceImpl).minus(-9, 2147483647);
  }
  @Test
  public void testMinus() throws Exception {
    Response response = new Response();
    response.setResult(0.0);
    response.setMessage("Successful Operation");
    when(unitServiceImpl.minus(2, 2)).thenReturn(response);
    mockMvc.perform(get("/calculator/minus").contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(new ObjectMapper().writeValueAsString(new RequestBodyClass(2,2)))).andDo(print())
        .andExpect(jsonPath("$.result", CoreMatchers.equalTo(0.0)))
        .andExpect(jsonPath("$.message", CoreMatchers.equalTo("Successful Operation"))).andExpect(status().isOk());
    Mockito.verify(unitServiceImpl).minus(2, 2);
  }
  @Test
  public void testMultiply() throws Exception {
    Response response = new Response();
    response.setResult(4.0);
    response.setMessage("Successful Operation");
    when(unitServiceImpl.multiply(2, 2)).thenReturn(response);
    mockMvc.perform(get("/calculator/multiply").contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(new ObjectMapper().writeValueAsString(new RequestBodyClass(2,2)))).andDo(print())
        .andExpect(jsonPath("$.result", CoreMatchers.equalTo(4.0)))
        .andExpect(jsonPath("$.message", CoreMatchers.equalTo("Successful Operation"))).andExpect(status().isOk());
    Mockito.verify(unitServiceImpl).multiply(2, 2);
  }
  @Test
  public void testMultiplyExceptionOverFlow() throws Exception {
    Response response = new Response();
    response.setResult(null);
    response.setMessage("Integer OverFlow!");
    when(unitServiceImpl.multiply(2147483647, 2)).thenReturn(response);
    mockMvc.perform(get("/calculator/multiply").contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(new ObjectMapper().writeValueAsString(new RequestBodyClass(2147483647,2)))).andDo(print())
        .andExpect(jsonPath("$.result", CoreMatchers.equalTo(null)))
        .andExpect(jsonPath("$.message", CoreMatchers.equalTo("Integer OverFlow!"))).andExpect(status().isOk());
    Mockito.verify(unitServiceImpl).multiply(2147483647, 2);
  }
  @Test
  public void testMultiplyExceptionUnderFlow() throws Exception {
    Response response = new Response();
    response.setResult(null);
    response.setMessage("Integer UnderFlow!");
    when(unitServiceImpl.multiply(2147483647, -2)).thenReturn(response);
    mockMvc.perform(get("/calculator/multiply").contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(new ObjectMapper().writeValueAsString(new RequestBodyClass(2147483647,-2)))).andDo(print())
        .andExpect(jsonPath("$.result", CoreMatchers.equalTo(null)))
        .andExpect(jsonPath("$.message", CoreMatchers.equalTo("Integer UnderFlow!"))).andExpect(status().isOk());
    Mockito.verify(unitServiceImpl).multiply(2147483647, -2);
  }
  @Test
  public void testDivide() throws Exception {
    Response response = new Response();
    response.setResult(1.0);
    response.setMessage("Successful Operation");
    when(unitServiceImpl.divide(2, 2)).thenReturn(response);
    mockMvc.perform(get("/calculator/divide").contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(new ObjectMapper().writeValueAsString(new RequestBodyClass(2,2)))).andDo(print())
        .andExpect(jsonPath("$.result", CoreMatchers.equalTo(1.0)))
        .andExpect(jsonPath("$.message", CoreMatchers.equalTo("Successful Operation"))).andExpect(status().isOk());
    Mockito.verify(unitServiceImpl).divide(2, 2);
  }
  @Test
  public void testDivideException() throws Exception {
    Response response = new Response();
    response.setResult(null);
    response.setMessage("divisor is 0");
    when(unitServiceImpl.divide(2, 0)).thenReturn(response);
    mockMvc.perform(get("/calculator/divide").contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(new ObjectMapper().writeValueAsString(new RequestBodyClass(2,0)))).andDo(print())
        .andExpect(jsonPath("$.result", CoreMatchers.equalTo(null)))
        .andExpect(jsonPath("$.message", CoreMatchers.equalTo("divisor is 0"))).andExpect(status().isOk());
    Mockito.verify(unitServiceImpl).divide(2, 0);
  }

  @Test
  public void testSumByOperator() throws Exception {
    Response response = new Response();
    response.setResult(5.0);
    response.setMessage("Successful Operation");
    when(unitServiceImpl.sumByOperator("*")).thenReturn(response);
    mockMvc.perform(get("/calculator/sumByOperator/*").contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(new ObjectMapper().writeValueAsString(new RequestBodyClass(2147483647,2)))).andDo(print())
        .andExpect(jsonPath("$.result", CoreMatchers.equalTo(5.0)))
        .andExpect(jsonPath("$.message", CoreMatchers.equalTo("Successful Operation"))).andExpect(status().isOk());
    Mockito.verify(unitServiceImpl).sumByOperator("*");
  }

  @Before
  public void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(unitController).build();
  }
  @After
  public void tearDown() {
    Mockito.verifyNoMoreInteractions(unitServiceImpl);
  }
}

