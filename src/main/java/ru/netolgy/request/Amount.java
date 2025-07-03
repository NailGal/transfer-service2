package ru.netolgy.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

// Amount.java
@Data
public class Amount {
    @Min(1)
    private long value;

    @NotBlank
    private String currency;
}

