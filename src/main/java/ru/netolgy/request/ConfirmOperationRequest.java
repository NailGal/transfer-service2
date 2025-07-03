package ru.netolgy.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

// ConfirmOperationRequest.java
@Data
public class ConfirmOperationRequest {
    @NotBlank
    private String operationId;

    @NotBlank @Size(min = 4, max = 4)
    private String code;
}

