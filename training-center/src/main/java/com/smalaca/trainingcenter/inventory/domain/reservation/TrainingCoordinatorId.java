package com.smalaca.trainingcenter.inventory.domain.reservation;

import com.smalaca.annotations.archetypes.ArchetypeParty;
import com.smalaca.annotations.architecture.DomainDrivenDesign;
import java.util.UUID;

@DomainDrivenDesign.ValueObject
@ArchetypeParty.PartyRoleIdentifier
public record TrainingCoordinatorId(UUID id) {
}
