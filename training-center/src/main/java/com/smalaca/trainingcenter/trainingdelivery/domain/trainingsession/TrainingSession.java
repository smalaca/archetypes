package com.smalaca.trainingcenter.trainingdelivery.domain.trainingsession;

import com.smalaca.annotations.archetypes.ArchetypeProduct;
import com.smalaca.annotations.architecture.DomainDrivenDesign;

import java.util.UUID;

@DomainDrivenDesign.AggregateRoot
@ArchetypeProduct.ProductInstance
public class TrainingSession {
    private final TrainingSessionId trainingSessionId;
    private final TrainingOfferId trainingOfferId;
    private final TrainingDateRange dateRange;
    private final DeliveryMode deliveryMode;
    private final Capacity capacity;
    private final TrainingSessionPrice price;

    private TrainingSession(
            TrainingSessionId trainingSessionId, TrainingOfferId trainingOfferId, TrainingDateRange dateRange,
            DeliveryMode deliveryMode, Capacity capacity, TrainingSessionPrice price) {
        this.trainingSessionId = trainingSessionId;
        this.trainingOfferId = trainingOfferId;
        this.dateRange = dateRange;
        this.deliveryMode = deliveryMode;
        this.capacity = capacity;
        this.price = price;
    }

    @DomainDrivenDesign.Factory
    public static TrainingSession onsite(
            TrainingOfferId trainingOfferId, TrainingDateRange dateRange, Capacity capacity, TrainingSessionPrice price,
            String city, String address) {
        return new TrainingSession(
                new TrainingSessionId(UUID.randomUUID()),
                trainingOfferId,
                dateRange,
                new Onsite(city, address),
                capacity,
                price
        );
    }

    @DomainDrivenDesign.Factory
    public static TrainingSession online(
            TrainingOfferId trainingOfferId, TrainingDateRange dateRange, Capacity capacity, TrainingSessionPrice price,
            Platform platform) {
        return new TrainingSession(
                new TrainingSessionId(UUID.randomUUID()),
                trainingOfferId,
                dateRange,
                new Online(platform),
                capacity,
                price
        );
    }
}
