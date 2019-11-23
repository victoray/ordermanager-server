package com.victor.ordermanager.repository;

import com.victor.ordermanager.model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@AutoConfigureDataJpa
@DataJpaTest
class OrderRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private OrderRepository repository;

    @BeforeEach
    void setUp(){
        Order order = new Order();
        entityManager.persist(order);

        Order order2 = new Order();
        entityManager.persist(order2);
        entityManager.flush();

    }

    @Test
    void findById() {
        Order foundOrder = repository.findById(3L).orElse(null);
        assertNotNull(foundOrder);
        assertEquals(3L, foundOrder.getId());
    }

    @Test
    void findAll() {

        List<Order> orders = (List<Order>) repository.findAll();
        assertEquals(2, orders.size());
    }



}