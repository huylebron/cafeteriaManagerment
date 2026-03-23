package com.example.cafeteriamanagerment.product.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductUpsertRequest {
    @NotBlank
    @Size(max = 150)
    private String name;

    @Size(max = 2000)
    private String description;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal price;

    @Size(max = 20)
    private String size;

    @Size(max = 1000)
    private String toppings;
}

