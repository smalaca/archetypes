package com.smalaca.trainingcenter.trainingorders.domain.trainingorder;

import com.smalaca.annotations.archetypes.ArchetypeOrder;
import com.smalaca.annotations.architecture.DomainDrivenDesign;

@DomainDrivenDesign.Entity
@ArchetypeOrder.OrderLine
public class TrainingOrderLine {
    private final TrainingOrderLineId trainingOrderLineId;
    private final SellableItemId sellableItemId;
    private final Quantity quantity;
    private final Money price;

    public TrainingOrderLine(TrainingOrderLineId trainingOrderLineId, SellableItemId sellableItemId, Quantity quantity, Money price) {
        this.trainingOrderLineId = trainingOrderLineId;
        this.sellableItemId = sellableItemId;
        this.quantity = quantity;
        this.price = price;
    }
}
