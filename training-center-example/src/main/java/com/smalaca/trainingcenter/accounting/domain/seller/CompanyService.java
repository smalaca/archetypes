package com.smalaca.trainingcenter.accounting.domain.seller;

import com.smalaca.annotations.architecture.PortsAndAdaptersArchitecture.DrivenPort;

@DrivenPort
public interface CompanyService {
    boolean isLegalEntity(SellerId sellerId);
    boolean hasValidTaxNumber(SellerId sellerId);
}
