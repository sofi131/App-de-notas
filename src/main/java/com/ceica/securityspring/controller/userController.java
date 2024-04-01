package com.ceica.securityspring.controller;

import com.ceica.securityspring.model.Items;
import com.ceica.securityspring.model.User;
import com.ceica.securityspring.service.ItemService;
import com.ceica.securityspring.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
        model.addAttribute("crearNota", item);
        return "redirect:/user";
    }
/*
    @PostMapping("/user")
    public String eliminarItem(@RequestParam("itemId") int itemId, RedirectAttributes redirectAttributes) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Aquí se invoca el método del servicio para eliminar el ítem por su ID
        itemService.eliminarItemPorId(itemId);
        // Se agrega un mensaje de éxito para mostrar en la vista
        redirectAttributes.addFlashAttribute("eliminarNota", "¡Nota eliminada correctamente!");
        // Se redirige de vuelta a la página de usuario
        return "redirect:/user";
    }

 */
    //--------------------------------editar item---------------------------------
@GetMapping("/user/{itemId}/editar")
public String editarItemPorId(@PathVariable("itemId") int itemId, Model model) {
    // Aquí solo deberíamos redirigir al formulario de edición, no eliminar el ítem
    // Primero, cargamos la nota a editar y la pasamos al formulario de edición
    Items notaEditar = itemService.encontrarItemPorId(itemId).orElse(null);
    if (notaEditar == null) {
        // Manejar el caso en que la nota no exista
        throw new IllegalArgumentException("La nota con ID " + itemId + " no existe");
    }
    model.addAttribute("itemEditar", notaEditar);

    // Retornamos la vista del formulario de edición
    return "formulario-edicion";
}


    @PostMapping("/user/editar")
    public String editarItem(Model model, @ModelAttribute Items items) {
        // Obtener el usuario autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(authentication.getName());

        // Verificar si el item ya existe
        if (items.getId() != 0) {
            // Obtener la nota existente por su ID
            Optional<Items> optionalNotaExistente = itemService.encontrarItemPorId(items.getId());

            // Verificar si la nota existe
            if (optionalNotaExistente.isPresent()) {
                // Actualizar los campos de la nota existente con los nuevos valores
                Items notaExistente = optionalNotaExistente.get();
                notaExistente.setTitle(items.getTitle());
                notaExistente.setDescription(items.getDescription());

                // Guardar la nota actualizada en la base de datos
                itemService.actualizarItem(notaExistente);
            } else {
                // Manejar el caso en que la nota no existe
                throw new IllegalArgumentException("La nota con ID " + items.getId() + " no existe");
            }
        } else {
            // Manejar el caso en que el item no tenga un ID
            throw new IllegalArgumentException("No se proporcionó un ID para editar la nota");
        }

        // Redireccionar de vuelta a la página de usuario
        return "redirect:/user";
    }



//-----------------------------------------------------------------------------------

    @GetMapping("/user/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userService.findUserByUsername(username);
    }
}
