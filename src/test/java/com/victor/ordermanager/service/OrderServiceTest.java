package com.victor.ordermanager.service;

import com.victor.ordermanager.model.Order;
import com.victor.ordermanager.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
class OrderServiceTest {

    private OrderService orderService;

    @MockBean
    OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        orderService = new OrderService(orderRepository);

        Order order = new Order();
        Order order2 = new Order();

        List<Order> orders = new ArrayList<>();

        orders.add(order);
        orders.add(order2);

        Mockito.when(orderRepository.findById(any()))
                .thenReturn(Optional.of(order));

        Mockito.when(orderRepository.findAll())
                .thenReturn(orders);
    }

    @Test
    void findAll() {
        List<Order> orders = orderService.findAll();
        assertEquals(2, orders.size());
    }

    @Test
    void findById() {
        Order foundOrder = orderService.findById(1L).orElse(null);
        assertNotNull(foundOrder);

    }
}