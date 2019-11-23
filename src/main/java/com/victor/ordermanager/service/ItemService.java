package com.victor.ordermanager.service;


import com.victor.ordermanager.model.Item;
import com.victor.ordermanager.model.Order;
import com.victor.ordermanager.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> findAll(){
        return (List<Item>) itemRepository.findAll();
    }

    public Optional<Item> findById(Long id){
        return itemRepository.findById(id);
    }

    public void save(Item item){
        itemRepository.save(item);
    }
}
