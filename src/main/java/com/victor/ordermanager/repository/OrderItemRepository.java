package com.victor.ordermanager.repository;

import com.victor.ordermanager.model.Order;
import com.victor.ordermanager.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
