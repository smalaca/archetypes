package com.smalaca.trainingcenter.catalog.query.learningoffer;

import java.util.List;
import java.util.UUID;

public record LearningOfferView(UUID learningOfferId, String title, String description, List<String> categories, List<UUID> trainingOfferIds) {
}
