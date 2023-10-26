package com.pair.programming.demo.service.implementation;

import com.pair.programming.demo.dto.TransactionDto;
import com.pair.programming.demo.entity.Transaction;
import com.pair.programming.demo.exception.ObjectException;
import com.pair.programming.demo.repository.TransactionRepository;
import com.pair.programming.demo.request.TransactionRequest;
import com.pair.programming.demo.service.TransactionService;
import com.pair.programming.demo.utils.Converter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.pair.programming.demo.utils.Constant.TRANSACTION_NOT_FOUND;

@Slf4j
@Service
public class TransactionImplementation implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final Converter converter;
    @Autowired
    public TransactionImplementation(TransactionRepository transactionRepository, Converter converter){
        this.transactionRepository = transactionRepository;
        this.converter = converter;
    }
    @Override
    public TransactionDto add(TransactionRequest transactionRequest) {
        log.info("Inside add method of Service layer, RequestBody: "+transactionRequest);
        try {
            Transaction transaction = new Transaction();
            transaction.setAmount(transactionRequest.getAmount());
            transaction.setDescription(transactionRequest.getDescription());
            transaction.setDate(transactionRequest.getDate());
            transaction.setType(transactionRequest.getType());
            transactionRepository.save(transaction);
            return converter.convertToDto(transaction);
        }catch (Exception e){
            throw new ObjectException(e.getMessage());
        }
    }

    @Override
    public List<TransactionDto> getAll() {
        log.info("Inside getAll method of Service layer");
        try {
            List<Transaction> transactions = transactionRepository.findAll();
            return transactions.stream()
                    .map(converter::convertToDto).toList();
        }catch (Exception e){
            throw new ObjectException(e.getMessage());
        }
    }

    @Override
    public TransactionDto getById(Long id) {
        log.info("Inside getById method of Service layer, ID: "+id);
        try {
            Transaction transaction = transactionRepository.findById(id)
                    .orElseThrow(() -> new ObjectException(TRANSACTION_NOT_FOUND+": "+id));
            return converter.convertToDto(transaction);
        }catch (Exception e){
            throw new ObjectException(e.getMessage());
        }
    }

    @Override
    public TransactionDto deleteById(Long id) {
        log.info("Inside deleteById method of Service layer, ID: "+id);
        try {
            Transaction transaction = transactionRepository.findById(id)
                    .orElseThrow(() -> new ObjectException("Transaction not found"+id));
            transactionRepository.deleteById(id);
            return converter.convertToDto(transaction);
        }catch (Exception e){
            throw new ObjectException(e.getMessage());
        }
    }

    @Override
    public TransactionDto update(Long id, TransactionRequest transactionRequest) {
        log.info("Inside update method of Service layer, ID: "+id + " Request Body: "+transactionRequest);
        try {
            Transaction transaction = transactionRepository.findById(id)
                    .orElseThrow(() -> new ObjectException("Transaction not found"+id));
            transaction.setAmount(transactionRequest.getAmount());
            transaction.setDescription(transactionRequest.getDescription());
            transaction.setDate(transactionRequest.getDate());
            transaction.setType(transactionRequest.getType());
            transactionRepository.save(transaction);
            return converter.convertToDto(transaction);

        }catch (Exception e){
            throw new ObjectException(e.getMessage());
        }
    }

    @Override
    public List<TransactionDto> findRecurringTransactionsForOneWeek(String startDate)  {
        log.info("Inside findRecurringTransactionsForOneWeek method of Service layer, date: "+startDate);
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = dateFormat.parse(startDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(parsedDate);
            calendar.add(Calendar.DAY_OF_YEAR, 7);
            Date endDate = calendar.getTime();

            List<Transaction> recurringTransaction = transactionRepository.
                    findRecurringTransactionsForOneWeek(parsedDate, endDate);
            return recurringTransaction.stream().map(converter::convertToDto).toList();
        }catch (Exception e){
            throw new ObjectException(e.getMessage());
        }
    }

}
