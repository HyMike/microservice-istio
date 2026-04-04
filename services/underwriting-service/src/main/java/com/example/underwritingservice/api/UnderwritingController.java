package com.example.underwritingservice.api;

import com.example.underwritingservice.service.DecisionService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UnderwritingController {
    private final DecisionService decisionService;

    public UnderwritingController(DecisionService decisionService) {
        this.decisionService = decisionService;
    }

    @PostMapping("/internal/underwriting-decisions")
    public Map<String, Object> decide(@RequestBody UnderwritingRequest request) {
        return decisionService.evaluate(
                request.applicationId(),
                request.normalizedAmount(),
                request.loanPurpose(),
                request.routePreference()
        );
    }

    public record UnderwritingRequest(
            @NotBlank String applicationId,
            @NotNull @Min(1) Double normalizedAmount,
            @NotBlank String loanPurpose,
            String routePreference
    ) {}
}
