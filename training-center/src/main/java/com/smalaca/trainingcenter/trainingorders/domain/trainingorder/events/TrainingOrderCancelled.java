package com.smalaca.trainingcenter.trainingorders.domain.trainingorder.events;

import com.smalaca.annotations.archetypes.ArchetypeOrder;
import com.smalaca.annotations.architecture.DomainDrivenDesign;

import java.time.LocalDateTime;
import java.util.UUID;

@DomainDrivenDesign.DomainEvent
@ArchetypeOrder.OrderEvent
@ArchetypeOrder.CancelEvent
public record TrainingOrderCancelled(UUID trainingOrderId, LocalDateTime occurredAt) {
}
