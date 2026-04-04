package com.example.applicationservice.api;

import com.example.applicationservice.api.dto.SubmissionResultDto;
import com.example.applicationservice.service.UnderwritingClient;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@RestController
public class ApplicationController {
    private final UnderwritingClient underwritingClient;

    public ApplicationController(UnderwritingClient underwritingClient) {
        this.underwritingClient = underwritingClient;
    }

    @PostMapping("/api/loan-applications")
    public SubmissionResultDto submit(@RequestBody LoanApplicationRequest request) {
        String applicationId = UUID.randomUUID().toString();
        Map<String, Object> decision = underwritingClient.requestDecision(
                applicationId,
                request.loanAmount(),
                request.loanPurpose(),
                request.routePreference()
        );

        return new SubmissionResultDto(
                applicationId,
                "decided",
                String.valueOf(decision.getOrDefault("decisionOutcome", "conditionally-approved")),
                String.valueOf(decision.getOrDefault("decisionSource", "primary")),
                String.valueOf(decision.getOrDefault("decisionReason", "synthetic decision"))
        );
    }

    public record LoanApplicationRequest(
            @NotBlank String applicantName,
            @NotNull @Min(1) Double loanAmount,
            @NotBlank String loanPurpose,
            @NotNull @Min(1) Integer requestedTermMonths,
            String routePreference
    ) {}
}
