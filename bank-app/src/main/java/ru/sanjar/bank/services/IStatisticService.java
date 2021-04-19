package ru.sanjar.bank.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.security.Principal;

public interface IStatisticService {
    ResponseEntity<?> getExpenses(Principal principal, String card) throws JsonProcessingException;
    ResponseEntity<?> getDeposits(Principal principal, String card) throws IOException, InterruptedException;
}
