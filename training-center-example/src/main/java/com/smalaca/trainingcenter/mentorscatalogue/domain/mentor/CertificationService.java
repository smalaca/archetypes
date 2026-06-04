package com.smalaca.trainingcenter.mentorscatalogue.domain.mentor;

import java.util.UUID;

public interface CertificationService {
    boolean isCertified(UserId userId);

    boolean isCertifiedFor(UUID userId, UUID topicId);
}
