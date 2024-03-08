package com.challenge.bricks.persistence.repository;

import com.challenge.bricks.persistence.model.Product;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long>, JpaSpecificationExecutor {
}
