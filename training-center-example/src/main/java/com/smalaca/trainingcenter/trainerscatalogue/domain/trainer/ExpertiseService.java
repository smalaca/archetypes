package com.smalaca.trainingcenter.trainerscatalogue.domain.trainer;

import java.util.UUID;

interface ExpertiseService {
    boolean hasExpertiseIn(UserId trainerUserId, UUID topicId);
}
