package com.ceica.securityspring.controller;

import com.ceica.securityspring.model.Items;
import com.ceica.securityspring.model.User;
import com.ceica.securityspring.service.ItemService;
import com.ceica.securityspring.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.List;

@Controller
public class userController {
    private ItemService itemService;

    private UserService userService;

    public userController(ItemService itemService, UserService userService) {
        this.itemService = itemService;
        this.userService = userService;
    }


    @GetMapping("/user")
    public String user(Model model){
        List< Items> itemsList=itemService.buscarTodas();
         model.addAttribute("listaNotaUser",itemsList);
        return "user";
    }

    @PostMapping("/user")
    public String crearItem(Model model, @ModelAttribute Items items) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user=userService.findUserByUsername(authentication.getName());
        items.setUser(user);
        items.setFecha(new Date());
        Items item = itemService.crearItem(items);

        return "redirect:/user";
    }

    @GetMapping("/user/{itemId}")
    public String eliminarItemPorId(@PathVariable("itemId") int itemId, Model model) {
        itemService.eliminarItemPorId(itemId);
        model.addAttribute("eliminarNota", "¡Nota eliminada correctamente!");

        return "redirect:/user";
    }

    @GetMapping("/user/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userService.findUserByUsername(username);
    }
}
