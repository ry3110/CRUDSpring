package com.crudapi.CRUDSpring.repository;

import com.crudapi.CRUDSpring.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity,Integer> {
    ProductEntity findByName(String name);

}
