package com.example.underwritingservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Random;
import java.util.UUID;

@Service
public class DecisionService {
    private final Random random = new Random();

    @Value("${underwriting.routing.alternate-weight:20}")
    private int alternateWeight;

    public Map<String, Object> evaluate(String applicationId, double amount, String purpose, String routePreference) {
        String source = resolveSource(routePreference);
        String outcome = amount <= 750000 ? "approved" : "conditionally-approved";
        String reason = source.equals("alternate")
                ? "alternate path synthetic review"
                : "primary path synthetic review";

        return Map.of(
                "decisionId", UUID.randomUUID().toString(),
                "applicationId", applicationId,
                "decisionOutcome", outcome,
                "decisionSource", source,
                "decisionReason", reason,
                "loanPurpose", purpose
        );
    }

    private String resolveSource(String routePreference) {
        if ("primary".equalsIgnoreCase(routePreference)) {
            return "primary";
        }
        if ("alternate".equalsIgnoreCase(routePreference)) {
            return "alternate";
        }
        return random.nextInt(100) < alternateWeight ? "alternate" : "primary";
    }
}
