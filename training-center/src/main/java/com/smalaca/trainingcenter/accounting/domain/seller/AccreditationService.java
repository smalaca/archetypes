package com.smalaca.trainingcenter.accounting.domain.seller;

import com.smalaca.annotations.architecture.PortsAndAdaptersArchitecture.DrivenPort;

@DrivenPort
public interface AccreditationService {
    boolean hasValidAccreditation(SellerId sellerId);
}
