package com.ess.essserver.module.asset;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AssetRequestDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Price is required")
    private double price;

    @NotBlank(message = "Category is required")
    private String category;

    @NotBlank(message = "Condition is required")
    private String condition;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    @NotBlank(message = "Status is required")
    private String status;
}
