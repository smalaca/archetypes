package com.smalaca.trainingcenter.trainingdelivery.domain.trainingsession;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static com.smalaca.trainingcenter.trainingdelivery.domain.trainingsession.DeliveryMode.*;
import static com.smalaca.trainingcenter.trainingdelivery.domain.trainingsession.SkillLevel.*;
import static org.assertj.core.api.Assertions.assertThat;

class TrainingSessionTest {
    @Test
    void shouldCreateTrainingSession() {
        TrainingSessionId trainingSessionId = new TrainingSessionId(UUID.randomUUID());
        TrainingOfferId offerId = new TrainingOfferId(UUID.randomUUID());
        TrainingDateRange dateRange = new TrainingDateRange(LocalDate.of(2026, 4, 1), LocalDate.of(2026, 4, 3));
        Language language = new Language("Polish");
        Capacity capacity = new Capacity(20);
        TrainingSessionPrice price = new TrainingSessionPrice(new BigDecimal("2500"), "PLN");

        TrainingSession actual = new TrainingSession(trainingSessionId, offerId, dateRange, ONSITE, BEGINNER, language, capacity, price);

        assertThat(actual).extracting("trainingSessionId").isEqualTo(trainingSessionId);
        assertThat(actual).extracting("trainingOfferId").isEqualTo(offerId);
        assertThat(actual).extracting("dateRange").isEqualTo(dateRange);
        assertThat(actual).extracting("deliveryMode").isEqualTo(ONSITE);
        assertThat(actual).extracting("skillLevel").isEqualTo(BEGINNER);
        assertThat(actual).extracting("language").isEqualTo(language);
        assertThat(actual).extracting("capacity").isEqualTo(capacity);
        assertThat(actual).extracting("price").isEqualTo(price);
    }
}
