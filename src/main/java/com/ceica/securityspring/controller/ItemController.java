package com.ceica.securityspring.controller;

import com.ceica.securityspring.model.Items;
import com.ceica.securityspring.repository.ItemsRepository;
import com.ceica.securityspring.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {

    private ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/items")
    public Items crearItem(@RequestBody Items item) {
        return itemService.crearItem(item);
    }
}