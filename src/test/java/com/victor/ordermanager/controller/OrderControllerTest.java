package com.victor.ordermanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.victor.ordermanager.model.Order;
import com.victor.ordermanager.model.OrderItem;
import com.victor.ordermanager.service.OrderItemService;
import com.victor.ordermanager.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @MockBean
    private OrderItemService orderItemService;

    private JacksonTester<Order> json;

    @BeforeEach
    void setUp(){
        Order order = new Order();
        order.setId(1L);
        order.setOrderItems(new ArrayList<>());
        order.setTotal(43);
        order.setOrderDate(new Date());

        ObjectMapper objectMapper = new ObjectMapper();
        JacksonTester.initFields(this, objectMapper);

        given(orderService.findAll()).willReturn(new ArrayList<>(Collections.singletonList(order)));
        given(orderService.findById(any())).willReturn(Optional.of(order));
        given(orderService.save(any())).willReturn(order);

    }

    @Test
    void addOrder() throws Exception {
        Order order = new Order();
        order.setId(1L);
        order.setOrderItems(new ArrayList<>());
        order.setTotal(43);
        order.setOrderDate(new Date());
        mockMvc.perform(put("/api/orders/new")
                .content(json.write(order).getJson())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void getOrders() throws Exception {
        mockMvc.perform(get("/api/orders")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].total", is(43.0)));

    }

    @Test
    void getOrdersById() throws Exception {
        mockMvc.perform(get("/api/orders/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(1)));

    }

}