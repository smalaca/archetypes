package com.smalaca.trainingcenter.trainingdelivery.domain.trainingsession;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static com.smalaca.trainingcenter.trainingdelivery.domain.trainingsession.DeliveryMode.*;
import static com.smalaca.trainingcenter.trainingdelivery.domain.trainingsession.SkillLevel.*;
import static org.assertj.core.api.Assertions.assertThat;

class TrainingSessionTest {
    @Test
    void shouldCreateTrainingSession() {
        UUID offerId = UUID.randomUUID();
        LocalDate startDate = LocalDate.of(2026, 4, 1);
        LocalDate endDate = LocalDate.of(2026, 4, 3);
        BigDecimal amount = new BigDecimal("2500");
        String currency = "PLN";

        TrainingSession actual = new TrainingSession.Builder()
                .trainingOfferId(offerId)
                .dateRange(startDate, endDate)
                .onsite()
                .beginner()
                .language("Polish")
                .capacity(20)
                .price(amount, currency)
                .build();

        assertThat(actual).extracting("trainingSessionId").isNotNull();
        assertThat(actual).extracting("trainingOfferId").isEqualTo(new TrainingOfferId(offerId));
        assertThat(actual).extracting("dateRange").isEqualTo(new TrainingDateRange(startDate, endDate));
        assertThat(actual).extracting("deliveryMode").isEqualTo(ONSITE);
        assertThat(actual).extracting("skillLevel").isEqualTo(BEGINNER);
        assertThat(actual).extracting("language").isEqualTo(new Language("Polish"));
        assertThat(actual).extracting("capacity").isEqualTo(new Capacity(20));
        assertThat(actual).extracting("price").isEqualTo(new TrainingSessionPrice(amount, currency));
    }

    @ParameterizedTest
    @EnumSource(DeliveryMode.class)
    void shouldCreateTrainingSessionWithDeliveryMode(DeliveryMode deliveryMode) {
        TrainingSession.Builder builder = new TrainingSession.Builder();
        setDeliveryMode(builder, deliveryMode);

        TrainingSession actual = builder.beginner().build();

        assertThat(actual).extracting("deliveryMode").isEqualTo(deliveryMode);
    }

    private void setDeliveryMode(TrainingSession.Builder builder, DeliveryMode deliveryMode) {
        switch (deliveryMode) {
            case ONLINE -> builder.online();
            case ONSITE -> builder.onsite();
            case HYBRID -> builder.hybrid();
        }
    }

    @ParameterizedTest
    @EnumSource(SkillLevel.class)
    void shouldCreateTrainingSessionWithSkillLevel(SkillLevel skillLevel) {
        TrainingSession.Builder builder = new TrainingSession.Builder();
        setSkillLevel(builder, skillLevel);

        TrainingSession actual = builder.online().build();

        assertThat(actual).extracting("skillLevel").isEqualTo(skillLevel);
    }

    private void setSkillLevel(TrainingSession.Builder builder, SkillLevel skillLevel) {
        switch (skillLevel) {
            case BEGINNER -> builder.beginner();
            case INTERMEDIATE -> builder.intermediate();
            case ADVANCED -> builder.advanced();
            case EXPERT -> builder.expert();
        }
    }
}
