package ru.sanjar.bank.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.sanjar.bank.models.Card;
import ru.sanjar.bank.models.ECurrency;
import ru.sanjar.bank.models.ETransferType;
import ru.sanjar.bank.models.User;
import ru.sanjar.bank.payload.response.CardResponse;
import ru.sanjar.bank.payload.response.ServerResponse;
import ru.sanjar.bank.repository.CardRepository;
import ru.sanjar.bank.repository.UserRepository;
import ru.sanjar.bank.services.ICardService;

import java.io.IOException;
import java.security.Principal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Service
class CardServiceImpl implements ICardService {

    private final CurrencyServiceImpl currencyService;
    private final UserRepository userRepository;
    private final CardRepository cardRepository;
    private final TransactionService transactionService;
    private final DecimalFormat decimalFormat = new DecimalFormat("#.###");

    public CardServiceImpl(CurrencyServiceImpl currencyService, UserRepository userRepository, CardRepository cardRepository, TransactionService transactionService) {
        this.currencyService = currencyService;
        this.userRepository = userRepository;
        this.cardRepository = cardRepository;
        this.transactionService = transactionService;
    }

    @Override
    public ResponseEntity<?> get(Principal principal) throws JsonProcessingException {
        User user = userRepository.findByUsername(principal.getName()).get();

        if (user.getCards().isEmpty()) {
            return ServerResponse.response(false, "cards_not_found", HttpStatus.NOT_FOUND);
        }

        List<CardResponse> result = new ArrayList<>();
        for (Card card : user.getCards()) {
            card.setBalance(Double.valueOf(decimalFormat.format(card.getBalance()).replace(",", ".")));
            result.add(card.toResponse());
        }

        return ServerResponse.response(true, result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> create(Principal principal, String currency) throws JsonProcessingException {
        User user = userRepository.findByUsername(principal.getName()).get();
        ECurrency eCurrency = null;
        for (ECurrency value : ECurrency.values()) {
            if (value.name().equals(currency)) {
                eCurrency = value;
            }
        }

        if (eCurrency != null) {
            Card card = new Card(user, eCurrency);
            cardRepository.save(card);

            return ServerResponse.response(true, "card_created", HttpStatus.CREATED);
        } else {
            return ServerResponse.response(false, "currency_not_found", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> delete(Principal principal, String card) throws JsonProcessingException {
        User user = userRepository.findByUsername(principal.getName()).get();

        if (user.getCards().isEmpty()) {
            return ServerResponse.response(false, "cards_not_found", HttpStatus.NOT_FOUND);
        }

        for (Card userCard : user.getCards()) {
            if (userCard.getId().equals(card)) {
                cardRepository.deleteById(card);

                return ServerResponse.response(true, "card_deleted", HttpStatus.OK);
            }
        }

        return ServerResponse.response(false, "card_not_found", HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> rebuild(Principal principal, String card) throws JsonProcessingException {
        User user = userRepository.findByUsername(principal.getName()).get();

        if (user.getCards().isEmpty()) {
            return ServerResponse.response(false, "cards_not_found", HttpStatus.NOT_FOUND);
        }

        for (Card userCard : user.getCards()) {
            if (userCard.getId().equals(card)) {
                if (!userCard.isActive()) {
                    userCard.rebuild();

                    cardRepository.save(userCard);

                    return ServerResponse.response(true, "card_rebuilded", HttpStatus.OK);
                }
            }
        }

        return ServerResponse.response(false, "card_not_found", HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> transfer(Principal principal, String cardFromId, String cardToId, Double amount, ETransferType type) throws IOException, InterruptedException {
        User user = userRepository.findByUsername(principal.getName()).get();
        for (Card cardFrom : user.getCards()) {
            if (cardFrom.getId().equals(cardFromId)) {
                if (cardFrom.isActive()) {
                    if (cardRepository.existsById(cardToId)) {
                        Card cardTo = cardRepository.findById(cardToId).get();
                        if (cardTo.isActive()) {
                            Double currency = currencyService.getCurrency(cardFrom.getCurrency().name(), cardTo.getCurrency().name());
                            if (cardFrom.canRemove(amount)) {
                                cardFrom.setBalance(cardFrom.getBalance() - amount);

                                cardTo.setBalance(cardTo.getBalance() + (amount * currency));

                                cardRepository.save(cardFrom);
                                cardRepository.save(cardTo);
                                transactionService.create(cardFrom, cardTo, type, amount);

                                return ServerResponse.response(true, "transfer_successful", HttpStatus.OK);
                            } else {
                                return ServerResponse.response(false, "lack_of_balance", HttpStatus.BAD_REQUEST);
                            }
                        } else {
                            return ServerResponse.response(false, "card_to_not_active", HttpStatus.BAD_REQUEST);
                        }
                    } else {
                        return ServerResponse.response(false, "transfer_failed_card_to_not_found", HttpStatus.NOT_FOUND);
                    }
                } else {
                    return ServerResponse.response(false, "card_from_not_active", HttpStatus.BAD_REQUEST);
                }
            }
        }

        return ServerResponse.response(false, "transfer_failed_card_from_not_found", HttpStatus.NOT_FOUND);
    }
}
