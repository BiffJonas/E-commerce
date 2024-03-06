package com.ecommerce.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.rest.model.Ware;

@Repository
public interface WaresRepository extends JpaRepository<Ware, Long> {
  List<Ware> findByNameContaining(String name);
  List<Ware> findByOwnerContaining(String owner);
}
