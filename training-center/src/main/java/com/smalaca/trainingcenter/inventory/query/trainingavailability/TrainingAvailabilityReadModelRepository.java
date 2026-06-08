package com.smalaca.trainingcenter.inventory.query.trainingavailability;

import com.smalaca.annotations.archetypes.ArchetypeInventory;
import com.smalaca.trainingcenter.inventory.domain.traininginventory.TrainingAvailabilityId;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ArchetypeInventory.Inventory
public interface TrainingAvailabilityReadModelRepository {
    List<TrainingAvailabilityView> findAll();

    Optional<TrainingAvailabilityView> findById(TrainingAvailabilityId id);

    List<TrainingAvailabilityView> findByTrainingSessionId(UUID trainingSessionId);
}
