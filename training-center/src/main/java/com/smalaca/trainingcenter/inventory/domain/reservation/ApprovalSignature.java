package com.smalaca.trainingcenter.inventory.domain.reservation;

import com.smalaca.annotations.archetypes.ArchetypeRule;
import com.smalaca.annotations.architecture.DomainDrivenDesign;

import java.time.LocalDateTime;

@DomainDrivenDesign.ValueObject
@ArchetypeRule.PartySignature
public record ApprovalSignature(TrainingCoordinatorId trainingCoordinatorId, LocalDateTime approvedAt) {
}
