package com.smalaca.trainingcenter.accounting.domain.seller;

interface AccreditationService {
    boolean hasValidAccreditation(SellerId sellerId);
}
