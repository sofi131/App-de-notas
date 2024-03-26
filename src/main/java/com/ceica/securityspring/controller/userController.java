package com.ceica.securityspring.controller;

import com.ceica.securityspring.model.Items;
import com.ceica.securityspring.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class userController {
    private ItemService itemService;

    public userController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/user")
    public String user(Model model){
        List< Items> itemsList=itemService.buscarTodas();
         model.addAttribute("notas",itemsList);
        return "user";
    }
}
