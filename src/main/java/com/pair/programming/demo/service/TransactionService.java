package com.pair.programming.demo.service;

import com.pair.programming.demo.Enum.TransactionType;
import com.pair.programming.demo.dto.TransactionDto;
import com.pair.programming.demo.request.RecurringTransactionRequest;
import com.pair.programming.demo.request.TransactionRequest;

import java.util.Date;
import java.util.List;

public interface TransactionService {
    TransactionDto add(TransactionRequest transactionRequest);
    List<TransactionDto> getAll();
    TransactionDto getById(Long id);
    TransactionDto deleteById(Long id);
    TransactionDto update(Long id, TransactionRequest transactionRequest);
    List<TransactionDto> findRecurringTransactionsForOneWeek(String startDate);
}
