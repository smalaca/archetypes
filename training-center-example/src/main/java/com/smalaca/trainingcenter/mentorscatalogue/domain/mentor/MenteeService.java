package com.smalaca.trainingcenter.mentorscatalogue.domain.mentor;

import java.util.UUID;

public interface MenteeService {
    boolean canBeMentored(UUID menteeId);
}
