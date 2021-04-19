package ru.sanjar.bank.repository;

import org.springframework.stereotype.Repository;
import ru.sanjar.bank.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
}
