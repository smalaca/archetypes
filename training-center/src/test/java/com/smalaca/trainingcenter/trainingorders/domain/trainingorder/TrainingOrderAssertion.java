package com.smalaca.trainingcenter.trainingorders.domain.trainingorder;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class TrainingOrderAssertion extends AbstractAssert<TrainingOrderAssertion, TrainingOrder> {
    private TrainingOrderAssertion(TrainingOrder actual) {
        super(actual, TrainingOrderAssertion.class);
    }

    public static TrainingOrderAssertion assertThat(TrainingOrder actual) {
        return new TrainingOrderAssertion(actual);
    }

    public TrainingOrderAssertion hasTrainingOrderIdEqualTo(TrainingOrderId expected) {
        Assertions.assertThat(actual).extracting("trainingOrderId").isEqualTo(expected);
        return this;
    }

    public TrainingOrderAssertion hasOrderedAtEqualTo(LocalDateTime expected) {
        Assertions.assertThat(actual).extracting("orderedAt").isEqualTo(expected);
        return this;
    }

    public TrainingOrderAssertion isCreated() {
        Assertions.assertThat(actual).extracting("status").isEqualTo(OrderStatus.CREATED);
        return this;
    }

    public TrainingOrderAssertion isCancelled() {
        Assertions.assertThat(actual).extracting("status").isEqualTo(OrderStatus.CANCELLED);
        return this;
    }

    public TrainingOrderAssertion isPaid() {
        Assertions.assertThat(actual).extracting("status").isEqualTo(OrderStatus.PAID);
        return this;
    }

    public TrainingOrderAssertion hasSeller(UUID participantId, String displayName) {
        return hasParticipant(participantId, displayName, OrderParticipantRole.SELLER);
    }

    public TrainingOrderAssertion hasBuyer(UUID participantId, String displayName) {
        return hasParticipant(participantId, displayName, OrderParticipantRole.BUYER);
    }

    private TrainingOrderAssertion hasParticipant(UUID participantId, String displayName, OrderParticipantRole role) {
        OrderParticipant expected = new OrderParticipant(new OrderParticipantId(participantId), displayName, role);
        Assertions.assertThat(actual).extracting("participants").asList().contains(expected);
        return this;
    }

    public TrainingOrderAssertion hasOrderLines(int size) {
        Assertions.assertThat(actual).extracting("orderLines").asList().hasSize(size);
        return this;
    }

    public TrainingOrderAssertion hasOrderLine(int index, UUID sellableItemId, BigDecimal quantity, BigDecimal price, String currency) {
        List<TrainingOrderLine> orderLines = (List<TrainingOrderLine>) getFieldValue("orderLines");
        TrainingOrderLine actualLine = orderLines.get(index);
        
        Assertions.assertThat(actualLine).extracting("sellableItemId").isEqualTo(new SellableItemId(sellableItemId));
        Assertions.assertThat(actualLine).extracting("quantity").isEqualTo(new Quantity(quantity));
        Assertions.assertThat(actualLine).extracting("price").isEqualTo(new Money(price, currency));
        Assertions.assertThat(actualLine).extracting("trainingOrderLineId").isNotNull();
        
        return this;
    }

    private Object getFieldValue(String fieldName) {
        try {
            java.lang.reflect.Field field = actual.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(actual);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
