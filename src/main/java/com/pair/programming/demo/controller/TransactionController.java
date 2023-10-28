package com.pair.programming.demo.controller;

import com.pair.programming.demo.dto.TransactionDto;
import com.pair.programming.demo.exception.ObjectException;
import com.pair.programming.demo.request.TransactionRequest;
import com.pair.programming.demo.service.TransactionService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.pair.programming.demo.utils.Constant.*;

@Slf4j
@RestController
@RequestMapping(BASE_URI)
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
    @GetMapping
    public ResponseEntity<List<TransactionDto>> getAll(){
        log.info("Inside getAll method of controller layer ");
        try {
            return ResponseEntity.ok(transactionService.getAll());
        }catch (Exception e){
            throw new ObjectException(e.getMessage());
        }
    }

    @GetMapping(ID)
    public ResponseEntity<TransactionDto> getById(@PathVariable Long id){
        log.info("Inside getById method of controller layer, Id: "+id);
        try {
            return ResponseEntity.ok(transactionService.getById(id));
        }catch (Exception e){
            throw new ObjectException(e.getMessage());
        }
    }
    @DeleteMapping(ID)
    public ResponseEntity<TransactionDto> deleteById(@PathVariable Long id){
        log.info("Inside deleteById method of controller layer, Id: "+id);
        try {
            return ResponseEntity.ok(transactionService.deleteById(id));
        }catch (Exception e){
            throw new ObjectException(e.getMessage());
        }
    }
    @PostMapping
    public ResponseEntity<TransactionDto> add(@Valid @RequestBody TransactionRequest transactionRequest){
        log.info("Inside add method of controller layer, RequestBody: "+transactionRequest);
        try {
            return ResponseEntity.ok(transactionService.add(transactionRequest));
        }catch (Exception e){
            throw new ObjectException(e.getMessage());
        }
    }
    @PutMapping(ID)
    public ResponseEntity<TransactionDto> update(@PathVariable Long id,
                                                 @Valid @RequestBody TransactionRequest transactionRequest){
        log.info("Inside update method of controller layer, Id: "+id);
        try {
            return ResponseEntity.ok(transactionService.update(id,transactionRequest));
        }catch (Exception e){
            throw new ObjectException(e.getMessage());
        }
    }
    @GetMapping(RECURRING)
    public ResponseEntity<List<TransactionDto>> getRecurringTransactionsForOneWeek(
            @RequestParam(name = "startDate") String startDate) {
        log.info("Inside getRecurringTransactionsForOneWeek method of controller layer, Id: "+startDate);
        try {
            return ResponseEntity.ok(transactionService.findRecurringTransactionsForOneWeek(startDate));
        }catch (Exception e){
            throw new ObjectException(e.getMessage());
        }
    }
}
