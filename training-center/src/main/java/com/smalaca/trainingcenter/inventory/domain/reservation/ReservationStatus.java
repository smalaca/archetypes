package com.smalaca.trainingcenter.inventory.domain.reservation;

import com.smalaca.annotations.architecture.DomainDrivenDesign;

@DomainDrivenDesign.ValueObject
public enum ReservationStatus {
    PENDING,
    CONFIRMED,
    CANCELLED
}
