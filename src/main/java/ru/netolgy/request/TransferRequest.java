package ru.netolgy.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

// TransferRequest.java
@Data
public class TransferRequest {
    @NotBlank
    @Size(min = 16, max = 16)
    private String cardFromNumber;

    @NotBlank @Pattern(regexp = "^(0[1-9]|1[0-2])/[0-9]{2}$")
    private String cardFromValidTill;

    @NotBlank @Size(min = 3, max = 3)
    private String cardFromCVV;

    @NotBlank @Size(min = 16, max = 16)
    private String cardToNumber;

    @Valid
    private Amount amount;
}


