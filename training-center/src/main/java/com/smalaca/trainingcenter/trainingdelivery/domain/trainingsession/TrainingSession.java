package com.smalaca.trainingcenter.trainingdelivery.domain.trainingsession;

import com.smalaca.annotations.archetypes.ArchetypeProduct;
import com.smalaca.annotations.architecture.DomainDrivenDesign;

@DomainDrivenDesign.AggregateRoot
@ArchetypeProduct.ProductInstance
public class TrainingSession {
    private final TrainingSessionId trainingSessionId;
    private final TrainingOfferId trainingOfferId;
    private final TrainingDateRange dateRange;
    private final DeliveryMode deliveryMode;
    private final SkillLevel skillLevel;
    private final Language language;
    private final Capacity capacity;
    private final TrainingSessionPrice price;

    public TrainingSession(
            TrainingSessionId trainingSessionId, TrainingOfferId trainingOfferId, TrainingDateRange dateRange,
            DeliveryMode deliveryMode, SkillLevel skillLevel, Language language, Capacity capacity, TrainingSessionPrice price) {
        this.trainingSessionId = trainingSessionId;
        this.trainingOfferId = trainingOfferId;
        this.dateRange = dateRange;
        this.deliveryMode = deliveryMode;
        this.skillLevel = skillLevel;
        this.language = language;
        this.capacity = capacity;
        this.price = price;
    }
}
