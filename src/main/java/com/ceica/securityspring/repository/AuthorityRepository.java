package com.ceica.securityspring.repository;


import com.ceica.securityspring.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {

}
