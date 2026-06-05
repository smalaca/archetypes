package com.smalaca.trainingcenter.accounting.domain.seller;

import com.smalaca.annotations.archetypes.ArchetypeParty;

@ArchetypeParty.PartyRoleConstraint
class AccreditationConstraint implements SellerConstraint {
    private final AccreditationService accreditationService;

    AccreditationConstraint(AccreditationService accreditationService) {
        this.accreditationService = accreditationService;
    }

    @Override
    public boolean isSatisfiedBy(SellerId sellerId) {
        return accreditationService.hasValidAccreditation(sellerId);
    }
}
