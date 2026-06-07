package com.smalaca.trainingcenter.mentorscatalogue.domain.mentor;

import java.util.List;
import java.util.UUID;

public record MentoringContext(List<UUID> menteeIds, UUID mentorId) {
}
