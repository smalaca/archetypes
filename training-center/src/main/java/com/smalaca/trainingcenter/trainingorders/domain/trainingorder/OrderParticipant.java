package com.smalaca.trainingcenter.trainingorders.domain.trainingorder;

import com.smalaca.annotations.archetypes.ArchetypeOrder;
import com.smalaca.annotations.architecture.DomainDrivenDesign;

@DomainDrivenDesign.ValueObject
@ArchetypeOrder.PartySummary
public record OrderParticipant(OrderParticipantId orderParticipantId, String displayName, OrderParticipantRole role) {
    boolean isSeller() {
        return OrderParticipantRole.SELLER.equals(role);
    }

    boolean isBuyer() {
        return OrderParticipantRole.BUYER.equals(role);
    }
}
