package com.smalaca.trainingcenter.accounting.domain.seller;

public interface CompanyService {
    boolean isLegalEntity(SellerId sellerId);
    boolean hasValidTaxNumber(SellerId sellerId);
}
