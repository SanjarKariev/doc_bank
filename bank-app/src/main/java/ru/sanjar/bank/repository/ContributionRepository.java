package ru.sanjar.bank.repository;

import org.springframework.data.repository.CrudRepository;
import ru.sanjar.bank.models.Contribution;

public interface ContributionRepository extends CrudRepository<Contribution, String> {
}
