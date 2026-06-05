package com.smalaca.trainingcenter.trainerscatalogue.domain.trainer;

import com.smalaca.annotations.architecture.PortsAndAdaptersArchitecture.DrivenPort;

import java.util.UUID;

@DrivenPort
public interface ExpertiseService {
    boolean hasExpertiseIn(UserId trainerUserId, UUID topicId);
}
