package com.victor.ordermanager.service;

import com.victor.ordermanager.model.Order;
import com.victor.ordermanager.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> findAll(){
        return (List<Order>) orderRepository.findAll();
    }

    public Optional<Order> findById(Long id){
        return orderRepository.findById(id);
    }

    public Order save(Order order){
        order.calcTotal();
        return orderRepository.save(order);
    }
}
