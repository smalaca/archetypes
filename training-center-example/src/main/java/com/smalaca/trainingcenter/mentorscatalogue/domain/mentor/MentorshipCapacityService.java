package com.smalaca.trainingcenter.mentorscatalogue.domain.mentor;

import java.util.UUID;

public interface MentorshipCapacityService {
    boolean hasCapacity(UUID mentorUserId);
}
