package com.pair.programming.demo.dto;

import com.pair.programming.demo.Enum.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {
    private Long id;
    private String description;
    private double amount;
    private Date date;
    private TransactionType type;
}
