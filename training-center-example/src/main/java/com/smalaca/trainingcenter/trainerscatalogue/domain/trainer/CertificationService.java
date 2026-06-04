package com.smalaca.trainingcenter.trainerscatalogue.domain.trainer;

import java.util.UUID;

interface CertificationService {
    boolean isCertifiedFor(UserId trainerUserId, UUID topicId, UUID levelId);
}
