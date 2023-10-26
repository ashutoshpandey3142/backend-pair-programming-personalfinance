package com.pair.programming.demo.request;

import com.pair.programming.demo.Enum.TransactionType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import static com.pair.programming.demo.utils.Constant.AMOUNT_NOT_PRESENT;
import static com.pair.programming.demo.utils.Constant.DESCRIPTION_NOT_PRESENT;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {
    @NotNull(message = DESCRIPTION_NOT_PRESENT)
    private String description;
    @NotNull(message = AMOUNT_NOT_PRESENT)
    private double amount;
    private Date date;
    @Enumerated(EnumType.STRING)
    private TransactionType type;
}
