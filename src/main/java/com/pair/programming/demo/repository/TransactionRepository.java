package com.pair.programming.demo.repository;

import com.pair.programming.demo.Enum.TransactionType;
import com.pair.programming.demo.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT t FROM Transaction t WHERE t.date BETWEEN :startDate AND :endDate")
    List<Transaction> findRecurringTransactionsForOneWeek(Date startDate, Date endDate);
}
