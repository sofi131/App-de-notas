package com.ceica.securityspring.controller;

import com.ceica.securityspring.model.User;
import com.ceica.securityspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class loginController {
    private UserService userService;

    @Autowired
    public loginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
/*
    @GetMapping("/login")
    public String dashboard() {
        // Obtener la autenticación actual
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // Verificar el rol del usuario
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("authority"));
        if (isAdmin) {
            // El usuario es administrador
            return "Donde queremos devolver si es admin";
        } else {
            // El usuario es usuario normal
            return "donde queremos devolver si es usuario normal";
        }
    }

 */

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String postRegister(@ModelAttribute User user) {
        //Encriptar contraseña
        user.setPassword(user.getPassword());
        //user.setEnabled(true);
        userService.crearUsuario(user);
        System.out.println(user);
        return "register";
    }

}
