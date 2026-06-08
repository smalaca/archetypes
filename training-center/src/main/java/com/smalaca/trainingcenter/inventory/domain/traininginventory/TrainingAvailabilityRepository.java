package com.smalaca.trainingcenter.inventory.domain.traininginventory;

import com.smalaca.annotations.archetypes.ArchetypeInventory;
import com.smalaca.annotations.architecture.DomainDrivenDesign;
import com.smalaca.annotations.architecture.PortsAndAdaptersArchitecture;

@DomainDrivenDesign.Repository
@PortsAndAdaptersArchitecture.DrivenPort
@ArchetypeInventory.Inventory
public interface TrainingAvailabilityRepository {
    void save(TrainingAvailability trainingAvailability);

    void delete(TrainingAvailabilityId id);
}
