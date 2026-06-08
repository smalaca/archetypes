package com.smalaca.trainingcenter.catalog.application.learningoffer;

import java.util.List;
import java.util.UUID;

public record UpdateLearningOfferCommand(UUID learningOfferId, List<UUID> trainingOfferIds) {
}
