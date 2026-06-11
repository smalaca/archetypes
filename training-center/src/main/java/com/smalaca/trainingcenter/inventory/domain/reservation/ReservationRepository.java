package com.smalaca.trainingcenter.inventory.domain.reservation;

import com.smalaca.annotations.architecture.DomainDrivenDesign;
import com.smalaca.annotations.architecture.PortsAndAdaptersArchitecture;

@DomainDrivenDesign.Repository
@PortsAndAdaptersArchitecture.DrivenPort
public interface ReservationRepository {
    boolean hasNoScheduleConflict(ParticipantAvailabilityRequest request);
}
