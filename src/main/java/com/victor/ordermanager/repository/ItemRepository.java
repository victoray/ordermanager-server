package com.victor.ordermanager.repository;

import com.victor.ordermanager.model.Item;
import com.victor.ordermanager.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
