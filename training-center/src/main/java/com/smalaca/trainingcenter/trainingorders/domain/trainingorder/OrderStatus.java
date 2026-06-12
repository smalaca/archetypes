package com.smalaca.trainingcenter.trainingorders.domain.trainingorder;

import com.smalaca.annotations.archetypes.ArchetypeOrder;

@ArchetypeOrder.OrderStatus
enum OrderStatus {
    CREATED,
    CONFIRMED,
    CANCELLED,
    COMPLETED
}
