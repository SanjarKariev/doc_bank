package ru.sanjar.bank.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.security.Principal;
import java.util.Date;

public interface IContributionService {
    ResponseEntity<?> get(Principal principal) throws JsonProcessingException;
    ResponseEntity<?> create(Principal principal, String card, Date date, Double amount) throws JsonProcessingException;
    ResponseEntity<?> take(Principal principal, String contribution, String card) throws IOException, InterruptedException;
    ResponseEntity<?> calculateProfit(Double amount, Date startDate, Date endDate) throws JsonProcessingException;
}
