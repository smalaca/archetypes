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
}
