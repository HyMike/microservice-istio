package com.example.applicationservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class UnderwritingClient {
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${underwriting.base-url}")
    private String underwritingBaseUrl;

    @Value("${underwriting.routing.simulation:auto}")
    private String defaultRouting;

    public Map<String, Object> requestDecision(String applicationId, double amount, String purpose, String routePreference) {
        String selectedRoute = routePreference == null || routePreference.isBlank() ? defaultRouting : routePreference;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(Map.of(
                "applicationId", applicationId,
                "normalizedAmount", amount,
                "loanPurpose", purpose,
                "routePreference", selectedRoute
        ), headers);

        return restTemplate.postForObject(
                underwritingBaseUrl + "/internal/underwriting-decisions",
                entity,
                Map.class
        );
    }
}
