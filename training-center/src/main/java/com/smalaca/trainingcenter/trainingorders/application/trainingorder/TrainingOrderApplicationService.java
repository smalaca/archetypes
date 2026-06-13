package com.smalaca.trainingcenter.trainingorders.application.trainingorder;

import com.smalaca.annotations.archetypes.ArchetypeOrder;
import com.smalaca.annotations.architecture.DomainDrivenDesign;
import com.smalaca.annotations.architecture.PortsAndAdaptersArchitecture;
import com.smalaca.trainingcenter.trainingorders.domain.clock.Clock;
import com.smalaca.trainingcenter.trainingorders.domain.eventpublisher.DomainEventPublisher;
import com.smalaca.trainingcenter.trainingorders.domain.trainingorder.OrderCreationResult;
import com.smalaca.trainingcenter.trainingorders.domain.trainingorder.TrainingOrder;
import com.smalaca.trainingcenter.trainingorders.domain.trainingorder.TrainingOrderFactory;
import com.smalaca.trainingcenter.trainingorders.domain.trainingorder.TrainingOrderId;
import com.smalaca.trainingcenter.trainingorders.domain.trainingorder.TrainingOrderRepository;
import com.smalaca.trainingcenter.trainingorders.domain.trainingorder.commands.CreateTrainingOrderCommand;
import com.smalaca.trainingcenter.trainingorders.domain.trainingorder.events.TrainingOrderCancelled;
import com.smalaca.trainingcenter.trainingorders.domain.trainingorder.events.TrainingOrderPaid;

import java.util.UUID;

@DomainDrivenDesign.ApplicationLayer
@ArchetypeOrder.OrderManager
public class TrainingOrderApplicationService {
    private final TrainingOrderRepository trainingOrderRepository;
    private final TrainingOrderFactory factory;
    private final DomainEventPublisher domainEventPublisher;
    private final Clock clock;

    private TrainingOrderApplicationService(TrainingOrderRepository trainingOrderRepository, TrainingOrderFactory factory, DomainEventPublisher domainEventPublisher, Clock clock) {
        this.trainingOrderRepository = trainingOrderRepository;
        this.factory = factory;
        this.domainEventPublisher = domainEventPublisher;
        this.clock = clock;
    }

    public static TrainingOrderApplicationService create(TrainingOrderRepository trainingOrderRepository, DomainEventPublisher domainEventPublisher, Clock clock) {
        TrainingOrderFactory trainingOrderFactory = new TrainingOrderFactory(clock);

        return new TrainingOrderApplicationService(trainingOrderRepository, trainingOrderFactory, domainEventPublisher, clock);
    }

    @PortsAndAdaptersArchitecture.DrivingPort
    public TrainingOrderId create(CreateTrainingOrderCommand command) {
        OrderCreationResult result = factory.create(command.participants(), command.orderLines());

        domainEventPublisher.publish(result.trainingOrderOpened());
        trainingOrderRepository.save(result.trainingOrder());

        return result.trainingOrder().getTrainingOrderId();
    }

    @PortsAndAdaptersArchitecture.DrivingPort
    public void cancel(UUID trainingOrderId) {
        TrainingOrder trainingOrder = trainingOrderRepository.findById(new TrainingOrderId(trainingOrderId));

        TrainingOrderCancelled event = trainingOrder.cancel(clock.now());

        domainEventPublisher.publish(event);
        trainingOrderRepository.save(trainingOrder);
    }

    @PortsAndAdaptersArchitecture.DrivingPort
    public void pay(UUID trainingOrderId) {
        TrainingOrder trainingOrder = trainingOrderRepository.findById(new TrainingOrderId(trainingOrderId));

        TrainingOrderPaid event = trainingOrder.pay(clock.now());

        domainEventPublisher.publish(event);
        trainingOrderRepository.save(trainingOrder);
    }
}
