package com.smalaca.trainingcenter.accounting.domain.seller;

import com.smalaca.archetypes.annotations.ArchetypeParty;

@ArchetypeParty.PartyRoleConstraint
class LegalEntityConstraint implements SellerConstraint {
    private final CompanyService companyService;

    LegalEntityConstraint(CompanyService companyService) {
        this.companyService = companyService;
    }

    @Override
    public boolean isSatisfiedBy(SellerId sellerId) {
        return companyService.isLegalEntity(sellerId);
    }
}
