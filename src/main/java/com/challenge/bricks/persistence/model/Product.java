package com.challenge.bricks.persistence.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "PRODUCT")
public class Product implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NM_PRODUCT_ID", length = 25, nullable = false)
    private Long productId;

    @Column(name = "VA_NAME", length = 250, nullable = false)
    private String name;

    @Column(name = "NM_PRICE", nullable = false, precision = 6, scale = 2)
    private BigDecimal price;

    @Column(name = "NM_STOCK", length = 6, nullable = false, columnDefinition = "integer default 0")
    private Integer stock;

    @Column(name = "BO_ACTIVE", nullable = false)
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "NM_CATEGORY_ID", nullable = false, referencedColumnName = "NM_CATEGORY_ID"
            , foreignKey = @ForeignKey(name = "fk_Product_Category"))
    private Category category;

}
