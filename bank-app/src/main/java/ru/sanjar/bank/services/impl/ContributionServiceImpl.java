package ru.sanjar.bank.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.sanjar.bank.models.Card;
import ru.sanjar.bank.models.Contribution;
import ru.sanjar.bank.models.ETransferType;
import ru.sanjar.bank.models.User;
import ru.sanjar.bank.payload.response.ContributionResponse;
import ru.sanjar.bank.payload.response.ServerResponse;
import ru.sanjar.bank.repository.CardRepository;
import ru.sanjar.bank.repository.ContributionRepository;
import ru.sanjar.bank.repository.UserRepository;
import ru.sanjar.bank.services.IContributionService;

import java.io.IOException;
import java.security.Principal;
import java.util.*;

@Service
class ContributionServiceImpl implements IContributionService {
    private final ContributionRepository contributionRepository;
    private final UserRepository userRepository;
    private final CardRepository cardRepository;
    private final TransactionService transactionService;

    private final CurrencyServiceImpl currencyService;

    @Value("${fadesml.app.profitPrc}")
    private String dayProfit;

    public ContributionServiceImpl(ContributionRepository contributionRepository, UserRepository userRepository, CardRepository cardRepository, TransactionService transactionService, CurrencyServiceImpl currencyService) {
        this.contributionRepository = contributionRepository;
        this.userRepository = userRepository;
        this.cardRepository = cardRepository;
        this.transactionService = transactionService;
        this.currencyService = currencyService;
    }


    @Override
    public ResponseEntity<?> get(Principal principal) throws JsonProcessingException {
        User user = userRepository.findByUsername(principal.getName()).get();
        List<ContributionResponse> result = new ArrayList<>();

        if (!user.getContributions().isEmpty()) {
            for (Contribution contribution : user.getContributions()) {
                result.add(contribution.toResponse());
            }

            return ServerResponse.response(true, result, HttpStatus.OK);
        } else {
            return ServerResponse.response(false, "contributions_not_found", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> create(Principal principal, String card, Date date, Double amount) throws JsonProcessingException {
        if (date.getTime() > new Date().getTime()) {
            User user = userRepository.findByUsername(principal.getName()).get();

            for (Card userCard : user.getCards()) {
                if (userCard.getId().equals(card)) {
                    if (userCard.canRemove(amount)) {
                        Contribution contribution = new Contribution(date, amount, userCard.getCurrency(), user);
                        userCard.setBalance(userCard.getBalance() - amount);

                        transactionService.create(userCard, ETransferType.CONTRIBUTION_OPEN, amount);
                        cardRepository.save(userCard);
                        contributionRepository.save(contribution);

                        return ServerResponse.response(true, "contribution_created", HttpStatus.CREATED);
                    } else {
                        return ServerResponse.response(false, "lack_of_balance", HttpStatus.BAD_REQUEST);
                    }
                }
            }

            return ServerResponse.response(false, "card_not_found", HttpStatus.NOT_FOUND);
        } else {
            return ServerResponse.response(false, "wrong_date", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> take(Principal principal, String contribution, String card) throws IOException, InterruptedException {
        User user = userRepository.findByUsername(principal.getName()).get();
        Card currentCard = null;
        for (Card userCard : user.getCards()) {
            if (userCard.getId().equals(card)) {
               currentCard = userCard;
            }
        }

        if (currentCard != null) {
            for (Contribution userContribution : user.getContributions()) {
                if (userContribution.getId().equals(contribution)) {
                    if (userContribution.isInProgress()) {
                        if (userContribution.getEndDate().getTime() <= new Date().getTime()) {
                            int daysBetween = daysBetween(userContribution.getStartDate(), userContribution.getEndDate());
                            double profit = (userContribution.getAmount()/Integer.parseInt(dayProfit)) * daysBetween;
                            double currency = currencyService.getCurrency(userContribution.getCurrency().name(), currentCard.getCurrency().name());

                            currentCard.setBalance(currentCard.getBalance() + ((userContribution.getAmount() + profit) * currency));

                            transactionService.create(1, currentCard, ETransferType.CONTRIBUTION_CLOSED, ((userContribution.getAmount() + profit) * currency));
                            cardRepository.save(currentCard);
                            userContribution.setInProgress(false);
                            contributionRepository.save(userContribution);

                            return ServerResponse.response(true, "contribution_closed", HttpStatus.OK);
                        } else {
                            return ServerResponse.response(false, "contribution_date_wrong", HttpStatus.BAD_REQUEST);
                        }
                    } else {
                        return ServerResponse.response(false, "contribution_is_not_in_progress", HttpStatus.BAD_REQUEST);
                    }
                }
            }
        } else {
            return ServerResponse.response(false, "card_not_found", HttpStatus.NOT_FOUND);
        }

        return ServerResponse.response(false, "contribution_not_found", HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> calculateProfit(Double amount, Date startDate, Date endDate) throws JsonProcessingException {
        if (startDate.getTime() < endDate.getTime()) {
            int daysBetween = daysBetween(startDate, endDate);

            return ServerResponse.response(true, String.valueOf(amount/Integer.parseInt(dayProfit) * daysBetween), HttpStatus.OK);
        }

        return ServerResponse.response(false, "wrong_date", HttpStatus.BAD_REQUEST);
    }

    private int daysBetween(Date d1, Date d2){
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }
}
