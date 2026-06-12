package com.smalaca.trainingcenter.trainingorders.application.trainingorder;

import com.smalaca.trainingcenter.trainingorders.domain.clock.Clock;
import com.smalaca.trainingcenter.trainingorders.domain.trainingorder.TrainingOrder;
import com.smalaca.trainingcenter.trainingorders.domain.trainingorder.TrainingOrderAssertion;
import com.smalaca.trainingcenter.trainingorders.domain.trainingorder.TrainingOrderId;
import com.smalaca.trainingcenter.trainingorders.domain.trainingorder.TrainingOrderRepository;
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

        thenSavedTrainingOrder()
                .hasTrainingOrderIdEqualTo(id)
                .hasOrderedAtEqualTo(now)
                .isCreated()
                .hasSeller(sellerId, "Seller Name")
                .hasBuyer(buyerId, "Buyer Name")
                .hasOrderLines(1)
                .hasOrderLine(0, itemId, BigDecimal.valueOf(1), BigDecimal.valueOf(100), "PLN");
    }

    private TrainingOrderAssertion thenSavedTrainingOrder() {
        ArgumentCaptor<TrainingOrder> captor = ArgumentCaptor.forClass(TrainingOrder.class);
        then(trainingOrderRepository).should().save(captor.capture());
        return TrainingOrderAssertion.assertThat(captor.getValue());
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

}
