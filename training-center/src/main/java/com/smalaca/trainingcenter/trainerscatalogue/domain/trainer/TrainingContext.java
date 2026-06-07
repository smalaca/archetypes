package com.smalaca.trainingcenter.trainerscatalogue.domain.trainer;

import com.smalaca.annotations.architecture.DomainDrivenDesign;
import java.util.UUID;

@DomainDrivenDesign.ValueObject
public record TrainingContext(UUID trainingId, UUID topicId, UUID levelId, UserId trainerUserId) {
}
