package com.smalaca.trainingcenter.trainerscatalogue.domain.trainer;

import com.smalaca.annotations.architecture.PortsAndAdaptersArchitecture.DrivenPort;

@DrivenPort
public interface WorkloadService {
    boolean hasCapacity(UserId trainerUserId);
}
