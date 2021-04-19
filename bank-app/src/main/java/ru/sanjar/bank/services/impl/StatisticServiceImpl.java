package ru.sanjar.bank.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.sanjar.bank.models.Card;
import ru.sanjar.bank.models.Transaction;
import ru.sanjar.bank.models.User;
import ru.sanjar.bank.payload.response.ServerResponse;
import ru.sanjar.bank.payload.response.TransactionResponse;
import ru.sanjar.bank.repository.UserRepository;
import ru.sanjar.bank.services.IStatisticService;

import java.io.IOException;
import java.security.Principal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Service
class StatisticServiceImpl implements IStatisticService {
    private final UserRepository userRepository;
    private final CurrencyServiceImpl currencyService;
    private final DecimalFormat decimalFormat = new DecimalFormat("#.###");

    public StatisticServiceImpl(UserRepository userRepository, CurrencyServiceImpl currencyService) {
        this.userRepository = userRepository;
        this.currencyService = currencyService;
    }

    @Override
    public ResponseEntity<?> getExpenses(Principal principal, String card) throws JsonProcessingException {
        User user = userRepository.findByUsername(principal.getName()).get();

        for (Card userCard : user.getCards()) {
            if (userCard.getId().equals(card)) {
                if (!userCard.getSend().isEmpty()) {
                    List<TransactionResponse> result = new ArrayList<>();
                    for (Transaction transaction : userCard.getSend()) {
                        transaction.setAmount(Double.valueOf(decimalFormat.format(transaction.getAmount()).replace(",", ".")));
                        result.add(transaction.toResponse());
                    }

                    return ServerResponse.response(true, result, HttpStatus.OK);
                } else {
                    return ServerResponse.response(false, "transactions_not_found", HttpStatus.NOT_FOUND);
                }
            }
        }

        return ServerResponse.response(false, "card_not_found", HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> getDeposits(Principal principal, String card) throws IOException, InterruptedException {
        User user = userRepository.findByUsername(principal.getName()).get();

        for (Card userCard : user.getCards()) {
            if (userCard.getId().equals(card)) {
                if (!userCard.getTaken().isEmpty()) {
                    List<TransactionResponse> result = new ArrayList<>();
                    for (Transaction transaction : userCard.getTaken()) {
                        String fromCurrency = null;
                        String toCurrency = null;

                        try {
                            if (transaction.getFrom() != null) {
                                fromCurrency = transaction.getFrom().getCurrency().name();
                            }
                        } catch (NullPointerException ignored) {}

                        try {
                            if (transaction.getFrom() != null) {
                                toCurrency = transaction.getTo().getCurrency().name();
                            }
                        } catch (NullPointerException ignored) {}

                        if (fromCurrency != null && toCurrency != null) {
                            Double currency = currencyService.getCurrency(transaction.getFrom().getCurrency().name(), transaction.getTo().getCurrency().name());
                            transaction.setAmount(Double.valueOf(decimalFormat.format(transaction.getAmount() * currency).replace(",", ".")));
                        } else {
                            transaction.setAmount(Double.valueOf(decimalFormat.format(transaction.getAmount()).replace(",", ".")));
                        }

                        result.add(transaction.toResponse());
                    }

                    return ServerResponse.response(true, result, HttpStatus.OK);
                } else {
                    return ServerResponse.response(false, "transactions_not_found", HttpStatus.NOT_FOUND);
                }
            }
        }

        return ServerResponse.response(false, "card_not_found", HttpStatus.NOT_FOUND);
    }
}
