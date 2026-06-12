package com.smalaca.trainingcenter.trainingorders.domain.trainingorder;

import com.smalaca.annotations.archetypes.ArchetypeOrder;
import com.smalaca.annotations.architecture.DomainDrivenDesign;

import java.time.LocalDateTime;
import java.util.List;

@DomainDrivenDesign.AggregateRoot
@ArchetypeOrder.Order
@ArchetypeOrder.SalesOrder
public class TrainingOrder {
    private final TrainingOrderId trainingOrderId;
    private final List<OrderParticipant> participants;
    private final List<TrainingOrderLine> orderLines;
    private OrderStatus status;
    private final LocalDateTime orderedAt;

    private TrainingOrder(TrainingOrderId trainingOrderId, List<OrderParticipant> participants, List<TrainingOrderLine> orderLines, OrderStatus status, LocalDateTime orderedAt) {
        this.trainingOrderId = trainingOrderId;
        this.participants = participants;
        this.orderLines = orderLines;
        this.status = status;
        this.orderedAt = orderedAt;
    }

    @DomainDrivenDesign.Factory
    static TrainingOrder created(List<OrderParticipant> participants, List<TrainingOrderLine> orderLines, LocalDateTime orderedAt) {
        return new TrainingOrder(
                TrainingOrderId.trainingOrderId(), List.copyOf(participants), List.copyOf(orderLines),
                OrderStatus.CREATED, orderedAt);
    }

    public TrainingOrderId getTrainingOrderId() {
        return trainingOrderId;
    }
}
