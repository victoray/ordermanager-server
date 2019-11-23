package com.victor.ordermanager.controller;

import com.victor.ordermanager.model.Item;
import com.victor.ordermanager.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(ItemController.class)
class ItemControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ItemService itemService;

    @BeforeEach
    void setUp() {
        Item item = new Item();
        item.setId(1L);
        item.setPrice(5.0);
        item.setName("book");

        given(itemService.findAll()).willReturn(Collections.singletonList(item));
    }

    @Test
    void getOrders() throws Exception {
        mockMvc.perform(get("/api/items")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}