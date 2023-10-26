package com.pair.programming.demo.utils;

import com.pair.programming.demo.dto.TransactionDto;
import com.pair.programming.demo.entity.Transaction;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Converter {
    private final ModelMapper modelMapper;

    @Autowired
    public Converter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    public Transaction convertToEntity(TransactionDto transactionDto){
        return modelMapper.map(transactionDto, Transaction.class);
    }
    public TransactionDto convertToDto(Transaction transaction){
        return modelMapper.map(transaction, TransactionDto.class);
    }
}
