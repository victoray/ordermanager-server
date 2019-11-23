package com.victor.ordermanager.controller;

import com.victor.ordermanager.model.Order;
import com.victor.ordermanager.service.OrderItemService;
import com.victor.ordermanager.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class OrderController {

    private OrderService orderService;
    OrderItemService orderItemService;

    @Autowired
    public OrderController(OrderService orderService, OrderItemService orderItemService){
        this.orderService = orderService;
        this.orderItemService = orderItemService;
    }

    @GetMapping({"/orders", "/"})
    public ResponseEntity<List<Order>> getOrders(){
        List<Order> orders = orderService.findAll();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrdersById(@PathVariable String id){
        try {
            Long orderId = Long.valueOf(id);
            Order order = orderService.findById(orderId).orElse(null);
            if (order != null) {
                return new ResponseEntity<>(order, HttpStatus.OK);
            }
        } catch (NumberFormatException e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }



    @PutMapping("/orders/new")
    public ResponseEntity<Order> addOrder(@RequestBody Order order) {
        if (order.getOrderItems() != null && order.getOrderItems().size() != 0){
            order.getOrderItems().forEach(orderItem -> orderItemService.save(orderItem));
            Order savedOrder = orderService.save(order);
            return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
        }

        return  new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

    }




}
