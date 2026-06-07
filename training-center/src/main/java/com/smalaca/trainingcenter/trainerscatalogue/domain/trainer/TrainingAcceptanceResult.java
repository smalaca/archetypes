package com.smalaca.trainingcenter.trainerscatalogue.domain.trainer;

import com.smalaca.annotations.architecture.DomainDrivenDesign;

@DomainDrivenDesign.ValueObject
public class TrainingAcceptanceResult {
    private enum Status { ACCEPTED, REJECTED, MANUAL_INTERVENTION }

    private final Status status;
    private final String reason;

    private TrainingAcceptanceResult(Status status, String reason) {
        this.status = status;
        this.reason = reason;
    }

    @DomainDrivenDesign.Factory
    public static TrainingAcceptanceResult accepted() {
        return new TrainingAcceptanceResult(Status.ACCEPTED, null);
    }

    @DomainDrivenDesign.Factory
    public static TrainingAcceptanceResult rejected(String reason) {
        return new TrainingAcceptanceResult(Status.REJECTED, reason);
    }

    @DomainDrivenDesign.Factory
    public static TrainingAcceptanceResult manualIntervention(String reason) {
        return new TrainingAcceptanceResult(Status.MANUAL_INTERVENTION, reason);
    }

    public boolean isAccepted() {
        return status == Status.ACCEPTED;
    }

    public boolean isRejected() {
        return status == Status.REJECTED;
    }

    public boolean requiresManualIntervention() {
        return status == Status.MANUAL_INTERVENTION;
    }

    public String getReason() {
        return reason;
    }
}
