package com.smalaca.trainingcenter.trainingportfolio.domain.trainingoffer;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static com.smalaca.trainingcenter.trainingportfolio.domain.trainingoffer.DeliveryMode.ONLINE;
import static com.smalaca.trainingcenter.trainingportfolio.domain.trainingoffer.DeliveryMode.ONSITE;
import static com.smalaca.trainingcenter.trainingportfolio.domain.trainingoffer.PriceType.*;
import static com.smalaca.trainingcenter.trainingportfolio.domain.trainingoffer.SkillLevel.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TrainingOfferTest {
    @Test
    void shouldCreateTrainingOffer() {
        TrainingOfferId id = new TrainingOfferId(UUID.randomUUID());
        TrainingOfferName name = new TrainingOfferName("DDD Training");
        Set<DeliveryMode> deliveryModes = Set.of(ONLINE, ONSITE);
        Set<SkillLevel> skillLevels = Set.of(BEGINNER, INTERMEDIATE, ADVANCED);
        Set<Language> languages = Set.of(new Language("English"), new Language("Polish"));

        TrainingOffer actual = new TrainingOffer(id, name, deliveryModes, skillLevels, languages);

        assertThat(actual).extracting("trainingOfferId").isEqualTo(id);
        assertThat(actual).extracting("name").isEqualTo(name);
        assertThat(actual).extracting("supportedDeliveryModes").isEqualTo(deliveryModes);
        assertThat(actual).extracting("supportedSkillLevels").isEqualTo(skillLevels);
        assertThat(actual).extracting("supportedLanguages").isEqualTo(languages);
    }

    @Test
    void shouldCreateTrainingOfferWithOnePrice() {
        TrainingOffer offer = givenTrainingOffer();
        TrainingPrice price = new TrainingPrice(STANDARD, new BigDecimal("2500"), "PLN");

        offer.add(price);

        assertThat(offer).extracting("prices").asList().containsExactly(price);
    }

    @Test
    void shouldCreateTrainingOfferWithMultiplePrices() {
        TrainingOffer offer = givenTrainingOffer();
        TrainingPrice standardPrice = new TrainingPrice(STANDARD, new BigDecimal("2500"), "PLN");
        TrainingPrice earlyBirdPrice = new TrainingPrice(EARLY_BIRD, new BigDecimal("2000"), "PLN");
        TrainingPrice corporatePrice = new TrainingPrice(CORPORATE, new BigDecimal("3000"), "PLN");

        offer.add(standardPrice);
        offer.add(earlyBirdPrice);
        offer.add(corporatePrice);

        assertThat(offer).extracting("prices").asList().containsExactlyInAnyOrder(standardPrice, earlyBirdPrice, corporatePrice);
    }

    @Test
    void shouldNotAllowMultiplePricesOfTheSameType() {
        TrainingOffer offer = givenTrainingOffer();
        offer.add(new TrainingPrice(STANDARD, new BigDecimal("2500"), "PLN"));
        offer.add(new TrainingPrice(EARLY_BIRD, new BigDecimal("2000"), "PLN"));
        offer.add(new TrainingPrice(CORPORATE, new BigDecimal("3000"), "PLN"));
        TrainingPrice alsoStandardPrice = new TrainingPrice(STANDARD, new BigDecimal("2600"), "PLN");

        RuntimeException exception = assertThrows(RuntimeException.class, () -> offer.add(alsoStandardPrice));

        assertThat(exception.getMessage()).isEqualTo("Price type already exists: STANDARD");
    }

    @Test
    void shouldCreateTrainingOfferWithMultipleFeatures() {
        TrainingOffer offer = givenTrainingOffer();
        TrainingFeature deliveryMethod = new TrainingFeature("Delivery Method", "The way of delivery", List.of("Onsite", "Online"));
        TrainingFeature trainingSpokenLanguage = new TrainingFeature("Spoken Language", "Language of training", List.of("English", "Polish"));

        offer.add(deliveryMethod);
        offer.add(trainingSpokenLanguage);

        assertThat(offer).extracting("features").asList().containsExactlyInAnyOrder(deliveryMethod, trainingSpokenLanguage);
    }

    @Test
    void shouldNotAllowMultipleFeaturesWithTheSameName() {
        TrainingOffer offer = givenTrainingOffer();
        offer.add(new TrainingFeature("Delivery Method", "The way of delivery", List.of("Onsite", "Online")));
        TrainingFeature alsoDeliveryMethod = new TrainingFeature("Delivery Method", "The way of delivery", List.of("Onsite", "Online"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> offer.add(alsoDeliveryMethod));

        assertThat(exception.getMessage()).isEqualTo("Feature name already exists: Delivery Method");
    }

    private TrainingOffer givenTrainingOffer() {
        TrainingOfferId trainingOfferId = new TrainingOfferId(UUID.randomUUID());
        TrainingOfferName trainingOfferName = new TrainingOfferName("Any Training");

        return new TrainingOffer(trainingOfferId, trainingOfferName, Set.of(ONLINE), Set.of(BEGINNER), Set.of(new Language("English")));
    }
}
