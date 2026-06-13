package com.smalaca.trainingcenter.trainingorders.query.trainingorder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record TrainingOfferView(
        UUID trainingOrderId, List<ParticipantView> participants, List<OrderLineView> orderLines,
        String status, LocalDateTime orderedAt) {

    public record ParticipantView(UUID participantId, String displayName, String role) {
    }

    public record OrderLineView(UUID sellableItemId, BigDecimal quantity, BigDecimal price, String currency) {
    }
}
