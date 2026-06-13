package com.smalaca.trainingcenter.trainingorders.application.trainingorder;

import com.smalaca.trainingcenter.trainingorders.domain.clock.Clock;
import com.smalaca.trainingcenter.trainingorders.domain.eventpublisher.DomainEventPublisher;
import com.smalaca.trainingcenter.trainingorders.domain.trainingorder.TrainingOrder;
import com.smalaca.trainingcenter.trainingorders.domain.trainingorder.TrainingOrderAssertion;
import com.smalaca.trainingcenter.trainingorders.domain.trainingorder.TrainingOrderFactory;
import com.smalaca.trainingcenter.trainingorders.domain.trainingorder.TrainingOrderId;
import com.smalaca.trainingcenter.trainingorders.domain.trainingorder.TrainingOrderRepository;
import com.smalaca.trainingcenter.trainingorders.domain.trainingorder.commands.CreateTrainingOrderCommand;
import com.smalaca.trainingcenter.trainingorders.domain.trainingorder.commands.OrderParticipantDto;
import com.smalaca.trainingcenter.trainingorders.domain.trainingorder.commands.TrainingOrderLineDto;
import com.smalaca.trainingcenter.trainingorders.domain.trainingorder.events.TrainingOrderCancelled;
import com.smalaca.trainingcenter.trainingorders.domain.trainingorder.events.TrainingOrderOpened;
import com.smalaca.trainingcenter.trainingorders.domain.trainingorder.events.TrainingOrderPaid;
import org.junit.jupiter.api.BeforeEach;
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
    private static final LocalDateTime NOW = LocalDateTime.now();

    private final TrainingOrderRepository trainingOrderRepository = mock(TrainingOrderRepository.class);
    private final DomainEventPublisher domainEventPublisher = mock(DomainEventPublisher.class);
    private final Clock clock = mock(Clock.class);
    private final TrainingOrderApplicationService service = TrainingOrderApplicationService.create(trainingOrderRepository, domainEventPublisher, clock);

    @BeforeEach
    void givenNow() {
        given(clock.now()).willReturn(NOW);
    }

    @Test
    void shouldReturnIdOfCreatedTrainingOrder() {
        TrainingOrderId actual = service.create(command());

        assertThat(actual).isNotNull();
        assertThat(actual.id()).isNotNull();
    }

    @Test
    void shouldCreateTrainingOrder() {
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
                .hasOrderedAtEqualTo(NOW)
                .isCreated()
                .hasSeller(sellerId, "Seller Name")
                .hasBuyer(buyerId, "Buyer Name")
                .hasOrderLines(1)
                .hasOrderLine(0, itemId, BigDecimal.valueOf(1), BigDecimal.valueOf(100), "PLN");
    }

    @Test
    void shouldPublishTrainingOrderOpened() {
        TrainingOrderId id = service.create(command());

        then(domainEventPublisher).should().publish(new TrainingOrderOpened(id.id(), NOW));
    }

    @Test
    void shouldCancelTrainingOrder() {
        UUID trainingOrderId = existingTrainingOrder().getTrainingOrderId().id();

        service.cancel(trainingOrderId);

        thenSavedTrainingOrder().isCancelled();
    }

    @Test
    void shouldPublishTrainingOrderCancelled() {
        UUID trainingOrderId = existingTrainingOrder().getTrainingOrderId().id();

        service.cancel(trainingOrderId);

        then(domainEventPublisher).should().publish(new TrainingOrderCancelled(trainingOrderId, NOW));
    }

    @Test
    void shouldPayTrainingOrder() {
        UUID trainingOrderId = existingTrainingOrder().getTrainingOrderId().id();

        service.pay(trainingOrderId);

        thenSavedTrainingOrder().isPaid();
    }

    @Test
    void shouldPublishTrainingOrderPaid() {
        UUID trainingOrderId = existingTrainingOrder().getTrainingOrderId().id();

        service.pay(trainingOrderId);

        then(domainEventPublisher).should().publish(new TrainingOrderPaid(trainingOrderId, NOW));
    }

    private TrainingOrder existingTrainingOrder() {
        TrainingOrderFactory factory = new TrainingOrderFactory(clock);
        TrainingOrder trainingOrder = factory.create(command().participants(), command().orderLines()).trainingOrder();
        given(trainingOrderRepository.findById(trainingOrder.getTrainingOrderId())).willReturn(trainingOrder);

        return trainingOrder;
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
