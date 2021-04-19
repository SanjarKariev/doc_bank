package ru.sanjar.bank.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sanjar.bank.models.ECurrency;
import ru.sanjar.bank.models.ETransferType;
import ru.sanjar.bank.payload.response.ServerResponse;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class EnumController {
    @GetMapping("/currency")
    public ResponseEntity<?> getAllowedCurrency() throws JsonProcessingException {
        return ServerResponse.response(true, ECurrency.values(), HttpStatus.OK);
    }

    @GetMapping("/transaction/types")
    public ResponseEntity<?> getTransactionTypes() throws JsonProcessingException {
        List<String> transferTypes = new ArrayList<>();

        for (ETransferType value : ETransferType.values()) {
            if (!value.name().equals("CONTRIBUTION_OPEN") && !value.name().equals("CONTRIBUTION_CLOSED")) {
                transferTypes.add(value.name());
            }
        }

        return ServerResponse.response(true, transferTypes, HttpStatus.OK);
    }
}
