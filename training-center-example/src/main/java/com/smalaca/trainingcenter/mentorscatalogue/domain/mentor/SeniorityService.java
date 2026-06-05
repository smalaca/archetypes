package com.smalaca.trainingcenter.mentorscatalogue.domain.mentor;

import com.smalaca.annotations.architecture.PortsAndAdaptersArchitecture.DrivenPort;

@DrivenPort
public interface SeniorityService {
    boolean hasEnoughExperience(UserId userId);
}
