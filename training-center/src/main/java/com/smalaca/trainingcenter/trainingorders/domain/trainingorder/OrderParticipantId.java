package com.smalaca.trainingcenter.trainingorders.domain.trainingorder;

import com.smalaca.annotations.architecture.DomainDrivenDesign;
import java.util.UUID;

@DomainDrivenDesign.ValueObject
public record OrderParticipantId(UUID id) {
}
