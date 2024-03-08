package com.challenge.bricks.persistence.criteria;

import com.challenge.bricks.persistence.model.Product;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public final class ProductSpecification {
    public static Specification<Product> findProductByCriteria(ProductCriteria criteria) {
        return (productRoot, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(criteria!=null) {

                if (criteria.name != null) {
                    predicates.add(cb.like(cb.lower(productRoot.get("name")), "%" + criteria.getName().toLowerCase() + "%"));
                }

                if (criteria.price != null) {
                    predicates.add(cb.equal(productRoot.get("price"), criteria.price));
                }

                if (criteria.stock != null) {
                    predicates.add(cb.equal(productRoot.get("stock"), criteria.stock));
                }

                if (criteria.category != null) {
                    predicates.add(cb.equal(productRoot.get("category"), criteria.category));
                }

            }
            return cb.and(predicates.toArray(Predicate[]::new));
        };
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProductCriteria {
        private String name;
        private BigDecimal price;
        private Integer stock;
        private Long category;

    }
}
