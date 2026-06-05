package com.smalaca.trainingcenter.mentorscatalogue.domain.mentor;

import com.smalaca.annotations.architecture.PortsAndAdaptersArchitecture.DrivenPort;

import java.util.UUID;

@DrivenPort
public interface MenteeService {
    boolean canBeMentored(UUID menteeId);
}
