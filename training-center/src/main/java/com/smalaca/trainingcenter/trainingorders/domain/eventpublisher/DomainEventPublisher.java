package com.smalaca.trainingcenter.trainingorders.domain.eventpublisher;

import com.smalaca.annotations.architecture.PortsAndAdaptersArchitecture;
import com.smalaca.trainingcenter.trainingorders.domain.trainingorder.events.TrainingOrderCancelled;
import com.smalaca.trainingcenter.trainingorders.domain.trainingorder.events.TrainingOrderOpened;
import com.smalaca.trainingcenter.trainingorders.domain.trainingorder.events.TrainingOrderPaid;

@PortsAndAdaptersArchitecture.DrivenPort
public interface DomainEventPublisher {
    void publish(TrainingOrderOpened trainingOrderOpened);

    void publish(TrainingOrderCancelled trainingOrderCancelled);

    void publish(TrainingOrderPaid trainingOrderPaid);
}
