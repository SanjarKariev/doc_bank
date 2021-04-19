package ru.sanjar.bank.repository;

import org.springframework.stereotype.Repository;
import ru.sanjar.bank.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CardRepository extends JpaRepository<Card, String> {
}
