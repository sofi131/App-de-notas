package com.ceica.securityspring.service;

import com.ceica.securityspring.model.Items;
import com.ceica.securityspring.repository.ItemsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ItemService {

    private ItemsRepository itemsRepository;


    public ItemService() {
        this.itemsRepository = itemsRepository;
    }

    public Items crearItem(Items item) {
        // Guardar el nuevo ítem en la base de datos
        return itemsRepository.save(item);
    }

    public void eliminarItemPorId(int itemsId) {
        itemsRepository.deleteById(itemsId);
    }

    // Método para modificar un item existente
    public Items modificarItem(Items items) {
        // Verificar si el item existe
        Optional<Items> optionalItem = itemsRepository.findById(items.getId());
        if (optionalItem.isPresent()) {
            // Actualizar los campos del item existente
            Items existingItem = optionalItem.get();
            existingItem.setTitle(items.getTitle());
            existingItem.setDescription(items.getDescription());
            // Guardar el item actualizado en la base de datos
            return itemsRepository.save(existingItem);
        } else {
            // Manejar el caso en que el item no existe
            throw new IllegalArgumentException("El item con ID " + items.getId() + " no existe");
        }
    }

    public List<Items> buscarItemsPorTitulo(String titulo) {
        return itemsRepository.findByTitle(titulo);
    }

    public int contarItemsPorUsuario(int userId) {
        return itemsRepository.countByUserId(userId);
    }

}
