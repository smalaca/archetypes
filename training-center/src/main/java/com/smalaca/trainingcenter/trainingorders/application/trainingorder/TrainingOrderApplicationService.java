package com.smalaca.trainingcenter.trainingorders.application.trainingorder;

import com.smalaca.annotations.archetypes.ArchetypeOrder;
import com.smalaca.annotations.architecture.DomainDrivenDesign;
import com.smalaca.annotations.architecture.PortsAndAdaptersArchitecture;
import com.smalaca.trainingcenter.trainingorders.domain.clock.Clock;
import com.smalaca.trainingcenter.trainingorders.domain.trainingorder.*;

import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@DomainDrivenDesign.ApplicationLayer
@ArchetypeOrder.Order
public class TrainingOrderApplicationService {
    private final TrainingOrderRepository trainingOrderRepository;
    private final TrainingOrderFactory factory;

    private TrainingOrderApplicationService(TrainingOrderRepository trainingOrderRepository, TrainingOrderFactory factory) {
        this.trainingOrderRepository = trainingOrderRepository;
        this.factory = factory;
    }

    public static TrainingOrderApplicationService create(TrainingOrderRepository trainingOrderRepository, Clock clock) {
        TrainingOrderFactory trainingOrderFactory = new TrainingOrderFactory(clock);

        return new TrainingOrderApplicationService(trainingOrderRepository, trainingOrderFactory);
    }

    @PortsAndAdaptersArchitecture.DrivingPort
    public TrainingOrderId create(CreateTrainingOrderCommand command) {
        List<OrderParticipant> participants = command.participants().stream()
                .map(this::orderParticipant)
                .collect(toList());
        List<TrainingOrderLine> orderLines = command.orderLines().stream()
                .map(this::trainingOrderLine)
                .collect(toList());

        TrainingOrder trainingOrder = factory.create(participants, orderLines);

        trainingOrderRepository.save(trainingOrder);
        return trainingOrder.getTrainingOrderId();
    }

    private OrderParticipant orderParticipant(OrderParticipantDto dto) {
        return new OrderParticipant(
                new OrderParticipantId(dto.participantId()),
                dto.displayName(),
                OrderParticipantRole.valueOf(dto.role()));
    }

    private TrainingOrderLine trainingOrderLine(TrainingOrderLineDto dto) {
        return new TrainingOrderLine(
                new TrainingOrderLineId(UUID.randomUUID()),
                new SellableItemId(dto.sellableItemId()),
                new Quantity(dto.quantity()),
                new Money(dto.priceAmount(), dto.currency()));
    }
}
