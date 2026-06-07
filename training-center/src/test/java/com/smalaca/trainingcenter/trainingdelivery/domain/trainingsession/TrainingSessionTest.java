package com.smalaca.trainingcenter.trainingdelivery.domain.trainingsession;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class TrainingSessionTest {
    @Test
    void shouldCreateOnsiteTrainingSession() {
        TrainingOfferId offerId = new TrainingOfferId(UUID.randomUUID());
        TrainingDateRange dateRange = new TrainingDateRange(LocalDate.of(2026, 9, 15), LocalDate.of(2026, 9, 17));
        Capacity capacity = new Capacity(20);
        TrainingSessionPrice price = new TrainingSessionPrice(new BigDecimal("2200"), "PLN");
        String city = "Kraków";
        String address = "Main Street 1";

        TrainingSession actual = TrainingSession.onsite(offerId, dateRange, capacity, price, city, address);

        assertThat(actual).extracting("trainingOfferId").isEqualTo(offerId);
        assertThat(actual).extracting("dateRange").isEqualTo(dateRange);
        assertThat(actual).extracting("capacity").isEqualTo(capacity);
        assertThat(actual).extracting("price").isEqualTo(price);
        assertThat(actual).extracting("deliveryMode").isInstanceOf(Onsite.class)
                .hasFieldOrPropertyWithValue("city", city)
                .hasFieldOrPropertyWithValue("address", address);
    }

    @Test
    void shouldCreateOnlineTrainingSession() {
        TrainingOfferId offerId = new TrainingOfferId(UUID.randomUUID());
        TrainingDateRange dateRange = new TrainingDateRange(LocalDate.of(2026, 9, 22), LocalDate.of(2026, 9, 24));
        Capacity capacity = new Capacity(50);
        TrainingSessionPrice price = new TrainingSessionPrice(new BigDecimal("3000"), "PLN");
        Platform platform = Platform.ZOOM;

        TrainingSession actual = TrainingSession.online(offerId, dateRange, capacity, price, platform);

        assertThat(actual).extracting("trainingOfferId").isEqualTo(offerId);
        assertThat(actual).extracting("dateRange").isEqualTo(dateRange);
        assertThat(actual).extracting("capacity").isEqualTo(capacity);
        assertThat(actual).extracting("price").isEqualTo(price);
        assertThat(actual).extracting("deliveryMode").isInstanceOf(Online.class)
                .hasFieldOrPropertyWithValue("platform", platform);
    }
}
