package com.smalaca.trainingcenter.trainerscatalogue.domain.trainer;

import com.smalaca.annotations.architecture.PortsAndAdaptersArchitecture.DrivenPort;

import java.util.UUID;

@DrivenPort
public interface CertificationService {
    boolean isCertifiedFor(UserId trainerUserId, UUID topicId, UUID levelId);
}
