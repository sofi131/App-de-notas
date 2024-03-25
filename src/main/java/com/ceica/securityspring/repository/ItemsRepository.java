package com.ceica.securityspring.repository;

import com.ceica.securityspring.model.Items;
import com.ceica.securityspring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemsRepository extends JpaRepository<Items, Integer> {

    List<Items> findByTitle(String title);



    //Método para contar la cantidad de Items que tiene buscando por id
    int countByUserId(int userId);

}
