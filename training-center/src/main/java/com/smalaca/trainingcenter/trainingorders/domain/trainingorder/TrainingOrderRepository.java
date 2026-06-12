package com.smalaca.trainingcenter.trainingorders.domain.trainingorder;

import com.smalaca.annotations.archetypes.ArchetypeOrder;
import com.smalaca.annotations.architecture.DomainDrivenDesign;
import com.smalaca.annotations.architecture.PortsAndAdaptersArchitecture;

@DomainDrivenDesign.Repository
@PortsAndAdaptersArchitecture.DrivenPort
@ArchetypeOrder.Order
public interface TrainingOrderRepository {
    void save(TrainingOrder trainingOrder);
}
