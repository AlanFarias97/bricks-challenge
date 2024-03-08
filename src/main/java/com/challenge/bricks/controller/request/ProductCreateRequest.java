package com.challenge.bricks.controller.request;

import lombok.*;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductCreateRequest {

    @NotNull
    @Size(min = 1, max = 200)
    @Pattern(regexp =  "^(?:[a-zA-ZzÀ-ÿ0-9\0\\s._\\-ñÑ(),'\"\\\\/&]|)+", message = "[productName not valid")
    private String name;

    @NotNull
    @Digits(integer = 15, fraction = 2)
    private BigDecimal price;
    @NotNull
    private Integer stock;
    @NotNull
    private Long categoryId;
    @NotNull
    private Boolean active;
}
