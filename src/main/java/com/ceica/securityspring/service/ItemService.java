package com.ceica.securityspring.service;

import com.ceica.securityspring.model.Items;
import com.ceica.securityspring.repository.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class ItemService {

    private ItemsRepository itemsRepository;

    @Autowired
    public ItemService(ItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }

    public Items crearItem(Items item) {
        // Guardar el nuevo ítem en la base de datos
        return itemsRepository.save(item);
    }

    public Optional<Items> encontrarItemPorId(int itemId) {
        return itemsRepository.findById(itemId);
    }

//    public void eliminarItemPorId(int itemsId) {
//        itemsRepository.deleteById(itemsId);
//    }

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

    public List<Items> buscarItemsPorUser(Integer iduser) {
        return itemsRepository.findByUserId(iduser);
    }

    public List<Items> buscarTodas() {
        return itemsRepository.findAll();
    }

    //método actualizar item --> editar básicamente
    public Items actualizarItem(Items notaExistente) {
        // Verificar si la nota existe en la base de datos
        Optional<Items> optionalItem = itemsRepository.findById(notaExistente.getId());
        if (optionalItem.isPresent()) {
            // Actualizar los campos de la nota existente
            Items existingItem = optionalItem.get();
            existingItem.setTitle(notaExistente.getTitle());
            existingItem.setDescription(notaExistente.getDescription());
            existingItem.setFecha(new Date()); // Actualizar la fecha si es necesario

            // Guardar la nota actualizada en la base de datos
            return itemsRepository.save(existingItem);
        } else {
            // Manejar el caso en que la nota no existe
            throw new IllegalArgumentException("La nota con ID " + notaExistente.getId() + " no existe");
        }
    }

}
