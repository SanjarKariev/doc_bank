package ru.sanjar.bank.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Service
public class CurrencyServiceImpl {
    @Value("${fadesml.app.currencyApiKey}")
    private String currencyApiKey;

    private final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(30))
            .build();

    protected double getCurrency(String from, String to) throws IOException, InterruptedException {
        String currency = from.toUpperCase() + "_" + to.toUpperCase();

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://free.currconv.com/api/v7/convert?q=" + currency + "&compact=ultra&apiKey=" + currencyApiKey))
                .setHeader("User-Agent", "Java 11 HttpClient Bot")
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        ObjectNode node = new ObjectMapper().readValue(response.body(), ObjectNode.class);

        if (node.has(currency)) {
            return node.get(currency).asDouble();
        }

        return 1;
    }
}
