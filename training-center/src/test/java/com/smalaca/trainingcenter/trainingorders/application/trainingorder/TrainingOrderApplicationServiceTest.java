package com.smalaca.trainingcenter.trainingorders.application.trainingorder;

import com.smalaca.trainingcenter.trainingorders.domain.clock.Clock;
import com.smalaca.trainingcenter.trainingorders.domain.trainingorder.*;
import com.smalaca.trainingcenter.trainingorders.domain.trainingorder.command.CreateTrainingOrderCommand;
import com.smalaca.trainingcenter.trainingorders.domain.trainingorder.command.OrderParticipantDto;
import com.smalaca.trainingcenter.trainingorders.domain.trainingorder.command.TrainingOrderLineDto;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

class TrainingOrderApplicationServiceTest {
    private final TrainingOrderRepository trainingOrderRepository = mock(TrainingOrderRepository.class);
    private final Clock clock = mock(Clock.class);
    private final TrainingOrderApplicationService service = TrainingOrderApplicationService.create(trainingOrderRepository, clock);

    @Test
    void shouldReturnIdOfCreatedTrainingOrder() {
        given(clock.now()).willReturn(LocalDateTime.now());
        CreateTrainingOrderCommand command = command();

        TrainingOrderId actual = service.create(command);

        assertThat(actual).isNotNull();
        assertThat(actual.id()).isNotNull();
    }

    @Test
    void shouldCreateTrainingOrder() {
        LocalDateTime now = LocalDateTime.now();
        given(clock.now()).willReturn(now);
        UUID sellerId = UUID.randomUUID();
        UUID buyerId = UUID.randomUUID();
        UUID itemId = UUID.randomUUID();
        CreateTrainingOrderCommand command = new CreateTrainingOrderCommand(
                List.of(
                        new OrderParticipantDto(sellerId, "Seller Name", "SELLER"),
                        new OrderParticipantDto(buyerId, "Buyer Name", "BUYER")),
                List.of(
                        new TrainingOrderLineDto(itemId, BigDecimal.valueOf(1), BigDecimal.valueOf(100), "PLN"))
        );

        TrainingOrderId id = service.create(command);

        TrainingOrder actual = thenSavedTrainingOrder();
        assertThat(actual).extracting("trainingOrderId").isEqualTo(id);
        assertThat(actual).extracting("orderedAt").isEqualTo(now);
        assertThat(actual).extracting("status").asString().isEqualTo("CREATED");
        assertThat(actual).extracting("participants").asList()
                .containsExactlyInAnyOrder(
                        new OrderParticipant(new OrderParticipantId(sellerId), "Seller Name", OrderParticipantRole.SELLER),
                        new OrderParticipant(new OrderParticipantId(buyerId), "Buyer Name", OrderParticipantRole.BUYER));
        List<?> orderLines = (List<?>) getFieldValue(actual, "orderLines");
        assertThat(orderLines).hasSize(1);
        Object actualLine = orderLines.get(0);
        assertThat(actualLine).extracting("sellableItemId").isEqualTo(new SellableItemId(itemId));
        assertThat(actualLine).extracting("quantity").isEqualTo(new Quantity(BigDecimal.valueOf(1)));
        assertThat(actualLine).extracting("price").isEqualTo(new Money(BigDecimal.valueOf(100), "PLN"));
        assertThat(actualLine).extracting("trainingOrderLineId").isNotNull();
    }

    private Object getFieldValue(Object object, String fieldName) {
        try {
            java.lang.reflect.Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private CreateTrainingOrderCommand command() {
        return new CreateTrainingOrderCommand(
                List.of(
                        new OrderParticipantDto(UUID.randomUUID(), "Seller Name", "SELLER"),
                        new OrderParticipantDto(UUID.randomUUID(), "Buyer Name", "BUYER")),
                List.of(
                        new TrainingOrderLineDto(UUID.randomUUID(), BigDecimal.valueOf(1), BigDecimal.valueOf(100), "PLN"))
        );
    }

    private TrainingOrder thenSavedTrainingOrder() {
        ArgumentCaptor<TrainingOrder> captor = ArgumentCaptor.forClass(TrainingOrder.class);
        then(trainingOrderRepository).should().save(captor.capture());
        return captor.getValue();
    }
}
