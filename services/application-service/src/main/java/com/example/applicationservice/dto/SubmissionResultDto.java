package com.example.applicationservice.api.dto;

public record SubmissionResultDto(
        String applicationId,
        String status,
        String decisionOutcome,
        String decisionSource,
        String decisionReason
) {}
