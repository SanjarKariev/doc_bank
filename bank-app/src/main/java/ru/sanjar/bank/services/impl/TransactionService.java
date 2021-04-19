package ru.sanjar.bank.services.impl;

import ru.sanjar.bank.models.Card;
import ru.sanjar.bank.models.ETransferType;
import ru.sanjar.bank.models.Transaction;
import ru.sanjar.bank.repository.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
class TransactionService {
    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public void create(Card from, Card to, ETransferType type, Double amount) {
        Transaction transaction = new Transaction(from, to, amount, type);
        repository.save(transaction);
    }

    public void create(Card from, ETransferType type, Double amount) {
        Transaction transaction = new Transaction(from, amount, type);
        repository.save(transaction);
    }

    public void create(int i, Card to, ETransferType type, Double amount) {
        Transaction transaction = new Transaction(i, to, amount, type);
        repository.save(transaction);
    }
}
