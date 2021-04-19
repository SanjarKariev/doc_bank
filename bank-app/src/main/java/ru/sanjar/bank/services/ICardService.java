package ru.sanjar.bank.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import ru.sanjar.bank.models.ETransferType;

import java.io.IOException;
import java.security.Principal;

public interface ICardService {
    ResponseEntity<?> get(Principal principal) throws JsonProcessingException;
    ResponseEntity<?> create(Principal principal, String currency) throws JsonProcessingException;
    ResponseEntity<?> delete(Principal principal, String card) throws JsonProcessingException;
    ResponseEntity<?> rebuild(Principal principal, String card) throws JsonProcessingException;
    ResponseEntity<?> transfer(Principal principal, String cardFromId, String cardToId, Double amount, ETransferType type) throws IOException, InterruptedException;
}
