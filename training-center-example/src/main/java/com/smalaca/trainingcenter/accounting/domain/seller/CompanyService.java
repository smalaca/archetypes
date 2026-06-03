package com.smalaca.trainingcenter.accounting.domain.seller;

interface CompanyService {
    boolean isLegalEntity(SellerId sellerId);
    boolean hasValidTaxNumber(SellerId sellerId);
}
