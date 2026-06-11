package com.smalaca.trainingcenter.inventory.domain.traininginventory;

import com.smalaca.annotations.architecture.DomainDrivenDesign;

@DomainDrivenDesign.ValueObject
public record ReservedSeats(int value) {
    @DomainDrivenDesign.Factory
    public ReservedSeats increment() {
        return new ReservedSeats(value + 1);
    }
}
