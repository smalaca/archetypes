package com.smalaca.trainingcenter.mentorscatalogue.domain.mentor;

import java.util.UUID;

public record MentoringContext(UUID menteeId, UUID topicId, UUID mentorId) {
}
