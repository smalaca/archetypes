package com.smalaca.trainingcenter.trainingorders.domain.trainingorder;

import com.smalaca.annotations.architecture.DomainDrivenDesign;
import com.smalaca.trainingcenter.trainingorders.domain.clock.Clock;

import java.util.List;

@DomainDrivenDesign.Factory
public class TrainingOrderFactory {
    private final Clock clock;

    public TrainingOrderFactory(Clock clock) {
        this.clock = clock;
    }

    public TrainingOrder create(List<OrderParticipant> participants, List<TrainingOrderLine> orderLines) {
        validateParticipants(participants);

        return TrainingOrder.created(participants, orderLines, clock.now());
    }

    private void validateParticipants(List<OrderParticipant> participants) {
        validateExactlyOneSeller(participants);
        validateAtLeastOneBuyer(participants);
    }

    private void validateExactlyOneSeller(List<OrderParticipant> participants) {
        long sellersCount = participants.stream()
                .filter(OrderParticipant::isSeller)
                .count();

        if (sellersCount != 1) {
            throw new RuntimeException("Training Order must have exactly one SELLER.");
        }
    }

    private void validateAtLeastOneBuyer(List<OrderParticipant> participants) {
        boolean hasBuyer = participants.stream()
                .anyMatch(OrderParticipant::isBuyer);

        if (!hasBuyer) {
            throw new RuntimeException("Training Order must have at least one BUYER.");
        }
    }
}
