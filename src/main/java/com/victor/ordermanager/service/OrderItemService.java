package com.victor.ordermanager.service;

import com.victor.ordermanager.model.Order;
import com.victor.ordermanager.model.OrderItem;
import com.victor.ordermanager.repository.OrderItemRepository;
import com.victor.ordermanager.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {

    private OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public List<OrderItem> findAll(){
        return (List<OrderItem>) orderItemRepository.findAll();
    }

    public Optional<OrderItem> findById(Long id){
        return orderItemRepository.findById(id);
    }

    public OrderItem save(OrderItem orderItem){
       return orderItemRepository.save(orderItem);
    }
}
