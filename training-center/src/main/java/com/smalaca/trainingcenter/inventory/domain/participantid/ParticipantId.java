package com.smalaca.trainingcenter.inventory.domain.participantid;

import com.smalaca.annotations.architecture.DomainDrivenDesign;
import java.util.UUID;

@DomainDrivenDesign.ValueObject
public record ParticipantId(UUID id) {
}
