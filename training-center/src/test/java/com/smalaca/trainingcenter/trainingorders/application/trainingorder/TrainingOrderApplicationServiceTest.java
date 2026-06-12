package com.smalaca.trainingcenter.trainingorders.application.trainingorder;

import com.smalaca.trainingcenter.trainingorders.domain.clock.Clock;
import com.smalaca.trainingcenter.trainingorders.domain.trainingorder.TrainingOrder;
import com.smalaca.trainingcenter.trainingorders.domain.trainingorder.TrainingOrderId;
import com.smalaca.trainingcenter.trainingorders.domain.trainingorder.TrainingOrderRepository;
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
        CreateTrainingOrderCommand command = command();

        TrainingOrderId id = service.create(command);

        TrainingOrder actual = thenSavedTrainingOrder();
        assertThat(actual).extracting("trainingOrderId").isEqualTo(id);
        assertThat(actual).extracting("orderedAt").isEqualTo(now);
        assertThat(actual).extracting("participants").asList().hasSize(2);
        assertThat(actual).extracting("orderLines").asList().hasSize(1);
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
