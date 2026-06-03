package com.smalaca.trainingcenter.accounting.domain.seller;

import com.smalaca.archetypes.annotations.ArchetypeParty;

@ArchetypeParty.PartyRoleConstraint
class ValidTaxNumberConstraint implements SellerConstraint {
    private final CompanyService companyService;

    ValidTaxNumberConstraint(CompanyService companyService) {
        this.companyService = companyService;
    }

    @Override
    public boolean isSatisfiedBy(SellerId sellerId) {
        return companyService.hasValidTaxNumber(sellerId);
    }
}
