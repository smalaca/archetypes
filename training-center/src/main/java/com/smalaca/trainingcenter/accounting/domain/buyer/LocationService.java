package com.smalaca.trainingcenter.accounting.domain.buyer;

import com.smalaca.annotations.architecture.PortsAndAdaptersArchitecture.DrivenPort;

@DrivenPort
public interface LocationService {
    boolean isEligible(BuyerId buyerId);
}
