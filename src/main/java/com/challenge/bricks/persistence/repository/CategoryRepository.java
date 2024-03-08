package com.challenge.bricks.persistence.repository;

import com.challenge.bricks.persistence.model.Category;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long>, JpaSpecificationExecutor {

    List<Category> findAll();

    Optional<Category> findTop1ByName(String name);
}
