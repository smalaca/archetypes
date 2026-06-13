package com.smalaca.trainingcenter.trainingorders.domain.trainingorder;

import com.smalaca.annotations.architecture.DomainDrivenDesign;
import com.smalaca.trainingcenter.trainingorders.domain.trainingorder.commands.OrderParticipantDto;
import com.smalaca.trainingcenter.trainingorders.domain.trainingorder.commands.TrainingOrderLineDto;
import com.smalaca.trainingcenter.trainingorders.domain.clock.Clock;
import com.smalaca.trainingcenter.trainingorders.domain.trainingorder.events.TrainingOrderOpened;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@DomainDrivenDesign.Factory
public class TrainingOrderFactory {
    private final Clock clock;

    public TrainingOrderFactory(Clock clock) {
        this.clock = clock;
    }

    public OrderCreationResult create(List<OrderParticipantDto> participantsDto, List<TrainingOrderLineDto> orderLinesDto) {
        List<OrderParticipant> participants = participantsDto.stream()
                .map(this::orderParticipant)
                .collect(toList());
        List<TrainingOrderLine> orderLines = orderLinesDto.stream()
                .map(this::trainingOrderLine)
                .collect(toList());

        validateParticipants(participants);

        LocalDateTime now = clock.now();
        TrainingOrder trainingOrder = TrainingOrder.created(participants, orderLines, now);
        TrainingOrderOpened event = new TrainingOrderOpened(trainingOrder.getTrainingOrderId().id(), now);

        return new OrderCreationResult(trainingOrder, event);
    }

    private OrderParticipant orderParticipant(OrderParticipantDto dto) {
        return new OrderParticipant(
                new OrderParticipantId(dto.participantId()),
                dto.displayName(),
                OrderParticipantRole.valueOf(dto.role()));
    }

    private TrainingOrderLine trainingOrderLine(TrainingOrderLineDto dto) {
        return TrainingOrderLine.trainingOrderLine(
                new SellableItemId(dto.sellableItemId()),
                new Quantity(dto.quantity()),
                new Money(dto.priceAmount(), dto.currency()));
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
