package ru.sanjar.bank.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sanjar.bank.services.IStatisticService;

import java.io.IOException;
import java.security.Principal;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/statistic")
public class StatisticController {
    private final IStatisticService statisticService;

    public StatisticController(IStatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @GetMapping("/expenses/{card}")
    public ResponseEntity<?> getCardExpenses(Principal principal, @PathVariable String card) throws JsonProcessingException {
        return statisticService.getExpenses(principal, card);
    }

    @GetMapping("/deposits/{card}")
    public ResponseEntity<?> getCardDeposits(Principal principal, @PathVariable String card) throws IOException, InterruptedException {
        return statisticService.getDeposits(principal, card);
    }
}
