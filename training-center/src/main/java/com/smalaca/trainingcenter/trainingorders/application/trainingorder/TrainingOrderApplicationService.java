package com.smalaca.trainingcenter.trainingorders.application.trainingorder;

import com.smalaca.annotations.archetypes.ArchetypeOrder;
import com.smalaca.annotations.architecture.DomainDrivenDesign;
import com.smalaca.annotations.architecture.PortsAndAdaptersArchitecture;
import com.smalaca.trainingcenter.trainingorders.domain.clock.Clock;
import com.smalaca.trainingcenter.trainingorders.domain.trainingorder.TrainingOrder;
import com.smalaca.trainingcenter.trainingorders.domain.trainingorder.TrainingOrderFactory;
import com.smalaca.trainingcenter.trainingorders.domain.trainingorder.TrainingOrderId;
import com.smalaca.trainingcenter.trainingorders.domain.trainingorder.TrainingOrderRepository;
import com.smalaca.trainingcenter.trainingorders.domain.trainingorder.command.CreateTrainingOrderCommand;

@DomainDrivenDesign.ApplicationLayer
@ArchetypeOrder.OrderManager
public class TrainingOrderApplicationService {
    private final TrainingOrderRepository trainingOrderRepository;
    private final TrainingOrderFactory factory;

    private TrainingOrderApplicationService(TrainingOrderRepository trainingOrderRepository, TrainingOrderFactory factory) {
        this.trainingOrderRepository = trainingOrderRepository;
        this.factory = factory;
    }

    public static TrainingOrderApplicationService create(TrainingOrderRepository trainingOrderRepository, Clock clock) {
        TrainingOrderFactory trainingOrderFactory = new TrainingOrderFactory(clock);

        return new TrainingOrderApplicationService(trainingOrderRepository, trainingOrderFactory);
    }

    @PortsAndAdaptersArchitecture.DrivingPort
    public TrainingOrderId create(CreateTrainingOrderCommand command) {
        TrainingOrder trainingOrder = factory.create(command.participants(), command.orderLines());

        trainingOrderRepository.save(trainingOrder);
        return trainingOrder.getTrainingOrderId();
    }
}
