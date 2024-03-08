package com.challenge.bricks.controller.request;

import lombok.*;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductUpdateRequest {

    @Size(min = 1, max = 200)
    @NotNull
    @Pattern(regexp =  "^(?:[a-zA-ZzÀ-ÿ0-9\0\\s._\\-ñÑ(),'\"\\\\/&]|)+", message = "[productName not valid")
    private String name;

    @Digits(integer = 15, fraction = 2)
    @NotNull
    private BigDecimal price;

    @NotNull
    private Integer stock;
    @NotNull
    private Long categoryId;
    @NotNull
    private Boolean active;
}
