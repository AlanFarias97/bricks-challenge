package com.challenge.bricks.controller.response;

import com.challenge.bricks.persistence.model.Product;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductListResponse {

    private Long productId;
    private String name;
    private Integer stock;
    private BigDecimal price;
    private Boolean active;
    private CategoryDTO category;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CategoryDTO{
        private Long categoryId;
        private String categoryName;
    }
    public static ProductListResponse convertTo(Product p) {
        return ProductListResponse.builder()
                .productId(p.getProductId())
                .name(p.getName())
                .price(p.getPrice())
                .stock(p.getStock())
                .category(CategoryDTO.builder()
                        .categoryId(p.getCategory().getId())
                        .categoryName(p.getCategory().getName())
                        .build())
                .active(p.getActive())
                .build();
    }
}
