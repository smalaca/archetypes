package com.smalaca.trainingcenter.trainerscatalogue.domain.trainer;

import java.util.UUID;

public record TrainingContext(UUID topicId, UUID levelId, UserId trainerUserId) {
}
