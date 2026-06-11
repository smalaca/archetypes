package com.smalaca.trainingcenter.inventory.domain.reservation;

import com.smalaca.annotations.architecture.DomainDrivenDesign;
import java.util.UUID;

@DomainDrivenDesign.ValueObject
public record ReservationId(UUID id) {
}
