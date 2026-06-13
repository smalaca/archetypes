package com.smalaca.trainingcenter.trainingorders.query.trainingorder;

import com.smalaca.annotations.archetypes.ArchetypeOrder;
import com.smalaca.trainingcenter.trainingorders.domain.trainingorder.TrainingOrderId;

import java.util.List;
import java.util.Optional;

@ArchetypeOrder.Order
public interface TrainingOrderReadModelRepository {
    List<TrainingOfferView> findAll();

    Optional<TrainingOfferView> findById(TrainingOrderId id);

    List<TrainingOfferView> findByStatus(String status);
}
