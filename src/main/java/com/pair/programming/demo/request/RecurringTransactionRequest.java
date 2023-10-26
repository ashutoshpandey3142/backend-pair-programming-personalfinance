package com.pair.programming.demo.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.pair.programming.demo.utils.Constant.AMOUNT_NOT_PRESENT;
import static com.pair.programming.demo.utils.Constant.DESCRIPTION_NOT_PRESENT;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecurringTransactionRequest {
    @NotNull(message = DESCRIPTION_NOT_PRESENT)
    private String description;
    @NotNull(message = AMOUNT_NOT_PRESENT)
    private double amount;
}
