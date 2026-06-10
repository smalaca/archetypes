package com.smalaca.trainingcenter.trainingdelivery.domain.trainingsession;

import com.smalaca.annotations.archetypes.ArchetypeProduct;
import com.smalaca.annotations.architecture.DomainDrivenDesign;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

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

    private TrainingSession(Builder builder) {
        this.trainingSessionId = builder.trainingSessionId;
        this.trainingOfferId = builder.trainingOfferId;
        this.dateRange = builder.dateRange;
        this.deliveryMode = builder.deliveryMode;
        this.skillLevel = builder.skillLevel;
        this.language = builder.language;
        this.capacity = builder.capacity;
        this.price = builder.price;
    }

    @DomainDrivenDesign.Factory
    public static class Builder {
        private TrainingSessionId trainingSessionId;
        private TrainingOfferId trainingOfferId;
        private TrainingDateRange dateRange;
        private DeliveryMode deliveryMode;
        private SkillLevel skillLevel;
        private Language language;
        private Capacity capacity;
        private TrainingSessionPrice price;

        public Builder trainingOfferId(UUID trainingOfferId) {
            this.trainingOfferId = new TrainingOfferId(trainingOfferId);
            return this;
        }

        public Builder dateRange(LocalDate startDate, LocalDate endDate) {
            this.dateRange = new TrainingDateRange(startDate, endDate);
            return this;
        }

        public Builder online() {
            this.deliveryMode = DeliveryMode.ONLINE;
            return this;
        }

        public Builder onsite() {
            this.deliveryMode = DeliveryMode.ONSITE;
            return this;
        }

        public Builder hybrid() {
            this.deliveryMode = DeliveryMode.HYBRID;
            return this;
        }

        public Builder beginner() {
            this.skillLevel = SkillLevel.BEGINNER;
            return this;
        }

        public Builder intermediate() {
            this.skillLevel = SkillLevel.INTERMEDIATE;
            return this;
        }

        public Builder advanced() {
            this.skillLevel = SkillLevel.ADVANCED;
            return this;
        }

        public Builder expert() {
            this.skillLevel = SkillLevel.EXPERT;
            return this;
        }

        public Builder language(String language) {
            this.language = new Language(language);
            return this;
        }

        public Builder capacity(int capacity) {
            this.capacity = new Capacity(capacity);
            return this;
        }

        public Builder price(BigDecimal amount, String currency) {
            this.price = new TrainingSessionPrice(amount, currency);
            return this;
        }

        public TrainingSession build() {
            trainingSessionId = new TrainingSessionId(UUID.randomUUID());
            return new TrainingSession(this);
        }
    }
}
