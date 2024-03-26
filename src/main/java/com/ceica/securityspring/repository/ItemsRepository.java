package com.ceica.securityspring.repository;


import com.ceica.securityspring.model.Items;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface ItemsRepository extends JpaRepository<Items, Integer> {

    List<Items> findByTitle(String title);


    List<Items> findByUserId(Integer user_id);
}
