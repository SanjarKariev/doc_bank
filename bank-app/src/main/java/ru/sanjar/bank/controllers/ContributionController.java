package ru.sanjar.bank.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sanjar.bank.payload.request.CalculateProfitRequest;
import ru.sanjar.bank.payload.request.ContributionCreateRequest;
import ru.sanjar.bank.payload.request.ContributionTakeRequest;
import ru.sanjar.bank.services.IContributionService;

import java.io.IOException;
import java.security.Principal;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/contribution")
public class ContributionController {
    private final IContributionService contributionService;

    public ContributionController(IContributionService contributionService) {
        this.contributionService = contributionService;
    }

    @GetMapping
    public ResponseEntity<?> getContributions(Principal principal) throws JsonProcessingException {
        return contributionService.get(principal);
    }

    @PostMapping("/profit")
    public ResponseEntity<?> calculateProfit(@RequestBody CalculateProfitRequest profitRequest) throws JsonProcessingException {
        return contributionService.calculateProfit(profitRequest.getAmount(), profitRequest.getStartDate(), profitRequest.getEndDate());
    }

    @PostMapping("/create")
    public ResponseEntity<?> createContribution(Principal principal, @RequestBody ContributionCreateRequest createRequest) throws JsonProcessingException {
        return contributionService.create(principal, createRequest.getCard(), createRequest.getDate(), createRequest.getAmount());
    }

    @PostMapping("/take")
    public ResponseEntity<?> takeContribution(Principal principal, @RequestBody ContributionTakeRequest takeRequest) throws IOException, InterruptedException {
        return contributionService.take(principal, takeRequest.getContribution(), takeRequest.getCard());
    }
}
