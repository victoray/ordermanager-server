package com.victor.ordermanager.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Entity
@Table(name = "orders")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<OrderItem> orderItems;

    private double total;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private Date orderDate = new Date();


    public Order() {
    }
    public Order(double total,Date orderDate, List<OrderItem> orderItems) {
        this.total = total;
        this.orderDate = orderDate;
        this.orderItems = orderItems;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void calcTotal(){
        double sum  = 0;
        for (OrderItem orderItem: this.orderItems){
            sum += orderItem.getItem().getPrice() * orderItem.getQuantity();
        }

        this.total = sum;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderItems=" + orderItems +
                ", total=" + total +
                ", orderDate=" + orderDate +
                '}';
    }
}
